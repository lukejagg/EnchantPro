package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class DoubleStrike extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public DoubleStrike() {
        super("double_strike", "Double Strike");
        setTargetItems(CustomEnchantmentTarget.SWORD);
        maxLevel = 3;
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        if (RANDOM.nextDouble() < 0.1 * level) {
            event.setDamage(event.getDamage() * 2);
        }
    }
}
