package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_Laser extends CustomEnchantment {
    public X_Laser() {
        super("laser", "Laser");
        setTargetItems(CustomEnchantmentTarget.TOOL);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
