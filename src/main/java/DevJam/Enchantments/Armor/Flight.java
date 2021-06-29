package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.GameMode;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;
import java.util.HashSet;

public class Flight extends CustomEnchantment {
    public static HashMap<Player, Integer> FlyingPlayers = new HashMap<>();

    public Flight() {
        super("flight", "Flight");
        enchantData = new EnchantData(1, 25, 2, 0, 0);
        setTargetItems(CustomEnchantmentTarget.ARMOR);
        updateDelay = 10;
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        if (event.entity instanceof Player) {
            Player player = (Player) event.entity;

            if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
                //player.setAllowFlight(true);
                return;
            }

            // Only remove food every 4 updates
            FlyingPlayers.put(player, FlyingPlayers.getOrDefault(player, 0) + 1);

            if (FlyingPlayers.get(player) % 4 == 0) {
                if (player.getFoodLevel() > 0) {
                    player.setAllowFlight(true);

                    if (player.isFlying())
                        player.setFoodLevel(player.getFoodLevel() - 1);
                } else {
                    player.setAllowFlight(false);
                }
            }
        }
    }

    @Override
    public void onFlyToggled(PlayerToggleFlightEvent event, int level) {

    }
}
