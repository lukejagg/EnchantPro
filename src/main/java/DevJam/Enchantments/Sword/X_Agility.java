package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_Agility extends CustomEnchantment {
    public X_Agility() {
        super("agility", "Agility");
        setTargetItems(CustomEnchantmentTarget.SWORD);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
