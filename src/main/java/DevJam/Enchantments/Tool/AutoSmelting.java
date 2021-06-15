package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Enums.EquipmentType;
import DevJam.Generated.IdealToolsGenerated;
import DevJam.Info;
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

        Info.log(IdealToolsGenerated.IDEAL_TOOLS.get(block.getType()).name());
        Info.log(EquipmentType.fromItemStack(heldItem).name());
        if (event.isDropItems() && !player.getGameMode().equals(GameMode.CREATIVE) && IdealToolsGenerated.IDEAL_TOOLS.get(block.getType()) == EquipmentType.fromItemStack(heldItem)) {
            Info.log("yippee");
            event.setDropItems(false);

            for (ItemStack drop : block.getDrops(heldItem, player)) {
                ItemStack smelted = ItemUtil.getSmelted(drop);
                if (smelted.getType().equals(Material.AIR)) continue;
                block.getWorld().dropItemNaturally(block.getLocation(), smelted);
            }
        }
    }
}
