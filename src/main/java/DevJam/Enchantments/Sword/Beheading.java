package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Random;

public class Beheading extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public Beheading() {
        super("beheading", "Beheading");
        setTargetItems(EnchantmentTarget.WEAPON);
        maxLevel = 3;
    }

    private static final HashMap<EntityType, Material> HEADS = new HashMap<EntityType, Material>(){{
        put(EntityType.PLAYER, Material.PLAYER_HEAD);
        put(EntityType.ZOMBIE, Material.ZOMBIE_HEAD);
        put(EntityType.SKELETON, Material.SKELETON_SKULL);
        put(EntityType.WITHER_SKELETON, Material.WITHER_SKELETON_SKULL);
        put(EntityType.CREEPER, Material.CREEPER_HEAD);
        put(EntityType.ENDER_DRAGON, Material.DRAGON_HEAD);
    }};
    @Override
    public void onEntityDeath(EntityDeathEvent event, int level) {
        if (RANDOM.nextDouble() < 0.1 * level) {
            Material headType = HEADS.get(event.getEntityType());
            if (headType == null) return;
            ItemStack head = new ItemStack(headType);
            if (headType.equals(Material.PLAYER_HEAD)) {
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                meta.setOwningPlayer((OfflinePlayer) event.getEntity());
                head.setItemMeta(meta);
            }
            event.getDrops().add(head);
        }
    }
}
