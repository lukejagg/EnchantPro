package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

public class Ethereal extends CustomEnchantment {
    public Ethereal() {
        super("ethereal", "Ethereal");
        setTargetItems(EnchantmentTarget.BREAKABLE);
    }

    @Override
    public void applyEnchant(ItemMeta meta, int level) {
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
    }
}
