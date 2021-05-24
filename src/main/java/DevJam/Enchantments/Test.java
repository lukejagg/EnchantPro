package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;

public class Test extends CustomEnchantment {
    public Test() {
        super("test", "Test Enchantment");
        maxLevel = 5;
        targetItem = EnchantmentTarget.WEAPON;
    }
}
