package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class PigGod extends CustomEnchantment {
    public PigGod() {
        super("piggod", "Pig God");
        setTargetItems(EnchantmentTarget.ARMOR_HEAD);
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event, int level) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.getHealth() <= event.getFinalDamage()) { // Player will die
                int cost = (int) (event.getFinalDamage() - player.getHealth() + 0.5);

                if (player.getFoodLevel() >= cost) {
                    event.setDamage(0);
                    player.setFoodLevel(player.getFoodLevel() - cost);
                    player.setHealth(1);
                }
            }
        }
    }
}
