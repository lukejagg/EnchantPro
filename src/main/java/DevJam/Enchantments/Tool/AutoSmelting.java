package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Enums.EquipmentType;
import DevJam.Generated.IdealToolsGenerated;
import DevJam.Util.ItemUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class AutoSmelting extends CustomEnchantment {
    public AutoSmelting() {
        super("auto_smelting", "Auto-Smelting");
        setTargetItems(CustomEnchantmentTarget.TOOL);
        conflicts.add(Enchantment.SILK_TOUCH);
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, int level) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack heldItem = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();

        if (event.isDropItems() && !player.getGameMode().equals(GameMode.CREATIVE) && IdealToolsGenerated.IDEAL_TOOLS.get(block.getType()) == EquipmentType.fromItemStack(heldItem)) {
            event.setDropItems(false);

            for (ItemStack drop : block.getDrops(heldItem, player)) {
                ItemStack smelted = ItemUtil.getSmelted(drop);
                if (smelted.getType().equals(Material.AIR)) continue;
                block.getWorld().dropItemNaturally(block.getLocation(), smelted);
            }
        }
    }
}
