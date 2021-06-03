package DevJam;

import DevJam.Enums.TextColor;
import DevJam.Events.UpdateItemEvent;
import DevJam.Util.ItemUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public abstract class CustomEnchantment extends Enchantment {
    protected String name, keyName;
    protected int maxLevel;
    protected EnchantmentTarget[] targetItems;
    protected boolean treasure;
    protected boolean cursed;
    protected ArrayList<Enchantment> conflicts = new ArrayList<>();
    protected int updateDelay;
    protected TextColor loreColor;
    protected boolean enabled;


    public CustomEnchantment(String key, String name) {
        super(new NamespacedKey(Info.plugin, key));
        this.name = name;
        this.keyName = key;
        maxLevel = 1;
        targetItems = new EnchantmentTarget[] {EnchantmentTarget.BREAKABLE};
        treasure = false;
        cursed = false;
        updateDelay = 0;
        loreColor = TextColor.DEFAULT_LORE;
        enabled = true;

        // Config Enable
        ArrayList<String> disabledEnchants = (ArrayList<String>) Info.config.get("Disabled Enchantments");
        if (disabledEnchants != null && (disabledEnchants.contains(key) || disabledEnchants.contains(name))) {
            enabled = false;
        }
    }

    //region Getters
    public String getName() {
        return name;
    }

    public EnchantmentTarget getItemTarget() {
        return targetItems[0];
    }

    public String getKeyName() {
        return keyName;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getStartLevel() {
        return 1; // TODO confused on how this works
    }

    /*public EnchantmentTarget getItemTarget() {
        return targetItem;
    }*/

    public boolean isTreasure() {
        return treasure;
    }

    public boolean isCursed() {
        return cursed;
    }
    //endregion

    public void setTargetItems(EnchantmentTarget... targets) {
        targetItems = targets;
    }

    //region Compatibility
    public static boolean canAddEnchantment(CustomEnchantment enchantment, Map<Enchantment, Integer> enchantments) {
        return true;
    }

    /**
     * Checks if the enchantment conflicts with another enchantment (idk if this even works)
     * @param other enchantment
     * @return true if it conflicts, false if it doesn't
     */
    public boolean conflictsWith(Enchantment other) {
        return conflicts.contains(other);
    }

    // Todo: check if this is correct
    /**
     * Checks if this enchantment can be applied to the item
     * @param item item
     * @return returns if the enchantment can be applied to item
     */
    public boolean canEnchantItem(ItemStack item) {
        for (EnchantmentTarget tar : targetItems) {
            if (tar.includes(item))
                return true;
        }
        return false;
    }

    /**
     * Check if the tick is the right tick to call update
     * Makes the update() only be called every (updateDelay) ticks
     * @param currentTick current server tick
     * @return returns if update can be called on this tick
     */
    public boolean canUpdate(int currentTick) {
        return updateDelay > 0 && currentTick % updateDelay == 0;
    }

    /**
     * Can slot be used with this enchantment?
     * Used to check if task can call update()
     * @param slot equipment slot
     * @return returns true if the enchantment can be used from this item slot
     */
    public boolean canUse(EquipmentSlot slot) {
        boolean ret = false;
        for (EnchantmentTarget targetItem : targetItems) {
            switch (targetItem) {
                case WEARABLE:
                case ARMOR:
                case ARMOR_FEET:
                case ARMOR_LEGS:
                case ARMOR_TORSO:
                case ARMOR_HEAD:
                    ret |= ItemUtil.slotIsArmor(slot);
                    break;
                case WEAPON:
                case TOOL:
                case BOW:
                case FISHING_ROD:
                case TRIDENT:
                case CROSSBOW:
                    ret |= ItemUtil.slotIsHands(slot);
                    break;
                case BREAKABLE:
                case VANISHABLE:
                    return true;
            }
        }
        return ret;
    }
    //endregion

    //region Enchantment Lore
    public final static String[] NUMERALS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
    public String loreString(int level) {
        String numeral = " ";

        if (maxLevel != 1 && level > 0 && level <= maxLevel && level <= NUMERALS.length)
            numeral += NUMERALS[level - 1];
        else if (level > maxLevel || level > NUMERALS.length)
            numeral += level;

        return loreColor.getColorCode() + name + numeral;
    }

    /**
     * Adds lore to an item to reflect all of the custom enchantments on the item
     * @param item item to apply lore to
     */
    public static void updateMeta(ItemStack item) {
        Map<Enchantment, Integer> enchantments = item.getEnchantments();

        if (ItemUtil.isEnchantedBook(item) && item.getItemMeta() != null)
            enchantments = ((EnchantmentStorageMeta)item.getItemMeta()).getStoredEnchants();

        updateMeta(item, enchantments);
    }

    /**
     * Adds lore to an item to reflect all of the custom enchantments on the item
     * @param item item to apply lore to
     * @param enchantments item's enchantments
     */
    public static void updateMeta(ItemStack item, Map<Enchantment, Integer> enchantments) {
        ArrayList<String> lore = new ArrayList<>();

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        // Remove attributes
        meta.removeAttributeModifier(EquipmentSlot.HEAD);
        meta.removeAttributeModifier(EquipmentSlot.CHEST);
        meta.removeAttributeModifier(EquipmentSlot.LEGS);
        meta.removeAttributeModifier(EquipmentSlot.FEET);
        meta.removeAttributeModifier(EquipmentSlot.HAND);
        meta.removeAttributeModifier(EquipmentSlot.OFF_HAND);

        // Add enchantment lore and meta data
        for (Enchantment enchant : enchantments.keySet()) {
            if (enchant instanceof CustomEnchantment) {
                CustomEnchantment en = (CustomEnchantment) enchant;
                if (!en.enabled) continue;
                int level = enchantments.get(enchant);
                lore.add(en.loreString(level));
                en.applyEnchant(meta, level);
            }
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    //endregion

    //region Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false;
        CustomEnchantment that = (CustomEnchantment) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
    //endregion

    // Methods to be overridden by subclasses
    // Action completed => true, action not completed => false

    //region Events
    /**
     * Update action called every time the Update event is called
     */
    public void update(UpdateItemEvent event) {

    }

    public void onFoodLevelChange(FoodLevelChangeEvent event, int level) {

    }

    public void onPlayerInteractEntity(PlayerInteractEntityEvent event, int level) {

    }

    public void onEntityTame(EntityTameEvent event, int level) {

    }

    public void onEntityDamage(EntityDamageEvent event, int level) {

    }

    public void onPlayerTeleport(PlayerTeleportEvent event, int level) {

    }

    public void onEntityDeath(EntityDeathEvent event, int level) {

    }

    public void onEntityAirChange(EntityAirChangeEvent event, int level) {

    }

    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {

    }
    //endregion

    //region Meta
    /**
     * Used to add custom meta data to item
     * Such as attributes (e.g. move speed)
     * @param meta item meta data
     * @param level enchantment level
     */
    public void applyEnchant(ItemMeta meta, int level) {

    }
    //endregion
}
