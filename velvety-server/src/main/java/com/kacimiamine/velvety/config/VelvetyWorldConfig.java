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

    public boolean leavesInstantDecay = false;
    private void leavesSettings() {
        leavesInstantDecay = getBoolean("blocks.leaves.instant-decay", leavesInstantDecay);
    }

    public boolean boggedShouldBurnInDay = true;
    private void boggedSettings() {
        boggedShouldBurnInDay = getBoolean("mobs.bogged.should-burn-in-day", boggedShouldBurnInDay);
    }

    public boolean breezeShouldBurnInDay = false;
    private void breezeSettings() {
        breezeShouldBurnInDay = getBoolean("mobs.breeze.should-burn-in-day", breezeShouldBurnInDay);
    }

    public boolean camelHuskShouldBurnInDay = false;
    private void camelHuskSettings() {
        camelHuskShouldBurnInDay = getBoolean("mobs.camel_husk.should-burn-in-day", camelHuskShouldBurnInDay);
    }

    public boolean caveSpiderShouldBurnInDay = false;
    private void caveSpiderSettings() {
        caveSpiderShouldBurnInDay = getBoolean("mobs.cave_spider.should-burn-in-day", caveSpiderShouldBurnInDay);
    }

    public boolean creeperShouldBurnInDay = false;
    private void creeperSettings() {
        creeperShouldBurnInDay = getBoolean("mobs.creeper.should-burn-in-day", creeperShouldBurnInDay);
    }

    public boolean drownedShouldBurnInDay = true;
    private void drownedSettings() {
        drownedShouldBurnInDay = getBoolean("mobs.drowned.should-burn-in-day", drownedShouldBurnInDay);
    }

    public boolean elderGuardianShouldBurnInDay = false;
    private void elderGuardianSettings() {
        elderGuardianShouldBurnInDay = getBoolean("mobs.elder_guardian.should-burn-in-day", elderGuardianShouldBurnInDay);
    }

    public boolean endermiteShouldBurnInDay = false;
    private void endermiteSettings() {
        endermiteShouldBurnInDay = getBoolean("mobs.endermite.should-burn-in-day", endermiteShouldBurnInDay);
    }

    public boolean guardianShouldBurnInDay = false;
    private void guardianSettings() {
        guardianShouldBurnInDay = getBoolean("mobs.guardian.should-burn-in-day", guardianShouldBurnInDay);
    }

    public boolean huskShouldBurnInDay = false;
    private void huskSettings() {
        huskShouldBurnInDay = getBoolean("mobs.husk.should-burn-in-day", huskShouldBurnInDay);
    }

    public boolean parchedShouldBurnInDay = false;
    private void parchedSettings() {
        parchedShouldBurnInDay = getBoolean("mobs.parched.should-burn-in-day", parchedShouldBurnInDay);
    }

    public boolean phantomShouldBurnInDay = true;
    private void phantomSettings() {
        phantomShouldBurnInDay = getBoolean("mobs.phantom.should-burn-in-day", phantomShouldBurnInDay);
    }

    public boolean shulkerShouldBurnInDay = false;
    private void shulkerSettings() {
        shulkerShouldBurnInDay = getBoolean("mobs.shulker.should-burn-in-day", shulkerShouldBurnInDay);
    }

    public boolean silverfishShouldBurnInDay = false;
    private void silverfishSettings() {
        silverfishShouldBurnInDay = getBoolean("mobs.silverfish.should-burn-in-day", silverfishShouldBurnInDay);
    }

    public boolean skeletonShouldBurnInDay = true;
    private void skeletonSettings() {
        skeletonShouldBurnInDay = getBoolean("mobs.skeleton.should-burn-in-day", skeletonShouldBurnInDay);
    }

    public boolean skeletonHorseShouldBurnInDay = false;
    private void skeletonHorseSettings() {
        skeletonHorseShouldBurnInDay = getBoolean("mobs.skeleton_horse.should-burn-in-day", skeletonHorseShouldBurnInDay);
    }

    public boolean slimeShouldBurnInDay = false;
    private void slimeSettings() {
        slimeShouldBurnInDay = getBoolean("mobs.slime.should-burn-in-day", slimeShouldBurnInDay);
    }

    public boolean snowGolemShouldBurnInDay = false;
    private void snowGolemSettings() {
        snowGolemShouldBurnInDay = getBoolean("mobs.snow_golem.should-burn-in-day", snowGolemShouldBurnInDay);
    }

    public boolean spiderShouldBurnInDay = false;
    private void spiderSettings() {
        spiderShouldBurnInDay = getBoolean("mobs.spider.should-burn-in-day", spiderShouldBurnInDay);
    }

    public boolean strayShouldBurnInDay = true;
    private void straySettings() {
        strayShouldBurnInDay = getBoolean("mobs.stray.should-burn-in-day", strayShouldBurnInDay);
    }

    public boolean sulfurCubeShouldBurnInDay = false;
    private void sulfurCubeSettings() {
        sulfurCubeShouldBurnInDay = getBoolean("mobs.sulfur_cube.should-burn-in-day", sulfurCubeShouldBurnInDay);
    }

    public boolean vexShouldBurnInDay = false;
    private void vexSettings() {
        vexShouldBurnInDay = getBoolean("mobs.vex.should-burn-in-day", vexShouldBurnInDay);
    }

    public boolean witchShouldBurnInDay = false;
    private void witchSettings() {
        witchShouldBurnInDay = getBoolean("mobs.witch.should-burn-in-day", witchShouldBurnInDay);
    }

    public boolean zombieShouldBurnInDay = true;
    private void zombieSettings() {
        zombieShouldBurnInDay = getBoolean("mobs.zombie.should-burn-in-day", zombieShouldBurnInDay);
    }

    public boolean zombieHorseShouldBurnInDay = true;
    private void zombieHorseSettings() {
        zombieHorseShouldBurnInDay = getBoolean("mobs.zombie_horse.should-burn-in-day", zombieHorseShouldBurnInDay);
    }

    public boolean zombieNautilusShouldBurnInDay = true;
    private void zombieNautilusSettings() {
        zombieNautilusShouldBurnInDay = getBoolean("mobs.zombie_nautilus.should-burn-in-day", zombieNautilusShouldBurnInDay);
    }

    public boolean zombieVillagerShouldBurnInDay = true;
    private void zombieVillagerSettings() {
        zombieVillagerShouldBurnInDay = getBoolean("mobs.zombie_villager.should-burn-in-day", zombieVillagerShouldBurnInDay);
    }
}
