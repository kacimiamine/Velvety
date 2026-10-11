package com.kacimiamine.velvety.config;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.util.TriState;
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

    private TriState getTriState(String path, TriState value) {
        if (worldConfig != null) return VelvetyConfig.getTriState(worldConfig, path, value);
        return VelvetyConfig.getTriState(defaultConfig, path, value);
    }

    public boolean chestAlwaysOpen = false;
    public boolean enderChestAlwaysOpen = false;
    public boolean leavesInstantDecay = false;
    public boolean shulkerBoxAlwaysOpen = false;
    private void blockSettings() {
        chestAlwaysOpen = getBoolean("blocks.chest.always-open", chestAlwaysOpen);
        enderChestAlwaysOpen = getBoolean("blocks.ender_chest.always-open", enderChestAlwaysOpen);
        leavesInstantDecay = getBoolean("blocks.leaves.instant-decay", leavesInstantDecay);
        shulkerBoxAlwaysOpen = getBoolean("blocks.shulker_box.always-open", shulkerBoxAlwaysOpen);
    }

    public TriState boggedBurnInDaylightOverride = TriState.NOT_SET;
    private void boggedSettings() {
        boggedBurnInDaylightOverride = getTriState("mobs.bogged.burn-in-daylight-override", boggedBurnInDaylightOverride);
    }

    public TriState breezeBurnInDaylightOverride = TriState.NOT_SET;
    private void breezeSettings() {
        breezeBurnInDaylightOverride = getTriState("mobs.breeze.burn-in-daylight-override", breezeBurnInDaylightOverride);
    }

    public TriState camelHuskBurnInDaylightOverride = TriState.NOT_SET;
    private void camelHuskSettings() {
        camelHuskBurnInDaylightOverride = getTriState("mobs.camel_husk.burn-in-daylight-override", camelHuskBurnInDaylightOverride);
    }

    public TriState caveSpiderBurnInDaylightOverride = TriState.NOT_SET;
    private void caveSpiderSettings() {
        caveSpiderBurnInDaylightOverride = getTriState("mobs.cave_spider.burn-in-daylight-override", caveSpiderBurnInDaylightOverride);
    }

    public TriState creeperBurnInDaylightOverride = TriState.NOT_SET;
    private void creeperSettings() {
        creeperBurnInDaylightOverride = getTriState("mobs.creeper.burn-in-daylight-override", creeperBurnInDaylightOverride);
    }

    public TriState drownedBurnInDaylightOverride = TriState.NOT_SET;
    private void drownedSettings() {
        drownedBurnInDaylightOverride = getTriState("mobs.drowned.burn-in-daylight-override", drownedBurnInDaylightOverride);
    }

    public TriState elderGuardianBurnInDaylightOverride = TriState.NOT_SET;
    private void elderGuardianSettings() {
        elderGuardianBurnInDaylightOverride = getTriState("mobs.elder_guardian.burn-in-daylight-override", elderGuardianBurnInDaylightOverride);
    }

    public TriState endermanBurnInDaylightOverride = TriState.NOT_SET;
    private void endermanSettings() {
        endermanBurnInDaylightOverride = getTriState("mobs.enderman.burn-in-daylight-override", endermanBurnInDaylightOverride);
    }

    public TriState endermiteBurnInDaylightOverride = TriState.NOT_SET;
    private void endermiteSettings() {
        endermiteBurnInDaylightOverride = getTriState("mobs.endermite.burn-in-daylight-override", endermiteBurnInDaylightOverride);
    }

    public TriState guardianBurnInDaylightOverride = TriState.NOT_SET;
    private void guardianSettings() {
        guardianBurnInDaylightOverride = getTriState("mobs.guardian.burn-in-daylight-override", guardianBurnInDaylightOverride);
    }

    public TriState huskBurnInDaylightOverride = TriState.NOT_SET;
    private void huskSettings() {
        huskBurnInDaylightOverride = getTriState("mobs.husk.burn-in-daylight-override", huskBurnInDaylightOverride);
    }

    public TriState parchedBurnInDaylightOverride = TriState.NOT_SET;
    private void parchedSettings() {
        parchedBurnInDaylightOverride = getTriState("mobs.parched.burn-in-daylight-override", parchedBurnInDaylightOverride);
    }

    public TriState phantomBurnInDaylightOverride = TriState.NOT_SET;
    private void phantomSettings() {
        phantomBurnInDaylightOverride = getTriState("mobs.phantom.burn-in-daylight-override", phantomBurnInDaylightOverride);
    }

    public TriState silverfishBurnInDaylightOverride = TriState.NOT_SET;
    private void silverfishSettings() {
        silverfishBurnInDaylightOverride = getTriState("mobs.silverfish.burn-in-daylight-override", silverfishBurnInDaylightOverride);
    }

    public TriState skeletonBurnInDaylightOverride = TriState.NOT_SET;
    private void skeletonSettings() {
        skeletonBurnInDaylightOverride = getTriState("mobs.skeleton.burn-in-daylight-override", skeletonBurnInDaylightOverride);
    }

    public TriState skeletonHorseBurnInDaylightOverride = TriState.NOT_SET;
    private void skeletonHorseSettings() {
        skeletonHorseBurnInDaylightOverride = getTriState("mobs.skeleton_horse.burn-in-daylight-override", skeletonHorseBurnInDaylightOverride);
    }

    public TriState slimeBurnInDaylightOverride = TriState.NOT_SET;
    private void slimeSettings() {
        slimeBurnInDaylightOverride = getTriState("mobs.slime.burn-in-daylight-override", slimeBurnInDaylightOverride);
    }

    public TriState snowGolemBurnInDaylightOverride = TriState.NOT_SET;
    private void snowGolemSettings() {
        snowGolemBurnInDaylightOverride = getTriState("mobs.snow_golem.burn-in-daylight-override", snowGolemBurnInDaylightOverride);
    }

    public TriState spiderBurnInDaylightOverride = TriState.NOT_SET;
    private void spiderSettings() {
        spiderBurnInDaylightOverride = getTriState("mobs.spider.burn-in-daylight-override", spiderBurnInDaylightOverride);
    }

    public TriState strayBurnInDaylightOverride = TriState.NOT_SET;
    private void straySettings() {
        strayBurnInDaylightOverride = getTriState("mobs.stray.burn-in-daylight-override", strayBurnInDaylightOverride);
    }

    public TriState vexBurnInDaylightOverride = TriState.NOT_SET;
    private void vexSettings() {
        vexBurnInDaylightOverride = getTriState("mobs.vex.burn-in-daylight-override", vexBurnInDaylightOverride);
    }

    public TriState zombieBurnInDaylightOverride = TriState.NOT_SET;
    private void zombieSettings() {
        zombieBurnInDaylightOverride = getTriState("mobs.zombie.burn-in-daylight-override", zombieBurnInDaylightOverride);
    }

    public TriState zombieHorseBurnInDaylightOverride = TriState.NOT_SET;
    private void zombieHorseSettings() {
        zombieHorseBurnInDaylightOverride = getTriState("mobs.zombie_horse.burn-in-daylight-override", zombieHorseBurnInDaylightOverride);
    }

    public TriState zombieNautilusBurnInDaylightOverride = TriState.NOT_SET;
    private void zombieNautilusSettings() {
        zombieNautilusBurnInDaylightOverride = getTriState("mobs.zombie_nautilus.burn-in-daylight-override", zombieNautilusBurnInDaylightOverride);
    }

    public TriState zombieVillagerBurnInDaylightOverride = TriState.NOT_SET;
    private void zombieVillagerSettings() {
        zombieVillagerBurnInDaylightOverride = getTriState("mobs.zombie_villager.burn-in-daylight-override", zombieVillagerBurnInDaylightOverride);
    }
}
