package DevJam.Events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

/**
 * Used as event paramter for tick event
 */
public class UpdateItemEvent {
    public LivingEntity entity;
    public ItemStack item;
    public int level;
    public EquipmentSlot slot;

    public UpdateItemEvent(LivingEntity entity, ItemStack item, int level, EquipmentSlot slot) {
        this.entity = entity;
        this.item = item;
        this.level = level;
        this.slot = slot;
    }
}