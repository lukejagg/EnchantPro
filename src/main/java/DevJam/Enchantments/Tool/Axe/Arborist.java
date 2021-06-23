package DevJam.Enchantments.Tool.Axe;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Util.ItemUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class Arborist extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public Arborist() {
        super("arborist", "Arborist");
        maxLevel = 3;
        setTargetItems(CustomEnchantmentTarget.AXE);
        conflicts.add(Enchantment.SILK_TOUCH);
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, int level) { // TODO
        /*Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack heldItem = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();

        if (event.isDropItems() && !player.getGameMode().equals(GameMode.CREATIVE) && ItemUtil.LEAVES.test(new ItemStack(block.getType()))) {
            for (ItemStack drop : block.getDrops(heldItem, player)) {
                if (RANDOM.nextDouble() < level / 3.0) {
                    block.getWorld().dropItemNaturally(block.getLocation(), drop); // double items
                }
            }
        }*/
    }
}
