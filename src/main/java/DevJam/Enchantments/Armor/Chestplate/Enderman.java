package DevJam.Enchantments.Armor.Chestplate;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Enderman extends CustomEnchantment {
    public Enderman() {
        super("enderman", "Enderman");
        setTargetItems(CustomEnchantmentTarget.CHESTPLATE);
    }

    @Override
    public void onPlayerTeleport(PlayerTeleportEvent event, int level) {
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL) && event.getTo() != null) {
            event.setCancelled(true);
            event.getPlayer().teleport(event.getTo());
        }
    }
}
