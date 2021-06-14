package DevJam.Listeners;

import DevJam.CustomEnchantment;
import DevJam.Enchantments.Irreparable;
import DevJam.Enums.EquipmentType;
import DevJam.Info;
import DevJam.Util.EnchantUtil;
import DevJam.Util.ItemUtil;
import org.bukkit.Bukkit;
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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnchantListener implements Listener {
    /* Debug for finding enchantments on item */
    /*@EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();

        Info.log("Enchants:");
        item.getItemMeta().getEnchants().forEach((f,l) -> {Info.log(f.toString());});
        Info.log("Stored Enchants:");
        EnchantmentStorageMeta meta =  ((EnchantmentStorageMeta)item.getItemMeta());
        meta.getStoredEnchants().forEach((f,l) -> {Info.log(f.toString());});
    }*/

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        ItemStack item = event.getItem();
        Map<CustomEnchantment, Integer> customEnchants = new HashMap<>();


        int level = event.getExpLevelCost();
        Info.log("Enchanting at " + level);

        ArrayList<CustomEnchantment> possibleEnchants = new ArrayList<>();
        EnchantUtil.getPossibleEnchantments(possibleEnchants, item);

        double luck = 0;

        // The higher level enchanted at, the better the luck
        // Higher luck = less chance to get next enchantment
        luck = 1.0 - level / 15.0;

        while (EnchantUtil.shouldAddEnchantment(luck)) {
            double total = 0.0;
            for (CustomEnchantment e : possibleEnchants)
                total += e.getEnchantWeight(level);

            // Todo: convert Weighted Random to use TreeMap (map.floorKey)
            CustomEnchantment enchant = null;
            double random = Math.random() * total;
            for (CustomEnchantment possibleEnchant : possibleEnchants) {
                random -= possibleEnchant.getEnchantWeight(level);
                if (random <= 0.0) {
                    enchant = possibleEnchant;
                    break;
                }
            }

            Info.log("Adding " + enchant);

            if (enchant != null) {
                customEnchants.put(enchant, EnchantUtil.getLevel(enchant, level));
                luck += enchant.getEnchantSignificance();
            }
        }

        // Apply enchantments
        Map<Enchantment, Integer> enchants = event.getEnchantsToAdd();
        customEnchants.forEach(enchants::put);

        // Update item metadata
        CustomEnchantment.updateMeta(item, enchants);

        // Add custom enchantments to enchantment storage
        // in EnchantedBooks
        // Books use EnchantmentStorageMeta for enchantments
        if (EquipmentType.fromItemStack(item) == EquipmentType.BOOK && item.getItemMeta() != null) {

            Bukkit.getScheduler().runTaskLater(Info.plugin, () -> {

                ItemStack newItem = event.getView().getTopInventory().getItem(0);
                if (newItem != null && EquipmentType.fromItemStack(newItem) == EquipmentType.ENCHANTED_BOOK) {
                    EnchantmentStorageMeta meta = (EnchantmentStorageMeta) newItem.getItemMeta();

                    if (meta == null) {
                        Info.warn("Enchanted book didn't have metadata.");
                        return;
                    }

                    // Re-add Custom Enchantments
                    customEnchants.forEach((e, l) -> meta.addStoredEnchant(e, l, true));
                    newItem.setItemMeta(meta);
                    CustomEnchantment.updateMeta(newItem);
                }
            }, 1);
        }

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
                    CustomEnchantment.updateMeta(item);
            },  1L);

            // Apply lore removal
            InventoryType.SlotType slotType = event.getSlotType();
            if (slotType == InventoryType.SlotType.RESULT && event.getCurrentItem() != null) {
                ItemStack item = event.getCurrentItem();
                CustomEnchantment.updateMeta(item);
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
            // Irreparable Enchantment
            if (item1.getItemMeta().getEnchantLevel(new Irreparable()) != 0) {
                if (result.getItemMeta() instanceof Damageable && item1.getItemMeta() instanceof Damageable) {
                    Damageable originalMeta = (Damageable) item1.getItemMeta();
                    Damageable resultMeta = (Damageable) result.getItemMeta();

                    resultMeta.setDamage(originalMeta.getDamage());
                    result.setItemMeta((ItemMeta) resultMeta);
                }
            }

            // Re-apply enchantments
            for (Enchantment ench : item1.getEnchantments().keySet()) {
                if (ench instanceof CustomEnchantment) {
                    CustomEnchantment enchantment = (CustomEnchantment)ench;
                    int level = item1.getEnchantments().get(ench);
                    result.addEnchantment(enchantment, level);
                }
            }
            CustomEnchantment.updateMeta(result);

            return;
        }

        // Make sure item2 is enchantable
        if (item2 == null || !ItemUtil.isEnchantable(item2) || item2.getAmount() > 1) return;

        // Prevent book from being in first slot
        if (EquipmentType.fromItemStack(item1) == EquipmentType.BOOK && EquipmentType.fromItemStack(item2) != EquipmentType.BOOK) return;

        if (!ItemUtil.isEnchantable(result) || result.getAmount() != 1) return;

        Map<CustomEnchantment, Integer> customEnchants = new HashMap<>();
        int cost = inv.getRepairCost();

        // Merge enchantments
        if (item1.getType() == item2.getType() || EquipmentType.fromItemStack(item2) == EquipmentType.ENCHANTED_BOOK) {
            Map<Enchantment, Integer> ench1 = item1.getEnchantments(), ench2 = item2.getEnchantments();

            if (EquipmentType.fromItemStack(item1) == EquipmentType.ENCHANTED_BOOK && item1.getItemMeta() != null)
                ench1 = ((EnchantmentStorageMeta)item1.getItemMeta()).getStoredEnchants();

            if (EquipmentType.fromItemStack(item2) == EquipmentType.ENCHANTED_BOOK && item2.getItemMeta() != null)
                ench2 = ((EnchantmentStorageMeta)item2.getItemMeta()).getStoredEnchants();

            for (Enchantment e : ench1.keySet()){
                if (e instanceof CustomEnchantment) {
                    customEnchants.put((CustomEnchantment) e, ench1.get(e));
                }
            }

            for (Enchantment e : ench2.keySet()) {
                if (e instanceof CustomEnchantment) {
                    CustomEnchantment enchant = (CustomEnchantment) e;
                    int level = ench2.get(e);

                    // Has the same enchant, try combining
                    if (customEnchants.containsKey(enchant)) {
                        int originalLevel = customEnchants.get(enchant);

                        if (level > originalLevel) {
                            // Overwrite level
                            customEnchants.put(enchant, level);
                            cost++;
                        }
                        else if (level == originalLevel && level != enchant.getMaxLevel()) {
                            // Add level to enchantment
                            customEnchants.put(enchant, level + 1);
                            cost++;
                        }

                        // Level is lower than original level
                    }
                    else {
                        if (CustomEnchantment.canAddEnchantment(enchant, ench1))
                            customEnchants.put(enchant, level);
                    }
                }
            }
        }

        if (EquipmentType.fromItemStack(result) == EquipmentType.ENCHANTED_BOOK) {
            // todo: EnchantmentStorageMeta
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) result.getItemMeta();

            if (meta == null)
                return;

            for (CustomEnchantment e : customEnchants.keySet()) {
                int level = customEnchants.get(e);
                meta.addStoredEnchant(e, level, true);
            }

            result.setItemMeta(meta);
        }
        else {
            for (CustomEnchantment e : customEnchants.keySet()) {
                int level = customEnchants.get(e);
                result.addEnchantment(e, level);
            }
        }

        // Irreparable Enchantment
        if (item1.getItemMeta().getEnchantLevel(new Irreparable()) != 0) {
            if (result.getItemMeta() instanceof Damageable && item1.getItemMeta() instanceof Damageable) {
                Damageable originalMeta = (Damageable) item1.getItemMeta();
                Damageable resultMeta = (Damageable) result.getItemMeta();

                resultMeta.setDamage(originalMeta.getDamage());
                result.setItemMeta((ItemMeta) resultMeta);
            }
        }

        CustomEnchantment.updateMeta(result);
        inv.setRepairCost(cost);
    }
}