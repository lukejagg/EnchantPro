package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import DevJam.Info;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class Flight extends CustomEnchantment {
    public Flight() {
        super("flight", "Flight");
        setTargetItems(EnchantmentTarget.ARMOR);
        updateDelay = 12;
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        if (event.entity instanceof Player) {
            Player player = (Player) event.entity;

            if (player.getFoodLevel() > 0) {
                player.setAllowFlight(true);

                if (player.isFlying())
                    player.setFoodLevel(player.getFoodLevel() - 1);
            }
            else {
                player.setAllowFlight(false);
            }
        }
    }

    @Override
    public void onFlyToggled(PlayerToggleFlightEvent event, int level) {

    }
}
