package com.kacimiamine.velvety.config;

import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import java.util.List;

@SuppressWarnings("unused")
public class VelvetyWorldConfig {

    private final String worldName;
    private boolean verbose;

    public VelvetyWorldConfig(Key worldKey) {
        this.worldName = worldKey.asString();
        this.init();
    }

    public void init() {
        this.verbose = this.getBoolean("verbose", false);

        this.log("-------- World Settings For [" + this.worldName + "] --------");
        VelvetyConfig.readConfig(VelvetyWorldConfig.class, this);
    }

    private void log(String s) {
        if (this.verbose) {
            Bukkit.getLogger().info(s);
        }
    }

    private void set(String path, Object val) {
        VelvetyConfig.config.set("world-settings.default." + path, val);
    }

    public boolean getBoolean(String path, boolean def) {
        VelvetyConfig.config.addDefault("world-settings.default." + path, def);
        return VelvetyConfig.config.getBoolean("world-settings." + this.worldName + "." + path, VelvetyConfig.config.getBoolean("world-settings.default." + path));
    }

    public double getDouble(String path, double def) {
        VelvetyConfig.config.addDefault("world-settings.default." + path, def);
        return VelvetyConfig.config.getDouble("world-settings." + this.worldName + "." + path, VelvetyConfig.config.getDouble("world-settings.default." + path));
    }

    public int getInt(String path, int def) {
        VelvetyConfig.config.addDefault("world-settings.default." + path, def);
        return VelvetyConfig.config.getInt("world-settings." + this.worldName + "." + path, VelvetyConfig.config.getInt("world-settings.default." + path));
    }

    public <T> List getList(String path, T def) {
        VelvetyConfig.config.addDefault("world-settings.default." + path, def);
        return (List<T>) VelvetyConfig.config.getList("world-settings." + this.worldName + "." + path, VelvetyConfig.config.getList("world-settings.default." + path));
    }

    public String getString(String path, String def) {
        VelvetyConfig.config.addDefault("world-settings.default." + path, def);
        return VelvetyConfig.config.getString("world-settings." + this.worldName + "." + path, VelvetyConfig.config.getString("world-settings.default." + path));
    }

    private Object get(String path, Object def) {
        VelvetyConfig.config.addDefault("world-settings.default." + path, def);
        return VelvetyConfig.config.get("world-settings." + this.worldName + "." + path, VelvetyConfig.config.get("world-settings.default." + path));
    }
}
