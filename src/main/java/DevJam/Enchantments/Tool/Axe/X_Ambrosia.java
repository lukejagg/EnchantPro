package DevJam.Enchantments.Tool.Axe;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;

import java.util.Random;

public class X_Ambrosia extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public X_Ambrosia() {
        super("ambrosia", "Ambrosia");
        setTargetItems(CustomEnchantmentTarget.AXE);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
