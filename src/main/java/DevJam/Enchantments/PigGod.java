package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class PigGod extends CustomEnchantment {
    public PigGod() {
        super("piggod", "Pig God");
        targetItem = EnchantmentTarget.ARMOR;
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event, int level) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.getHealth() <= event.getFinalDamage()) { // Player will die
                int cost = (int) (event.getFinalDamage() - player.getHealth() + 0.5);
                cost = Math.min(20, cost);

                if (player.getFoodLevel() >= cost) {
                    event.setDamage(0);
                    player.setFoodLevel(player.getFoodLevel() - cost);
                    player.setHealth(1);
                }
            }
        }
    }
}
