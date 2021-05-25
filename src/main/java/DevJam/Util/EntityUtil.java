package DevJam.Util;

import DevJam.Info;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EntityUtil {
    public static ArrayList<ItemStack> getEquipment(Entity entity) {
        return null;
    }

    /**
     * Gets all living entities in all worlds on the server
     * @return an ArrayList of all living entities
     */
    public static ArrayList<LivingEntity> getLivingEntities() {
        ArrayList<LivingEntity> livingEntities = new ArrayList<LivingEntity>();

        for (World world : Info.plugin.getServer().getWorlds()) {
            livingEntities.addAll(world.getLivingEntities());
        }

        return livingEntities;
    }
}
