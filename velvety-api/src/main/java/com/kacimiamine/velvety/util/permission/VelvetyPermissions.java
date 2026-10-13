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

        DefaultPermissions.registerPermission(PREFIX + "one-hit-kill-creative", "Gives the user the ability to one hit kill any entity when creative", PermissionDefault.OP, velvety);

        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.1", "Allows the user to have an enderchest size of 1 row", PermissionDefault.OP, velvety);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.2", "Allows the user to have an enderchest size of 2 rows", PermissionDefault.OP, velvety);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.3", "Allows the user to have an enderchest size of 3 rows", PermissionDefault.OP, velvety);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.4", "Allows the user to have an enderchest size of 4 rows", PermissionDefault.OP, velvety);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.5", "Allows the user to have an enderchest size of 5 rows", PermissionDefault.OP, velvety);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.6", "Allows the user to have an enderchest size of 6 rows", PermissionDefault.OP, velvety);

        velvety.recalculatePermissibles();
        return velvety;
    }
}
