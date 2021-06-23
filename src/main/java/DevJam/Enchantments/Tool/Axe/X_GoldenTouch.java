package DevJam.Enchantments.Tool.Axe;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class X_GoldenTouch extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public X_GoldenTouch() {
        super("golden_touch", "Golden Touch");
        setTargetItems(CustomEnchantmentTarget.AXE);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
