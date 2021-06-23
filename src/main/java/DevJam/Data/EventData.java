package DevJam.Data;

import org.bukkit.inventory.ItemStack;

public class EventData<T> {
    public ItemStack item;
    public int level;

    public EventData(ItemStack item, int level) {
        this.item = item;
        this.level = level;
    }
}
