package DevJam.Util;

import DevJam.CustomEnchantment;
import DevJam.EnchantManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class EnchantUtil {
    final static Random rnd = new Random();

    /**
     * How slowly the significance reduces the chances of getting enchantments
     */
    final static double positiveDecay = 10, negativeGrowth = 4;

    public static boolean shouldAddEnchantment(double luck) {
        double chance = 0.5;

        if (luck > 0)
            chance = (1 - Math.tanh(luck / positiveDecay)) / 2.0;
        if (luck < 0)
            chance = (1 - Math.tanh(luck / negativeGrowth)) / 2.0;

        return rnd.nextDouble() < chance;
    }

    public static void getPossibleEnchantments(ArrayList<CustomEnchantment> enchants, ItemStack item) {
        if (item.getType() == Material.BOOK) {
            enchants.addAll(EnchantManager.getEnchants());
        }
        else {
            for (CustomEnchantment ench : EnchantManager.getEnchants()) {
                if (ench.canEnchantItem(item)) {
                    enchants.add(ench);
                }
            }
        }
    }

    public static int getLevel(CustomEnchantment ench, int enchLevel) {
        double enchProgress = (enchLevel - 1.0) / 29.0;
        double targetLevel = 1 + (ench.getMaxLevel() - ench.getEnchantLevelBias() - 1) * enchProgress;
        int level = (int)Math.round(targetLevel + Math.random() * ench.getEnchantLevelSpread());

        // Clamp level range
        if (level < 1)
            level = 1;
        if (level > ench.getMaxLevel())
            level = ench.getMaxLevel();

        return level;
    }
}
