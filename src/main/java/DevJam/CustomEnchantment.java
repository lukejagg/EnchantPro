package DevJam;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public abstract class CustomEnchantment extends Enchantment {
    protected String name;
    protected int maxLevel;
    protected EnchantmentTarget targetItem;
    protected boolean treasure;
    protected boolean cursed;
    protected ArrayList<Enchantment> conflicts = new ArrayList<Enchantment>(); // TODO maybe use classes instead of instances

    public CustomEnchantment(String key, String name) {
        super(new NamespacedKey(Info.plugin, key));
        this.name = name;
        maxLevel = 1;
        targetItem = EnchantmentTarget.ALL;
        treasure = false;
        cursed = false;
    }

    public String getName() {
        return name;
    }

    public final static String[] NUMERALS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
    public String loreString(int level) {
        String numeral = " ";

        if (maxLevel != 1 && level > 0 && level <= maxLevel && level <= NUMERALS.length) {
            numeral += NUMERALS[level - 1];
        } else if (level > maxLevel || level > NUMERALS.length) {
            numeral += level;
        }

        return "§7" + name + numeral;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getStartLevel() {
        return 1; // TODO confused on how this works
    }

    public EnchantmentTarget getItemTarget() {
        return targetItem;
    }

    public boolean isTreasure() {
        return treasure;
    }

    public boolean isCursed() {
        return cursed;
    }

    /**
     * Checks if the enchantment conflicts with another enchantment (idk if this even works)
     * @param other
     * @return true if it conflicts, false if it doesn't
     */
    public boolean conflictsWith(Enchantment other) {
        for (Enchantment conflict : conflicts) {
            if (conflict.equals(other)) {
                return true;
            }
        }
        return false;
    }

    public boolean canEnchantItem(ItemStack item) {
        // TODO
        return true;
    }

    /**
     * Adds lore to an item to reflect all of the custom enchantments on the item
     * @param item
     */
    public static void apply(ItemStack item) {
        Map<Enchantment, Integer> enchantments = item.getEnchantments();
        apply(item, enchantments);
    }

    public static void apply(ItemStack item, Map<Enchantment, Integer> enchantments) {
        ArrayList<String> lore = new ArrayList<String>();

        for (Enchantment enchant : enchantments.keySet()) {
            if (enchant instanceof CustomEnchantment) {
                CustomEnchantment en = (CustomEnchantment) enchant;
                int level = enchantments.get(enchant);
                lore.add(en.loreString(level));
            }
        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

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
}
