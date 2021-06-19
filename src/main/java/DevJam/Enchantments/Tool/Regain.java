package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Util.ItemUtil;
import org.bukkit.block.Block;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Regain extends CustomEnchantment {
    public Regain() {
        super("regain", "Regain");
        setTargetItems(EnchantmentTarget.TOOL);
        maxLevel = 3;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, int level) {
        EntityEquipment equipment = event.getPlayer().getEquipment();
        Block block = event.getBlock();
        if (equipment == null) return;
        ItemStack heldItem = equipment.getItemInMainHand();
        if (heldItem.getItemMeta() instanceof Damageable && ItemUtil.getRawMaterialForTool(heldItem).test(new ItemStack(block.getType()))) { // Held item is damageable and the block is of the raw material of the tool
            Damageable meta = (Damageable) heldItem.getItemMeta();
            meta.setDamage(Math.max(0, meta.getDamage() - level - 1));
            heldItem.setItemMeta((ItemMeta) meta);
        }
    }
}
