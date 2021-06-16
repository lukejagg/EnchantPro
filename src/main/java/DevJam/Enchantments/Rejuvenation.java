package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class Rejuvenation extends CustomEnchantment {
    private static final Random random = new Random();
    private static double rejuvenateChance;

    public Rejuvenation() {
        super("rejuvenation", "Rejuvenation");
        setTargetItems(EnchantmentTarget.BREAKABLE);
        updateDelay = 20;
        rejuvenateChance = updateDelay / (24.0 * 60 * 60 * 20 / 429); // Defined to fully repair diamond boots after 24 hours on average
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        if (event.item.getItemMeta() instanceof Damageable && random.nextDouble() < rejuvenateChance) {
            Damageable meta = (Damageable) event.item.getItemMeta();
            if (meta.hasDamage()) {
                meta.setDamage(meta.getDamage() - 1);
                event.item.setItemMeta((ItemMeta) meta);
            }
        }
    }
}
