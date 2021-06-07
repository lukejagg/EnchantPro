package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.util.Random;

public class WellFed extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public WellFed() {
        super("wellfed", "Well-Fed");
        setTargetItems(EnchantmentTarget.ARMOR);
        maxLevel = 3;
    }

    @Override
    public void onFoodLevelChange(FoodLevelChangeEvent event, int level) {
        if (RANDOM.nextDouble() < 0.25 * level && event.getFoodLevel() < event.getEntity().getFoodLevel()) {
            event.setCancelled(true);
        }
    }
}
