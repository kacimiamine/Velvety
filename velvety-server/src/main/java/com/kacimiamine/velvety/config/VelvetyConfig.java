package com.kacimiamine.velvety.config;

import com.kacimiamine.velvety.command.VelvetyCommand;
import com.mojang.logging.LogUtils;
import net.kyori.adventure.util.TriState;
import net.minecraft.server.MinecraftServer;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class VelvetyConfig {

    protected static final Logger LOGGER = LogUtils.getClassLogger();
    public static final String CONFIG_DIR_NAME = "velvety";
    protected static File CONFIG_DIR;
    private static Map<String, Command> COMMANDS;
    protected static final int VERSION = 1;

    public static void init(File configDir) {
        CONFIG_DIR = configDir;
        if (!CONFIG_DIR.exists() && !CONFIG_DIR.mkdirs()) {
            LOGGER.error("Could not create directory: {}", CONFIG_DIR);
        }

        COMMANDS = new HashMap<>();
        COMMANDS.put("velvety", new VelvetyCommand("velvety"));

        VelvetyGlobalConfig.init(CONFIG_DIR);
    }

    public static void registerCommands() {
        COMMANDS.forEach((name, command) -> MinecraftServer.getServer().server.getCommandMap().register(name, "Velvety", command));
    }

    protected static void readConfig(Class<?> clazz, Object instance, File configFile, YamlConfiguration config) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isPrivate(method.getModifiers())) {
                if (method.getParameterTypes().length == 0 && method.getReturnType() == Void.TYPE) {
                    try {
                        method.setAccessible(true);
                        method.invoke(instance);
                    } catch (InvocationTargetException ex) {
                        throw new RuntimeException(ex.getCause());
                    } catch (Exception ex) {
                        VelvetyConfig.LOGGER.error("Error invoking {}", method, ex);
                    }
                }
            }
        }

        try {
            config.save(configFile);
        } catch (IOException ex) {
            VelvetyConfig.LOGGER.error("Could not save {}", configFile, ex);
        }
    }

    public static String getSparkVelvetyConfigs() {
        File configDir = new File(CONFIG_DIR_NAME);
        File[] files = configDir.listFiles(((_, name) -> name.startsWith("velvety-") && name.endsWith(".yml")));
        if (files == null) return "";
        return Arrays.stream(files)
            .map(f -> CONFIG_DIR_NAME + "/" + f.getName())
            .collect(Collectors.joining(","));
    }

    public static void set(YamlConfiguration config, String path, Object value) {
        config.set(path, value);
    }

    public static int getInt(YamlConfiguration config, String path, int value) {
        if (config.isSet(path)) return config.getInt(path);
        config.set(path, value);
        return value;
    }

    public static double getDouble(YamlConfiguration config, String path, double value) {
        if (config.isSet(path)) return config.getDouble(path);
        config.set(path, value);
        return value;
    }

    public static String getString(YamlConfiguration config, String path, String value) {
        if (config.isSet(path)) return config.getString(path);
        config.set(path, value);
        return value;
    }

    public static boolean getBoolean(YamlConfiguration config, String path, boolean value) {
        if (config.isSet(path)) return config.getBoolean(path);
        config.set(path, value);
        return value;
    }

    public static TriState getTriState(YamlConfiguration config, String path, TriState value) {
        if (config.isSet(path)) {
            return TriState.valueOf(Objects.requireNonNull(config.getString(path)).toUpperCase());
        }
        config.set(path, value.name().toLowerCase());
        return value;
    }
}
