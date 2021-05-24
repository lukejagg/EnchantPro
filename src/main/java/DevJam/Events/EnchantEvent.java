package DevJam.Events;

import DevJam.CustomEnchantment;
import DevJam.Enchantments.Test;
import DevJam.Info;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

public class EnchantEvent implements Listener {
    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        ItemStack item = event.getItem();
        Map<Enchantment, Integer> enchants = event.getEnchantsToAdd();

        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            int level = rnd.nextInt(5) + 1;
            Info.plugin.getLogger().info("Enchanted with level " + level);
            enchants.put(new Test(), level);
        }

        CustomEnchantment.apply(item, enchants);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getSlotType() == InventoryType.SlotType.RESULT) {
            InventoryType type = event.getClickedInventory().getType();
            if (type == InventoryType.GRINDSTONE || type == InventoryType.ANVIL) {
                ItemStack item = event.getCurrentItem();
                CustomEnchantment.apply(item);

                for (Enchantment e : item.getEnchantments().keySet()) {
                    Info.log(e.toString());
                }
            }
        }
    }
}
