#!/usr/bin/env bash

# requires curl & jq

# upstreamCommit --paper HASH
# flag: --paper HASH - (Optional) the commit hash to use for comparing commits between paper (PaperMC/Paper/compare/HASH...HEAD)

function getCommits() {
    curl -H "Accept: application/vnd.github.v3+json" https://api.github.com/repos/"$1"/compare/"$2"..."$3" | jq -r '.commits[] | "'"$1"'@\(.sha[:8]) \(.commit.message | split("\r\n")[0] | split("\n")[0])" | sub("\\[ci( |-)skip]"; "[ci/skip]")'
}

function getDiff() {
    if git diff --cached --quiet -- gradle.properties; then
        git add gradle.properties
    fi

    git diff --cached gradle.properties | sed -n "s/^${1}paperCommit=//p"
}

(
set -e
PS1="$"

paperHash=$(getDiff '-')

TEMP=$(getopt --long paper: -o "" -- "$@")
eval set -- "$TEMP"
while true; do
    case "$1" in
        --paper)
            paperHash="$2"
            shift 2
            ;;
        *)
            break
            ;;
    esac
done

paper=""
updated=""
logsuffix=""

# Paper updates
if [ -n "$paperHash" ]; then
    newHash=$(getDiff '+')
    paper=$(getCommits "PaperMC/Paper" "$paperHash" $(echo $newHash | grep . -q && echo $newHash || echo "HEAD"))

    # Updates found
    if [ -n "$paper" ]; then
        updated="Paper"
        logsuffix="$logsuffix\n\nPaper Changes:\n$paper"
    fi
fi

disclaimer="Upstream has released updates that appear to apply and compile correctly"
log="Updated Upstream ($updated)\n\n${disclaimer}${logsuffix}"

git add -A

echo -e "$log" | git commit -F -

) || exit 1
