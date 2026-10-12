package com.kacimiamine.velvety.util.permission;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.permissions.DefaultPermissions;
import org.jetbrains.annotations.NotNull;

public final class VelvetyPermissions {
    private static final String ROOT = "velvety";
    private static final String PREFIX = ROOT + ".";

    @NotNull
    @CanIgnoreReturnValue
    public static Permission registerPermissions() {
        Permission velvety = DefaultPermissions.registerPermission(ROOT, "Gives the user the ability to use all Velvety features", PermissionDefault.OP);

        DefaultPermissions.registerPermission(PREFIX + "command", "Gives the user access to /velvety command", PermissionDefault.OP, velvety);

        velvety.recalculatePermissibles();
        return velvety;
    }
}
