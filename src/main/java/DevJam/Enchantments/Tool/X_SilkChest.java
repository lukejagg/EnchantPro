package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_SilkChest extends CustomEnchantment {
    public X_SilkChest() {
        super("silk_chest", "Silk Chest");
        setTargetItems(CustomEnchantmentTarget.TOOL);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
