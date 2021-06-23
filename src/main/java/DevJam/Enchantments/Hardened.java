package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class Hardened extends CustomEnchantment {
    private static final Random random = new Random();
    private static double rejuvenateChance;

    public Hardened() {
        super("hardened", "Hardened");
        setTargetItems(EnchantmentTarget.BREAKABLE);
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
