package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_Chickenator extends CustomEnchantment {
    public X_Chickenator() {
        super("chickenator", "Chickenator");
        setTargetItems(CustomEnchantmentTarget.SWORD);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
