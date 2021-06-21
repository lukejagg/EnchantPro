package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class Flight extends CustomEnchantment {
    public Flight() {
        super("flight", "Flight");
        setTargetItems(CustomEnchantmentTarget.ARMOR);
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
