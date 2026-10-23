#!/usr/bin/env bash
set -e

current() {
    grep '^paperCommit' gradle.properties | cut -d= -f2 | tr -d ' ';
}

latest()  {
    git ls-remote https://github.com/PaperMC/Paper.git "refs/heads/${1:-main}" | cut -f1;
}

case "$1" in
  log)
      git fetch -q https://github.com/PaperMC/Paper.git "${2:-main}" && git log --color=always --format='%C(yellow)%H%Creset %s' $(current)..FETCH_HEAD
      ;;
  bump)
      sha=$(latest $2); sed -i "s/^paperCommit.*/paperCommit=${sha:-$2}/" gradle.properties
      ;;
  apply)
      ./gradlew applyAllPatches
      ;;
  rebuild-single)
      ./gradlew rebuildPaperSingleFilePatches
      ;;
  rebuild)
      ./gradlew rebuildPaperPatches && ./gradlew rebuildAllServerPatches
      ;;
  update)
      ./scripts/updateUpstream.sh
      ;;
  *)
      echo "usage: $0 <command>"
      echo "  log [branch]               show commits between yours and the upstream branch (default: main)"
      echo "  bump [branch | commit]     set paperCommit in gradle.properties to the branch's latest commit or a specific commit"
      echo "  apply                      apply all patches"
      echo "  rebuild                    rebuild Paper and server patches"
      echo "  rebuild-single             rebuild Paper single file patches"
      echo "  update                     run the updateUpstream script"
      ;;
esac
