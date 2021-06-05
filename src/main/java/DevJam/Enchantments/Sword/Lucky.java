package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class Lucky extends CustomEnchantment {
    private static Random random = new Random();

    public Lucky() {
        super("lucky", "Lucky");
        setTargetItems(EnchantmentTarget.WEAPON);
        maxLevel = 5;
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, int level) {
        if (random.nextDouble() < 0.2 * level) {
            event.setDroppedExp(event.getDroppedExp() * 2);
        }
    }
}
