package com.kacimiamine.velvety.config;

import net.kyori.adventure.util.TriState;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

@SuppressWarnings("unused")
public class VelvetyGlobalConfig {

    protected static final String CONFIG_NAME = "velvety-global.yml";
    private static YamlConfiguration config;
    private static int version;

    protected static void init(File configDir) {
        File configFile = new File(configDir, CONFIG_NAME);
        config = YamlConfiguration.loadConfiguration(configFile);

        version = getInt("version", VelvetyConfig.VERSION);
        set("version", VelvetyConfig.VERSION);

        VelvetyConfig.readConfig(VelvetyGlobalConfig.class, null, configFile, config);
    }

    private static void set(String path, Object value) {
        VelvetyConfig.set(config, path, value);
    }

    private static int getInt(String path, int value) {
        return VelvetyConfig.getInt(config, path, value);
    }

    private static String getString(String path, String value) {
        return VelvetyConfig.getString(config, path, value);
    }

    private static double getDouble(String path, double value) {
        return VelvetyConfig.getDouble(config, path, value);
    }

    private static boolean getBoolean(String path, boolean value) {
        return VelvetyConfig.getBoolean(config, path, value);
    }

    public static TriState getTriState(String path, TriState value) {
        return VelvetyConfig.getTriState(config, path, value);
    }

    public static boolean anvilCumulativeCost = true;
    private static void anvilSettings() {
        anvilCumulativeCost = getBoolean("blocks.anvil.cumulative-cost", anvilCumulativeCost);
    }
}
