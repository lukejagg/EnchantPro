package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Util.ItemUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class AutoSmelting extends CustomEnchantment { // TODO WIP
    public AutoSmelting() {
        super("autosmelting", "Auto-Smelting");
        setTargetItems(EnchantmentTarget.TOOL);
        conflicts.add(Enchantment.SILK_TOUCH);
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, int level) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack heldItem = player.getEquipment().getItemInMainHand();

        if (event.isDropItems() && !player.getGameMode().equals(GameMode.CREATIVE) && block.isPreferredTool(heldItem)) {
            //if ((block.getType().equals(Material.SAND) || block.getType().equals(Material.CLAY)) && !ItemUtil.isShovel(heldItem)) return; // A pickaxe is a preferred tool for sand bruh
            //if ((block.getType().equals(Material.WOOD)))

            event.setDropItems(false);

            for (ItemStack drop : block.getDrops(heldItem, player)) {
                ItemStack smelted = ItemUtil.getSmelted(drop);
                if (smelted.getType().equals(Material.AIR)) continue;
                block.getWorld().dropItemNaturally(block.getLocation(), smelted);
            }
        }
    }
}
