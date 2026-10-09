package com.kacimiamine.velvety.util;

import com.destroystokyo.paper.VersionHistoryManager;
import com.destroystokyo.paper.util.VersionFetcher;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.logging.LogUtils;
import io.papermc.paper.ServerBuildInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.OptionalInt;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.color;
import static io.papermc.paper.ServerBuildInfo.StringRepresentation.VERSION_SIMPLE;

@DefaultQualifier(NonNull.class)
public class VelvetyVersionFetcher implements VersionFetcher {
    private static final Logger LOGGER = LogUtils.getClassLogger();
    private static final ComponentLogger COMPONENT_LOGGER = ComponentLogger.logger(LogManager.getRootLogger().getName());
    private static final String DOWNLOAD_PAGE = "https://github.com/kacimiamine/releases";
    private static final String REPOSITORY = "kacimiamine/Velvety";
    private static final ServerBuildInfo BUILD_INFO = ServerBuildInfo.buildInfo();
    private static final String USER_AGENT = BUILD_INFO.brandName() + "/" + BUILD_INFO.asString(VERSION_SIMPLE) + " (https://github.com/kacimiamine)";
    private static final Gson GSON = new Gson();

    enum MinecraftVersionStatus {
        ERROR,
        UNKNOWN,
        OUTDATED,
        LATEST
    }

    record Release(MinecraftVersionStatus versionStatus, int buildDistance) {}

    @Override
    public long getCacheTime() {
        return 720000;
    }

    @Override
    public Component getVersionMessage() {
        final Component updateMessage;
        if (BUILD_INFO.buildNumber().isEmpty() && BUILD_INFO.gitCommit().isEmpty()) {
            updateMessage = text("You are running a development version without access to version information", color(0xFF5300));
        } else {
            updateMessage = getUpdateStatusMessage();
        }
        final @Nullable Component history = this.getHistory();

        return history != null ? Component.textOfChildren(updateMessage, Component.newline(), history) : updateMessage;
    }

    public static void getUpdateStatusStartupMessage() {
        Release distance = new Release(MinecraftVersionStatus.ERROR, -1);

        final OptionalInt buildNumber = BUILD_INFO.buildNumber();
        if (buildNumber.isEmpty() && BUILD_INFO.gitCommit().isEmpty()) {
            COMPONENT_LOGGER.warn(text("*** You are running a development version without access to version information ***"));
        } else {
            if (buildNumber.isPresent()) {
                distance = fetchDistanceFromReleases(BUILD_INFO.minecraftVersionId(), buildNumber.getAsInt());
            } else {
                final Optional<String> gitBranch = BUILD_INFO.gitBranch();
                final Optional<String> gitCommit = BUILD_INFO.gitCommit();
                if (gitBranch.isPresent() && gitCommit.isPresent()) {
                    distance = fetchDistanceFromGitHub(gitBranch.get(), gitCommit.get());
                }
            }

            switch (distance.versionStatus) {
                case ERROR -> COMPONENT_LOGGER.error(text("*** Error obtaining version information! Cannot fetch version info ***"));
                case UNKNOWN -> COMPONENT_LOGGER.warn(text("*** You are running an unknown version! Cannot fetch version info ***"));
                case OUTDATED -> {
                    COMPONENT_LOGGER.warn(text("*************************************************************************************"));
                    COMPONENT_LOGGER.warn(text("You are running an outdated Minecraft version (" + BUILD_INFO.minecraftVersionId() + ")"));
                    COMPONENT_LOGGER.warn(text("It is recommended that you update to the latest version as soon as possible"));
                    COMPONENT_LOGGER.warn(text(DOWNLOAD_PAGE));
                    COMPONENT_LOGGER.warn(text("*************************************************************************************"));
                }
                case LATEST -> {
                    if (distance.buildDistance == 0) {
                        COMPONENT_LOGGER.info(text("*** You are running the latest version (" + BUILD_INFO.minecraftVersionId() + ") ***"));
                    } else {
                        COMPONENT_LOGGER.info(text("*** Currently you are " + distance.buildDistance + " build" + (distance.buildDistance == 1 ? "" : "s") + " behind ***"));
                        COMPONENT_LOGGER.info(text("*** It is highly recommended to download the latest build from " + DOWNLOAD_PAGE + " ***"));
                    }
                }
            }
        }
    }

    private static Component getUpdateStatusMessage() {
        Release distance = new Release(MinecraftVersionStatus.ERROR, -1);

        final OptionalInt buildNumber = BUILD_INFO.buildNumber();
        if (buildNumber.isPresent()) {
            distance = fetchDistanceFromReleases(BUILD_INFO.minecraftVersionId(), buildNumber.getAsInt());
        } else {
            final Optional<String> gitBranch = BUILD_INFO.gitBranch();
            final Optional<String> gitCommit = BUILD_INFO.gitCommit();
            if (gitBranch.isPresent() && gitCommit.isPresent()) {
                distance = fetchDistanceFromGitHub(gitBranch.get(), gitCommit.get());
            }
        }

        return switch (distance.versionStatus) {
            case ERROR -> text("Error obtaining version information", NamedTextColor.YELLOW);
            case UNKNOWN -> text("Unknown version", NamedTextColor.YELLOW);
            case OUTDATED -> text("You are running an outdated Minecraft version", NamedTextColor.YELLOW);
            case LATEST -> {
                if (distance.buildDistance == 0) {
                    yield text("You are running the latest version", NamedTextColor.GREEN);
                } else {
                    yield text("You are " + distance.buildDistance + " version" + (distance.buildDistance == 1 ? "" : "s") + " behind", NamedTextColor.YELLOW)
                        .append(Component.newline())
                        .append(text("Download the new version at: ")
                            .append(text(DOWNLOAD_PAGE, NamedTextColor.GOLD)
                                .hoverEvent(text("Click to open", NamedTextColor.WHITE))
                                .clickEvent(ClickEvent.openUrl(DOWNLOAD_PAGE))));
                }
            }
        };
    }

    private static MinecraftVersionStatus compareMinecraftVersions(final String current, final String latest) {
        if (current.equals(latest)) return MinecraftVersionStatus.LATEST;
        final String[] currentParts = current.split("\\.");
        final String[] latestParts = latest.split("\\.");
        for (int i = 0; i < Math.max(currentParts.length, latestParts.length); i++) {
            int currentPart = i < currentParts.length ? Integer.parseInt(currentParts[i]) : 0;
            int latestPart = i < latestParts.length ? Integer.parseInt(latestParts[i]) : 0;
            if (currentPart < latestPart) return MinecraftVersionStatus.OUTDATED;
            if (currentPart > latestPart) return MinecraftVersionStatus.LATEST;
        }
        return MinecraftVersionStatus.LATEST;
    }

    private static Release fetchDistanceFromReleases(final String currentVersion, final int currentBuild) {
        try {
            final HttpURLConnection connection = (HttpURLConnection) URI.create("https://api.github.com/repos/%s/releases".formatted(REPOSITORY)).toURL().openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) return new Release(MinecraftVersionStatus.UNKNOWN, -1); // Unknown
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                final JsonArray releases = GSON.fromJson(reader, JsonArray.class);
                String latestVersion = currentVersion;
                int latestBuild = currentBuild;
                String mcVersion;
                int buildNumber;
                for (JsonElement release : releases) {
                    String[] tagParts = release.getAsJsonObject().get("tag_name").getAsString().split("-");
                    mcVersion = tagParts[1];
                    buildNumber = Integer.parseInt(tagParts[2]);
                    MinecraftVersionStatus status = compareMinecraftVersions(latestVersion, mcVersion);
                    if (status == MinecraftVersionStatus.OUTDATED || (status == MinecraftVersionStatus.LATEST && latestBuild < buildNumber)) {
                        latestVersion = mcVersion;
                        latestBuild = buildNumber;
                    }
                }
                return new Release(compareMinecraftVersions(currentVersion, latestVersion), latestBuild - currentBuild);
            } catch (final JsonSyntaxException ex) {
                LOGGER.error("Error parsing json from Github's releases API", ex);
                return new Release(MinecraftVersionStatus.ERROR, -1);
            }
        } catch (final IOException e) {
            LOGGER.error("Error while parsing version", e);
            return new Release(MinecraftVersionStatus.ERROR, -1);
        }
    }

    // Contributed by Techcable <Techcable@outlook.com> in GH-65
    private static Release fetchDistanceFromGitHub(final String branch, final String hash) {
        try {
            final HttpURLConnection connection = (HttpURLConnection) URI.create("https://api.github.com/repos/%s/compare/%s...%s".formatted(REPOSITORY, branch, hash)).toURL().openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) return new Release(MinecraftVersionStatus.UNKNOWN, -1); // Unknown commit
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                final JsonObject obj = GSON.fromJson(reader, JsonObject.class);
                final String status = obj.get("status").getAsString();
                return switch (status) {
                    case "identical" -> new Release(MinecraftVersionStatus.LATEST, 0);
                    case "behind" -> new Release(MinecraftVersionStatus.LATEST, obj.get("behind_by").getAsInt());
                    default -> new Release(MinecraftVersionStatus.ERROR, -1);
                };
            } catch (final JsonSyntaxException | NumberFormatException e) {
                LOGGER.error("Error parsing json from GitHub's API", e);
                return new Release(MinecraftVersionStatus.ERROR, -1);
            }
        } catch (final IOException e) {
            LOGGER.error("Error while parsing version", e);
            return new Release(MinecraftVersionStatus.ERROR, -1);
        }
    }

    private @Nullable Component getHistory() {
        final VersionHistoryManager.@Nullable VersionData data = VersionHistoryManager.INSTANCE.getVersionData();
        if (data == null) {
            return null;
        }

        final @Nullable String oldVersion = data.getOldVersion();
        if (oldVersion == null) {
            return null;
        }

        return text("Previous version: " + oldVersion, NamedTextColor.GRAY, TextDecoration.ITALIC);
    }
}
