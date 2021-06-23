package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

public class X_DivineTouch extends CustomEnchantment {
    public X_DivineTouch() {
        super("divine_touch", "Divine Touch");
        setTargetItems(CustomEnchantmentTarget.TOOL);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
