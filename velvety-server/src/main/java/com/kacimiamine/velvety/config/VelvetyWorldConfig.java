package com.kacimiamine.velvety.config;

import net.kyori.adventure.key.Key;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

@SuppressWarnings("unused")
public class VelvetyWorldConfig {

    // Default config
    protected static final String DEFAULT_CONFIG_NAME = "velvety-world-defaults.yml";
    private static final File DEFAULT_CONFIG_FILE;
    private static YamlConfiguration defaultConfig;

    // World config files
    private final File worldFile;
    private YamlConfiguration worldConfig;

    private int version;

    static {
        DEFAULT_CONFIG_FILE = new File(VelvetyConfig.CONFIG_DIR, DEFAULT_CONFIG_NAME);
    }

    public VelvetyWorldConfig(Key worldKey) {
        worldFile = new File(VelvetyConfig.CONFIG_DIR, "velvety-world_" + worldKey.asMinimalString() + ".yml");
        init();
    }

    public void init() {
        defaultConfig = YamlConfiguration.loadConfiguration(DEFAULT_CONFIG_FILE);
        worldConfig = worldFile.exists() ? YamlConfiguration.loadConfiguration(worldFile) : null;
        version = getInt("version", VelvetyConfig.VERSION);
        set("version", VelvetyConfig.VERSION);

        VelvetyConfig.readConfig(VelvetyWorldConfig.class, this, DEFAULT_CONFIG_FILE, defaultConfig);
        if (worldConfig != null) VelvetyConfig.readConfig(VelvetyWorldConfig.class, this, worldFile, worldConfig);
    }

    private void set(String path, Object value) {
        VelvetyConfig.set(defaultConfig, path, value);
        if (worldConfig != null) VelvetyConfig.set(worldConfig, path, value);
    }

    private int getInt(String path, int value) {
        if (worldConfig != null) return VelvetyConfig.getInt(worldConfig, path, value);
        return VelvetyConfig.getInt(defaultConfig, path, value);
    }

    private String getString(String path, String value) {
        if (worldConfig != null) return VelvetyConfig.getString(worldConfig, path, value);
        return VelvetyConfig.getString(defaultConfig, path, value);
    }

    private double getDouble(String path, double value) {
        if (worldConfig != null) return VelvetyConfig.getDouble(worldConfig, path, value);
        return VelvetyConfig.getDouble(defaultConfig, path, value);
    }

    private boolean getBoolean(String path, boolean value) {
        if (worldConfig != null) return VelvetyConfig.getBoolean(worldConfig, path, value);
        return VelvetyConfig.getBoolean(defaultConfig, path, value);
    }

    public boolean leavesInstantDecay = false;
    private void blockSettings() {
        leavesInstantDecay = getBoolean("blocks.leaves.instant-decay", leavesInstantDecay);
    }
}
