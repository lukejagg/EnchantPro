package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;

@Deprecated
public class Test extends CustomEnchantment {
    public Test() { // TODO remove - temporary
        super("test", "Test Enchantment");
        maxLevel = 5;
        setTargetItems(EnchantmentTarget.WEAPON);
    }
}
