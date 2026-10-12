package com.kacimiamine.velvety.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class VillagerUtil {

    public static boolean isRespawnPlayerPoint(ServerLevel level, BlockPos pos) {
        return level.getServer().getPlayerList().getPlayers().stream().anyMatch(player ->
            player.getRespawnConfig() != null && pos.equals(player.getRespawnConfig().respawnData().pos())
        );
    }
}
