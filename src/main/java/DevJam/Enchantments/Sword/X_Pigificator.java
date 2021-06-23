package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_Pigificator extends CustomEnchantment {
    public X_Pigificator() {
        super("pigificator", "Pigificator");
        setTargetItems(CustomEnchantmentTarget.SWORD);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
