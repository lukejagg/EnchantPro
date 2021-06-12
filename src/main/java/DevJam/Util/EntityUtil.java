package DevJam.Util;

import DevJam.Info;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EntityUtil {
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

    private final static HashMap<EntityType, List<Material>> TAMING_ITEMS = new HashMap<EntityType, List<Material>>() {{
        put(EntityType.WOLF, Collections.singletonList(Material.BONE));
        put(EntityType.CAT, Arrays.asList(Material.COD, Material.SALMON)); // Tamer will allow cooked cod and salmon to tame cats (feature not bug kek)
        put(EntityType.HORSE, Arrays.asList(Material.GOLDEN_APPLE, Material.HAY_BLOCK, Material.CARROT, Material.APPLE, Material.WHEAT, Material.SUGAR, Material.BREAD)); // Tamer will allow regular carrots to tame horses, donkeys, and mules (feature not bug once again)
        put(EntityType.DONKEY, Arrays.asList(Material.GOLDEN_APPLE, Material.HAY_BLOCK, Material.CARROT, Material.APPLE, Material.WHEAT, Material.SUGAR, Material.BREAD));
        put(EntityType.MULE, Arrays.asList(Material.GOLDEN_APPLE, Material.HAY_BLOCK, Material.CARROT, Material.APPLE, Material.WHEAT, Material.SUGAR, Material.BREAD));
        put(EntityType.LLAMA, Arrays.asList(Material.GOLDEN_APPLE, Material.HAY_BLOCK, Material.CARROT, Material.APPLE, Material.WHEAT, Material.SUGAR, Material.BREAD));
        put(EntityType.PARROT, Arrays.asList(Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.WHEAT_SEEDS, Material.PUMPKIN_SEEDS, Material.COCOA_BEANS));
    }};
    /**
     * Checks if an item can tame an entity
     * @param entityType type of entity being tamed
     * @param item item taming
     * @return true if can tame, false otherwise
     */
    public static boolean canTame(EntityType entityType, ItemStack item) {
        if (!TAMING_ITEMS.containsKey(entityType)) return false;
        for (Material material : TAMING_ITEMS.get(entityType)) {
            if (material.equals(item.getType())) {
                return true;
            }
        }
        return false;
    }

    private static final HashMap<EntityType, Material> HEADS = new HashMap<EntityType, Material>(){{
        put(EntityType.PLAYER, Material.PLAYER_HEAD);
        put(EntityType.ZOMBIE, Material.ZOMBIE_HEAD);
        put(EntityType.SKELETON, Material.SKELETON_SKULL);
        put(EntityType.WITHER_SKELETON, Material.WITHER_SKELETON_SKULL);
        put(EntityType.CREEPER, Material.CREEPER_HEAD);
        put(EntityType.ENDER_DRAGON, Material.DRAGON_HEAD);
    }};
    public static Material getHead(EntityType entityType) {
        return HEADS.get(entityType);
    }

    // Entity Type Categorization
    private static final ArrayList<EntityType> ILLAGERS_AND_RAIDERS = new ArrayList<EntityType>(){{ // https://minecraft.fandom.com/wiki/Illager
        add(EntityType.EVOKER);
        add(EntityType.ILLUSIONER);
        add(EntityType.PILLAGER);
        add(EntityType.VINDICATOR);
        add(EntityType.RAVAGER);
        add(EntityType.VEX);
        add(EntityType.WITCH);
    }};
    public static boolean entityTypeIsAnIllagerOrRaider(EntityType entityType) {
        return ILLAGERS_AND_RAIDERS.contains(entityType);
    }

    private static final ArrayList<EntityType> WITHERS = new ArrayList<EntityType>(){{
        add(EntityType.WITHER_SKELETON);
        add(EntityType.WITHER);
    }};
    public static boolean entityTypeIsAWither(EntityType entityType) {
        return WITHERS.contains(entityType);
    }

    private static final ArrayList<EntityType> ENDERS = new ArrayList<EntityType>() {{
       add(EntityType.ENDERMITE);
       add(EntityType.ENDER_DRAGON);
       add(EntityType.ENDERMAN);
    }};
    public static boolean entityTypeIsAnEnder(EntityType entityType) {
        return ENDERS.contains(entityType);
    }
}
