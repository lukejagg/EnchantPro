package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Enums.TextColor;
import DevJam.Info;
import org.bukkit.World;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

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
