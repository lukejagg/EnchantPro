package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class PigGod extends CustomEnchantment {
    public PigGod() {
        super("piggod", "Pig God");
        setTargetItems(CustomEnchantmentTarget.HELMET);
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event, int level) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.getHealth() <= event.getFinalDamage()) { // Player will die
                int cost = (int) (event.getFinalDamage() - player.getHealth() + 1);

                if (player.getFoodLevel() >= cost) {
                    event.setDamage(0);
                    player.setFoodLevel(player.getFoodLevel() - cost);
                    player.setHealth(1);
                }
            }
        }
    }
}
