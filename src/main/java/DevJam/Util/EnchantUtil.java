package DevJam.Util;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EnchantUtil {
    /**
     * Adds enchantment, but doesn't apply meta
     * Doesn't check if the enchantment can be added
     * @param item item
     * @param enchant enchantment
     * @param level level of enchantment
     */
    public static void AddEnchantment(ItemStack item, Enchantment enchant, int level) {
        if (ItemUtil.isBook(item)) {

        }
        else {
            item.getEnchantments().put(enchant, level);
        }
    }
}
