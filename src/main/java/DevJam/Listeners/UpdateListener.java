package DevJam.Listeners;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import DevJam.Info;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Technically is task, not a listener
 * "Listens" for tick updates
 */
public class UpdateListener implements Runnable {
    private int currentTick = 0;

    /**
     * Called every tick.
     */
    public void run() {
        // Execute every task
        updateEquipment();

        currentTick++;
        //Bukkit.getPluginManager().callEvent(updateEvent);
    }

    /**
     * Applies passive ability to all enchanted armor. Needs refactoring.
     */
    private void updateEquipment() {
        for (World world : Info.plugin.getServer().getWorlds()) {
            for (LivingEntity entity : world.getLivingEntities()) {
                EntityEquipment equipment = entity.getEquipment();
                if (equipment == null) continue;

                applyItemEnchantments(entity, equipment.getHelmet(), EquipmentSlot.HEAD);
                applyItemEnchantments(entity, equipment.getChestplate(), EquipmentSlot.CHEST);
                applyItemEnchantments(entity, equipment.getLeggings(), EquipmentSlot.LEGS);
                applyItemEnchantments(entity, equipment.getBoots(), EquipmentSlot.FEET);
                applyItemEnchantments(entity, equipment.getItemInMainHand(), EquipmentSlot.HAND);
                applyItemEnchantments(entity, equipment.getItemInOffHand(), EquipmentSlot.OFF_HAND);
            }
        }
    }

    /**
     * Update enchantment on item
     * @param entity entity that has this item
     * @param item enchanted item
     * @param slot slot the item is in
     */
    private void applyItemEnchantments(LivingEntity entity, ItemStack item, EquipmentSlot slot) {
        if (item == null) return;

        Map<Enchantment, Integer> enchantments = item.getEnchantments();

        for (Enchantment enchant : enchantments.keySet()) {
            if (enchant instanceof CustomEnchantment) {
                CustomEnchantment enchantment = (CustomEnchantment) enchant;

                if (enchantment.canUpdate(currentTick) && enchantment.canUse(slot)) {
                    int level = enchantments.get(enchantment);
                    enchantment.update(new UpdateItemEvent(entity, item, level, slot));
                }
            }
        }
    }
}