package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Util.EntityUtil;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Random;

public class Beheading extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public Beheading() {
        super("beheading", "Beheading");
        setTargetItems(EnchantmentTarget.WEAPON);
        maxLevel = 3;
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, int level) {
        if (RANDOM.nextDouble() < 0.1 * level) {
            Material headType = EntityUtil.getHead(event.getEntityType());
            if (headType == null) return;
            ItemStack head = new ItemStack(headType);
            if (headType.equals(Material.PLAYER_HEAD)) {
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                if (meta == null) return;
                meta.setOwningPlayer((OfflinePlayer) event.getEntity());
                head.setItemMeta(meta);
            }
            event.getDrops().add(head);
        }
    }
}
