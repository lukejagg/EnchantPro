package DevJam.Enchantments.Armor.Chestplate;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Enderman extends CustomEnchantment {
    public Enderman() {
        super("enderman", "Enderman");
        setTargetItems(EnchantmentTarget.ARMOR_TORSO);
    }

    @Override
    public void onPlayerTeleport(PlayerTeleportEvent event, int level) {
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL) && event.getTo() != null) {
            event.setCancelled(true);
            event.getPlayer().teleport(event.getTo());
        }
    }
}
