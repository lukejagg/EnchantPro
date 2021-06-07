package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class DoubleStrike extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public DoubleStrike() {
        super("doublestrike", "Double Strike");
        setTargetItems(EnchantmentTarget.WEAPON);
        maxLevel = 3;
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        if (RANDOM.nextDouble() < 0.1 * level) {
            event.setDamage(event.getDamage() * 2);
        }
    }
}
