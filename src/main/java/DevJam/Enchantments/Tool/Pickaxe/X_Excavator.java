package DevJam.Enchantments.Tool.Pickaxe;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_Excavator extends CustomEnchantment {
    public X_Excavator() {
        super("excavator", "Excavator");
        setTargetItems(CustomEnchantmentTarget.PICKAXE);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
