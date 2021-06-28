package DevJam;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WorldGuardHook {

    private static StateFlag EPLUS_FLAG;

    public static void init() {
        EPLUS_FLAG = new StateFlag("enchantpro", true);
        WorldGuard.getInstance().getFlagRegistry().register(EPLUS_FLAG);
    }

    public static boolean hasPermission(Player p, Location loc) {
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(loc.getWorld()));
        if (regionManager == null) {
            return true;
        }

        // If any regions in the given chunk deny chunk claiming, false is returned
        for (ProtectedRegion regionIn : regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(loc))) {
            StateFlag.State flagState = regionIn.getFlag(EPLUS_FLAG);
            if (flagState == StateFlag.State.DENY) {
                return false;
            }
        }
        return true;
    }
}
