package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Enums.TextColor;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.entity.EntityDeathEvent;

public class SelfDestruction extends CustomEnchantment {
    public SelfDestruction() {
        super("selfdestruction", "Self-Destruction");
        setTargetItems(EnchantmentTarget.ARMOR);
        maxLevel = 5;
        cursed = true;
        loreColor = TextColor.CURSED_LORE;
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, int level) {
        event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), level, true); // Fiery explosion of radius = level
        event.getDrops().removeIf(item -> item != null && item.getItemMeta().getEnchantLevel(this) != 0);
    }
}
