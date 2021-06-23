package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Util.ItemUtil;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class X_Reinforced extends CustomEnchantment {
    public X_Reinforced() {
        super("reinforced", "Reinforced");
        setTargetItems(CustomEnchantmentTarget.TOOL);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
