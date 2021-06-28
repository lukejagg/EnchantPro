package DevJam.Enums;

import org.bukkit.permissions.Permissible;

public enum PermissionType {

    USE("enchantpro.enchant.use"), ENCHANT("enchantpro.command.enchant"), SET("enchantpro.command.set"), REMOVE("enchantpro.command.remove"), CLEAR("enchantpro.command.clear"),
    LIST("enchantpro.command.toggle"), INFO("enchantpro.command.reload"),
    REFRESH("enchantpro.command.refresh");

    private final String permission;

    PermissionType(String permission) {
        this.permission = permission;
    }

    /**
     * Checks whether the given permissible has the permission that is represented by the enum.
     *
     * @param permissible The permissible to query
     * @return True if the permission is exiting on the permissible, false otherwise
     * @since 2.0.0
     */
    public boolean hasPermission(Permissible permissible) {
        return permissible.hasPermission(permission);
    }
}