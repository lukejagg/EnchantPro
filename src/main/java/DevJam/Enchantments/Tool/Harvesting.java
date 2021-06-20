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

import java.util.Objects;
import java.util.Random;

public class Harvesting extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public Harvesting() {
        super("harvesting", "Harvesting");
        setTargetItems(EnchantmentTarget.TOOL);
        conflicts.add(Enchantment.SILK_TOUCH);
        maxLevel = 3;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, int level) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack heldItem = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();

        if (event.isDropItems() && !player.getGameMode().equals(GameMode.CREATIVE)) {
            event.setDropItems(false);

            for (ItemStack drop : block.getDrops(heldItem, player)) {
                if (drop.getType() == Material.AIR) continue;
                block.getWorld().dropItemNaturally(block.getLocation(), drop);
                if (RANDOM.nextDouble() < level / 3.0 && ItemUtil.isCrop(drop)) {
                    block.getWorld().dropItemNaturally(block.getLocation(), drop); // double items
                }
            }
        }
    }
}
