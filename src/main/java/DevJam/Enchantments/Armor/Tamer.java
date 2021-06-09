package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Util.EntityUtil;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Tamer extends CustomEnchantment {
    public Tamer() {
        super("tamer", "Tamer");
        setTargetItems(EnchantmentTarget.ARMOR);
    }

    @Override
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event, int level) {
        ItemStack mainItem = event.getPlayer().getInventory().getItemInMainHand();
        ItemStack offItem = event.getPlayer().getInventory().getItemInOffHand();
        Entity entity = event.getRightClicked();

        if (entity instanceof Tameable) {
            Tameable animal = (Tameable) entity;

            if (!animal.isTamed() && (EntityUtil.canTame(animal.getType(), mainItem) || EntityUtil.canTame(animal.getType(), offItem))) {
                animal.setOwner(event.getPlayer());
                Bukkit.getServer().getPluginManager().callEvent(new EntityTameEvent(animal, event.getPlayer())); // Compatibility with Beastmaster enchantment
            }
        }
    }
}
