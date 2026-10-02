package com.kacimiamine.velvety.config;

import net.kyori.adventure.key.Key;
import net.minecraft.world.level.block.FrogspawnBlock;
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

    public boolean chestCanAlwaysOpen = false;
    public boolean enderChestCanAlwaysOpen = false;
    public boolean leavesInstantDecay = false;
    public boolean shulkerBoxCanAlwaysOpen = false;
    public boolean turtleEggUnbreakable = false;
    private void blockSettings() {
        chestCanAlwaysOpen = getBoolean("blocks.chest.can-always-open", chestCanAlwaysOpen);
        leavesInstantDecay = getBoolean("blocks.leaves.instant-decay", leavesInstantDecay);
        enderChestCanAlwaysOpen = getBoolean("blocks.ender_chest.can-always-open", enderChestCanAlwaysOpen);
        shulkerBoxCanAlwaysOpen = getBoolean("blocks.shulker_box.can-always-open", shulkerBoxCanAlwaysOpen);
        turtleEggUnbreakable = getBoolean("blocks.turtle_egg.unbreakable", turtleEggUnbreakable);
    }

    public boolean oneHitKillWhenCreative = false;
    public boolean oneHitKillWhenCreativeUsePermission = true;
    private void oneHitKillWhenCreativeSettings() {
        oneHitKillWhenCreative = getBoolean("gameplay.player.one-hit-kill-when-creative.enabled", oneHitKillWhenCreative);
        oneHitKillWhenCreativeUsePermission = getBoolean("gameplay.player.one-hit-kill-when-creative.use-permission", oneHitKillWhenCreativeUsePermission);
    }

    public double armadilloBreedingChance = 0.0D;
    public int armadilloBreedingMinOffspring = 1;
    public int armadilloBreedingMaxOffspring = 1;
    private void armadilloSettings() {
        armadilloBreedingChance = getDouble("mobs.armadillo.breeding.offspring.chance", armadilloBreedingChance);
        armadilloBreedingMinOffspring = getInt("mobs.armadillo.breeding.offspring.min", armadilloBreedingMinOffspring);
        armadilloBreedingMaxOffspring = getInt("mobs.armadillo.breeding.offspring.max", armadilloBreedingMaxOffspring);
    }

    public double axolotlBreedingChance = 0.0D;
    public int axolotlBreedingMinOffspring = 1;
    public int axolotlBreedingMaxOffspring = 1;
    private void axolotlSettings() {
        axolotlBreedingChance = getDouble("mobs.axolotl.breeding.offspring.chance", axolotlBreedingChance);
        axolotlBreedingMinOffspring = getInt("mobs.axolotl.breeding.offspring.min", axolotlBreedingMinOffspring);
        axolotlBreedingMaxOffspring = getInt("mobs.axolotl.breeding.offspring.max", axolotlBreedingMaxOffspring);
    }

    public double beeBreedingChance = 0.0D;
    public int beeBreedingMinOffspring = 1;
    public int beeBreedingMaxOffspring = 1;
    private void beeSettings() {
        beeBreedingChance = getDouble("mobs.bee.breeding.offspring.chance", beeBreedingChance);
        beeBreedingMinOffspring = getInt("mobs.bee.breeding.offspring.min", beeBreedingMinOffspring);
        beeBreedingMaxOffspring = getInt("mobs.bee.breeding.offspring.max", beeBreedingMaxOffspring);
    }

    public boolean boggedShouldBurnInDay = true;
    private void boggedSettings() {
        boggedShouldBurnInDay = getBoolean("mobs.bogged.should-burn-in-day", boggedShouldBurnInDay);
    }

    public boolean breezeShouldBurnInDay = false;
    private void breezeSettings() {
        breezeShouldBurnInDay = getBoolean("mobs.breeze.should-burn-in-day", breezeShouldBurnInDay);
    }

    public double camelBreedingChance = 0.0D;
    public int camelBreedingMinOffspring = 1;
    public int camelBreedingMaxOffspring = 1;
    private void camelSettings() {
        camelBreedingChance = getDouble("mobs.camel.breeding.offspring.chance", camelBreedingChance);
        camelBreedingMinOffspring = getInt("mobs.camel.breeding.offspring.min", camelBreedingMinOffspring);
        camelBreedingMaxOffspring = getInt("mobs.camel.breeding.offspring.max", camelBreedingMaxOffspring);
    }

    public boolean camelHuskShouldBurnInDay = false;
    private void camelHuskSettings() {
        camelHuskShouldBurnInDay = getBoolean("mobs.camel_husk.should-burn-in-day", camelHuskShouldBurnInDay);
    }

    public double catBreedingChance = 0.0D;
    public int catBreedingMinOffspring = 1;
    public int catBreedingMaxOffspring = 1;
    private void catSettings() {
        catBreedingChance = getDouble("mobs.cat.breeding.offspring.chance", catBreedingChance);
        catBreedingMinOffspring = getInt("mobs.cat.breeding.offspring.min", catBreedingMinOffspring);
        catBreedingMaxOffspring = getInt("mobs.cat.breeding.offspring.max", catBreedingMaxOffspring);
    }

    public boolean caveSpiderShouldBurnInDay = false;
    private void caveSpiderSettings() {
        caveSpiderShouldBurnInDay = getBoolean("mobs.cave_spider.should-burn-in-day", caveSpiderShouldBurnInDay);
    }

    public double chickenBreedingChance = 0.0D;
    public int chickenBreedingMinOffspring = 1;
    public int chickenBreedingMaxOffspring = 1;
    private void chickenSettings() {
        chickenBreedingChance = getDouble("mobs.chicken.breeding.offspring.chance", chickenBreedingChance);
        chickenBreedingMinOffspring = getInt("mobs.chicken.breeding.offspring.min", chickenBreedingMinOffspring);
        chickenBreedingMaxOffspring = getInt("mobs.chicken.breeding.offspring.max", chickenBreedingMaxOffspring);
    }

    public double cowBreedingChance = 0.0D;
    public int cowBreedingMinOffspring = 1;
    public int cowBreedingMaxOffspring = 1;
    private void cowSettings() {
        cowBreedingChance = getDouble("mobs.cow.breeding.offspring.chance", cowBreedingChance);
        cowBreedingMinOffspring = getInt("mobs.cow.breeding.offspring.min", cowBreedingMinOffspring);
        cowBreedingMaxOffspring = getInt("mobs.cow.breeding.offspring.max", cowBreedingMaxOffspring);
    }

    public boolean creeperShouldBurnInDay = false;
    private void creeperSettings() {
        creeperShouldBurnInDay = getBoolean("mobs.creeper.should-burn-in-day", creeperShouldBurnInDay);
    }

    public double donkeyBreedingChance = 0.0D;
    public int donkeyBreedingMinOffspring = 1;
    public int donkeyBreedingMaxOffspring = 1;
    private void donkeySettings() {
        donkeyBreedingChance = getDouble("mobs.donkey.breeding.offspring.chance", donkeyBreedingChance);
        donkeyBreedingMinOffspring = getInt("mobs.donkey.breeding.offspring.min", donkeyBreedingMinOffspring);
        donkeyBreedingMaxOffspring = getInt("mobs.donkey.breeding.offspring.max", donkeyBreedingMaxOffspring);
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

    public double foxBreedingChance = 0.0D;
    public int foxBreedingMinOffspring = 1;
    public int foxBreedingMaxOffspring = 1;
    private void foxSettings() {
        foxBreedingChance = getDouble("mobs.fox.breeding.offspring.chance", foxBreedingChance);
        foxBreedingMinOffspring = getInt("mobs.fox.breeding.offspring.min", foxBreedingMinOffspring);
        foxBreedingMaxOffspring = getInt("mobs.fox.breeding.offspring.max", foxBreedingMaxOffspring);
    }

    public double frogBreedingChance = 1.0D;
    public int frogBreedingMinOffspring = FrogspawnBlock.MIN_TADPOLES_SPAWN;
    public int frogBreedingMaxOffspring = FrogspawnBlock.MAX_TADPOLES_SPAWN;
    private void frogSettings() {
        frogBreedingChance = getDouble("mobs.frog.breeding.offspring.chance", frogBreedingChance);
        frogBreedingMinOffspring = getInt("mobs.frog.breeding.offspring.min", frogBreedingMinOffspring);
        frogBreedingMaxOffspring = getInt("mobs.frog.breeding.offspring.max", frogBreedingMaxOffspring);
    }

    public double goatBreedingChance = 0.0D;
    public int goatBreedingMinOffspring = 1;
    public int goatBreedingMaxOffspring = 1;
    private void goatSettings() {
        goatBreedingChance = getDouble("mobs.goat.breeding.offspring.chance", goatBreedingChance);
        goatBreedingMinOffspring = getInt("mobs.goat.breeding.offspring.min", goatBreedingMinOffspring);
        goatBreedingMaxOffspring = getInt("mobs.goat.breeding.offspring.max", goatBreedingMaxOffspring);
    }

    public boolean guardianShouldBurnInDay = false;
    private void guardianSettings() {
        guardianShouldBurnInDay = getBoolean("mobs.guardian.should-burn-in-day", guardianShouldBurnInDay);
    }

    public double hoglinBreedingChance = 0.0D;
    public int hoglinBreedingMinOffspring = 1;
    public int hoglinBreedingMaxOffspring = 1;
    private void hoglinSettings() {
        hoglinBreedingChance = getDouble("mobs.hoglin.breeding.offspring.chance", hoglinBreedingChance);
        hoglinBreedingMinOffspring = getInt("mobs.hoglin.breeding.offspring.min", hoglinBreedingMinOffspring);
        hoglinBreedingMaxOffspring = getInt("mobs.hoglin.breeding.offspring.max", hoglinBreedingMaxOffspring);
    }

    public double horseBreedingChance = 0.0D;
    public int horseBreedingMinOffspring = 1;
    public int horseBreedingMaxOffspring = 1;
    private void horseSettings() {
        horseBreedingChance = getDouble("mobs.horse.breeding.offspring.chance", horseBreedingChance);
        horseBreedingMinOffspring = getInt("mobs.horse.breeding.offspring.min", horseBreedingMinOffspring);
        horseBreedingMaxOffspring = getInt("mobs.horse.breeding.offspring.max", horseBreedingMaxOffspring);
    }

    public boolean huskShouldBurnInDay = false;
    private void huskSettings() {
        huskShouldBurnInDay = getBoolean("mobs.husk.should-burn-in-day", huskShouldBurnInDay);
    }

    public boolean illusionerSpawnInRaids = false;
    private void illusionerSettings() {
        illusionerSpawnInRaids = getBoolean("mobs.illusioner.spawn-in-raids", illusionerSpawnInRaids);
    }

    public double llamaBreedingChance = 0.0D;
    public int llamaBreedingMinOffspring = 1;
    public int llamaBreedingMaxOffspring = 1;
    private void llamaSettings() {
        llamaBreedingChance = getDouble("mobs.llama.breeding.offspring.chance", llamaBreedingChance);
        llamaBreedingMinOffspring = getInt("mobs.llama.breeding.offspring.min", llamaBreedingMinOffspring);
        llamaBreedingMaxOffspring = getInt("mobs.llama.breeding.offspring.max", llamaBreedingMaxOffspring);
    }

    public double mooshroomBreedingChance = 0.0D;
    public int mooshroomBreedingMinOffspring = 1;
    public int mooshroomBreedingMaxOffspring = 1;
    private void mooshroomSettings() {
        mooshroomBreedingChance = getDouble("mobs.mooshroom.breeding.offspring.chance", mooshroomBreedingChance);
        mooshroomBreedingMinOffspring = getInt("mobs.mooshroom.breeding.offspring.min", mooshroomBreedingMinOffspring);
        mooshroomBreedingMaxOffspring = getInt("mobs.mooshroom.breeding.offspring.max", mooshroomBreedingMaxOffspring);
    }

    public double nautilusBreedingChance = 0.0D;
    public int nautilusBreedingMinOffspring = 1;
    public int nautilusBreedingMaxOffspring = 1;
    private void nautilusSettings() {
        nautilusBreedingChance = getDouble("mobs.nautilus.breeding.offspring.chance", nautilusBreedingChance);
        nautilusBreedingMinOffspring = getInt("mobs.nautilus.breeding.offspring.min", nautilusBreedingMinOffspring);
        nautilusBreedingMaxOffspring = getInt("mobs.nautilus.breeding.offspring.max", nautilusBreedingMaxOffspring);
    }

    public double ocelotBreedingChance = 0.0D;
    public int ocelotBreedingMinOffspring = 1;
    public int ocelotBreedingMaxOffspring = 1;
    private void ocelotSettings() {
        ocelotBreedingChance = getDouble("mobs.ocelot.breeding.offspring.chance", ocelotBreedingChance);
        ocelotBreedingMinOffspring = getInt("mobs.ocelot.breeding.offspring.min", ocelotBreedingMinOffspring);
        ocelotBreedingMaxOffspring = getInt("mobs.ocelot.breeding.offspring.max", ocelotBreedingMaxOffspring);
    }

    public double pandaBreedingChance = 0.0D;
    public int pandaBreedingMinOffspring = 1;
    public int pandaBreedingMaxOffspring = 1;
    private void pandaSettings() {
        pandaBreedingChance = getDouble("mobs.panda.breeding.offspring.chance", pandaBreedingChance);
        pandaBreedingMinOffspring = getInt("mobs.panda.breeding.offspring.min", pandaBreedingMinOffspring);
        pandaBreedingMaxOffspring = getInt("mobs.panda.breeding.offspring.max", pandaBreedingMaxOffspring);
    }

    public boolean parchedShouldBurnInDay = false;
    private void parchedSettings() {
        parchedShouldBurnInDay = getBoolean("mobs.parched.should-burn-in-day", parchedShouldBurnInDay);
    }

    public boolean phantomShouldBurnInDay = true;
    private void phantomSettings() {
        phantomShouldBurnInDay = getBoolean("mobs.phantom.should-burn-in-day", phantomShouldBurnInDay);
    }

    public double pigBreedingChance = 0.0D;
    public int pigBreedingMinOffspring = 1;
    public int pigBreedingMaxOffspring = 1;
    private void pigSettings() {
        pigBreedingChance = getDouble("mobs.pig.breeding.offspring.chance", pigBreedingChance);
        pigBreedingMinOffspring = getInt("mobs.pig.breeding.offspring.min", pigBreedingMinOffspring);
        pigBreedingMaxOffspring = getInt("mobs.pig.breeding.offspring.max", pigBreedingMaxOffspring);
    }

    public double rabbitBreedingChance = 0.0D;
    public int rabbitBreedingMinOffspring = 1;
    public int rabbitBreedingMaxOffspring = 1;
    private void rabbitSettings() {
        rabbitBreedingChance = getDouble("mobs.rabbit.breeding.offspring.chance", rabbitBreedingChance);
        rabbitBreedingMinOffspring = getInt("mobs.rabbit.breeding.offspring.min", rabbitBreedingMinOffspring);
        rabbitBreedingMaxOffspring = getInt("mobs.rabbit.breeding.offspring.max", rabbitBreedingMaxOffspring);
    }

    public double sheepBreedingChance = 0.0D;
    public int sheepBreedingMinOffspring = 1;
    public int sheepBreedingMaxOffspring = 1;
    private void sheepSettings() {
        sheepBreedingChance = getDouble("mobs.sheep.breeding.offspring.chance", sheepBreedingChance);
        sheepBreedingMinOffspring = getInt("mobs.sheep.breeding.offspring.min", sheepBreedingMinOffspring);
        sheepBreedingMaxOffspring = getInt("mobs.sheep.breeding.offspring.max", sheepBreedingMaxOffspring);
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

    public double striderBreedingChance = 0.0D;
    public int striderBreedingMinOffspring = 1;
    public int striderBreedingMaxOffspring = 1;
    private void striderSettings() {
        striderBreedingChance = getDouble("mobs.strider.breeding.offspring.chance", striderBreedingChance);
        striderBreedingMinOffspring = getInt("mobs.strider.breeding.offspring.min", striderBreedingMinOffspring);
        striderBreedingMaxOffspring = getInt("mobs.strider.breeding.offspring.max", striderBreedingMaxOffspring);
    }

    public boolean sulfurCubeShouldBurnInDay = false;
    private void sulfurCubeSettings() {
        sulfurCubeShouldBurnInDay = getBoolean("mobs.sulfur_cube.should-burn-in-day", sulfurCubeShouldBurnInDay);
    }

    public double traderLlamaBreedingChance = 0.0D;
    public int traderLlamaBreedingMinOffspring = 1;
    public int traderLlamaBreedingMaxOffspring = 1;
    private void traderLlamaSettings() {
        traderLlamaBreedingChance = getDouble("mobs.trader_llama.breeding.offspring.chance", traderLlamaBreedingChance);
        traderLlamaBreedingMinOffspring = getInt("mobs.trader_llama.breeding.offspring.min", traderLlamaBreedingMinOffspring);
        traderLlamaBreedingMaxOffspring = getInt("mobs.trader_llama.breeding.offspring.max", traderLlamaBreedingMaxOffspring);
    }

    public double turtleBreedingChance = 0.0D;
    public int turtleBreedingMinOffspring = 1;
    public int turtleBreedingMaxOffspring = 1;
    private void turtleSettings() {
        turtleBreedingChance = getDouble("mobs.turtle.breeding.offspring.chance", turtleBreedingChance);
        turtleBreedingMinOffspring = getInt("mobs.turtle.breeding.offspring.min", turtleBreedingMinOffspring);
        turtleBreedingMaxOffspring = getInt("mobs.turtle.breeding.offspring.max", turtleBreedingMaxOffspring);
    }

    public boolean vexShouldBurnInDay = false;
    private void vexSettings() {
        vexShouldBurnInDay = getBoolean("mobs.vex.should-burn-in-day", vexShouldBurnInDay);
    }

    public double villagerBreedingChance = 0.0D;
    public int villagerBreedingMinOffspring = 1;
    public int villagerBreedingMaxOffspring = 1;
    private void villagerSettings() {
        villagerBreedingChance = getDouble("mobs.villager.breeding.offspring.chance", villagerBreedingChance);
        villagerBreedingMinOffspring = getInt("mobs.villager.breeding.offspring.min", villagerBreedingMinOffspring);
        villagerBreedingMaxOffspring = getInt("mobs.villager.breeding.offspring.max", villagerBreedingMaxOffspring);
    }

    public boolean witchShouldBurnInDay = false;
    private void witchSettings() {
        witchShouldBurnInDay = getBoolean("mobs.witch.should-burn-in-day", witchShouldBurnInDay);
    }

    public double wolfBreedingChance = 0.0D;
    public int wolfBreedingMinOffspring = 1;
    public int wolfBreedingMaxOffspring = 1;
    private void wolfSettings() {
        wolfBreedingChance = getDouble("mobs.wolf.breeding.offspring.chance", wolfBreedingChance);
        wolfBreedingMinOffspring = getInt("mobs.wolf.breeding.offspring.min", wolfBreedingMinOffspring);
        wolfBreedingMaxOffspring = getInt("mobs.wolf.breeding.offspring.max", wolfBreedingMaxOffspring);
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
