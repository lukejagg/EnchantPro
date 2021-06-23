package DevJam.Enchantments.Tool.Shovel;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_GoldRush extends CustomEnchantment {
    public X_GoldRush() {
        super("gold_rush", "Gold Rush");
        setTargetItems(CustomEnchantmentTarget.SHOVEL);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
