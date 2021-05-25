package DevJam.Listeners;

import DevJam.CustomEnchantment;
import DevJam.Enchantments.Test;
import DevJam.Info;
import DevJam.Util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

public class EnchantListener implements Listener {
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
    public void onInventoryClick(final InventoryClickEvent event) {
        InventoryType inventoryType = event.getView().getTopInventory().getType();
        if (inventoryType == InventoryType.GRINDSTONE) {
            // Remove lore from preview item
            Info.plugin.getServer().getScheduler().runTaskLater(Info.plugin, () -> {
                GrindstoneInventory inv = (GrindstoneInventory)event.getView().getTopInventory();
                ItemStack item = inv.getItem(2);
                if (item != null)
                    CustomEnchantment.apply(item);
            },  1L);

            // Apply lore removal
            InventoryType.SlotType slotType = event.getSlotType();
            if (slotType == InventoryType.SlotType.RESULT && event.getCurrentItem() != null) {
                ItemStack item = event.getCurrentItem();
                CustomEnchantment.apply(item);
            }
        }
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory inv = event.getInventory();
        ItemStack item1 = inv.getItem(0);
        ItemStack item2 = inv.getItem(1);
        ItemStack result = event.getResult();

        // Make sure item1 is enchantable
        if (item1 == null || item1.getAmount() > 1 || !ItemUtil.isEnchantable(item1) || result == null) return;

        // Repairing, renaming
        if ((item2 == null || item2.getType() == Material.AIR || !ItemUtil.isEnchantable(item2)) && result.getType() == item1.getType()) {
            // Re-apply enchantments
            for (Enchantment ench : item1.getEnchantments().keySet()) {
                if (ench instanceof CustomEnchantment) {
                    CustomEnchantment enchantment = (CustomEnchantment)ench;
                    int level = item1.getEnchantments().get(ench);
                    result.addEnchantment(enchantment, level);
                }
            }
            CustomEnchantment.apply(result);

            return;
        }

        // Make sure item2 is enchantable
        if (!ItemUtil.isEnchantable(item2) || item2.getAmount() > 1)return;

        // Prevent book from being in first slot
        if (ItemUtil.isBook(item1) && !ItemUtil.isBook(item2)) return;

        // Merge enchantments
        if (item1.getType() == item2.getType() || ItemUtil.isBook(item2)) {
            Map<Enchantment, Integer> ench1 = item1.getEnchantments(), ench2 = item2.getEnchantments();

            for (Enchantment e : ench1.keySet()) {

            }
        }
    }
}