package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_AutoBoxer extends CustomEnchantment {
    public X_AutoBoxer() {
        super("auto_boxer", "Auto-Boxer");
        setTargetItems(CustomEnchantmentTarget.TOOL);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
