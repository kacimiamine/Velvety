package com.kacimiamine.velvety.block;

import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;

public class EnderChestUtil {

    @SuppressWarnings("unchecked")
    public static final MenuType<ChestMenu>[] TYPES = new MenuType[] {
        MenuType.GENERIC_9x1, MenuType.GENERIC_9x2, MenuType.GENERIC_9x3,
        MenuType.GENERIC_9x4, MenuType.GENERIC_9x5, MenuType.GENERIC_9x6
    };
}
