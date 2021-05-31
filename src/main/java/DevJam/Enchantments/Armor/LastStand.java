package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class LastStand extends CustomEnchantment {
    public LastStand() {
        super("laststand", "Last Stand");
        setTargetItems(EnchantmentTarget.ARMOR);
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event, int level) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) return;

            if (player.getHealth() <= event.getFinalDamage()) { // Player will die
                int cost = (int) (event.getFinalDamage() - player.getHealth() + 0.5);

                if (player.getLevel() >= cost) {
                    event.setDamage(0);
                    player.giveExpLevels(-1 * cost);
                    player.setHealth(1);
                }
            }
        }
    }
}
