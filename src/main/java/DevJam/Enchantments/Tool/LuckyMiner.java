package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class LuckyMiner extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public LuckyMiner() {
        super("lucky_miner", "Lucky Miner");
        maxLevel = 3;
        setTargetItems(CustomEnchantmentTarget.TOOL);
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, int level) {
        if (RANDOM.nextDouble() < level / 3.0) {
            event.setExpToDrop(event.getExpToDrop() * 2);
        }
    }
}
