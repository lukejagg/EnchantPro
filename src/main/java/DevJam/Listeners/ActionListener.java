package DevJam.Listeners;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Listens for actions that enchantments use
 */
public class ActionListener implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        EntityEquipment equipment = event.getEntity().getEquipment();

        for (ItemStack armorPiece : equipment.getArmorContents()) {
            if (armorPiece == null) continue;
            Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
            for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                if (enchant instanceof CustomEnchantment) {
                    int level = enchantmentIntegerMap.get(enchant);
                    CustomEnchantment enchantment = (CustomEnchantment) enchant;
                    enchantment.onFoodLevelChange(event, level);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        EntityEquipment equipment = event.getPlayer().getEquipment();

        for (ItemStack armorPiece : equipment.getArmorContents()) {
            if (armorPiece == null) continue;
            Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
            for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                if (enchant instanceof CustomEnchantment) {
                    int level = enchantmentIntegerMap.get(enchant);
                    CustomEnchantment enchantment = (CustomEnchantment) enchant;
                    enchantment.onPlayerInteractEntity(event, level);
                }
            }
        }
    }
}