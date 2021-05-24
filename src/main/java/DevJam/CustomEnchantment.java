package DevJam;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;

public class CustomEnchantment extends Enchantment {
    private String name;
    private int maxLevel;
    private EnchantmentTarget targetItem;
    private boolean treasure;
    private boolean cursed;
    private ArrayList<Enchantment> conflicts = new ArrayList<Enchantment>();

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

        if (maxLevel != 1 && level > 0 && level <= maxLevel)
            numeral += NUMERALS[level - 1];

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

    public boolean conflictsWith(Enchantment other) {
        for (Enchantment e : conflicts) {
            if (e.equals(other)) {
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
        ArrayList<String> lore = new ArrayList<String>();

        Map<Enchantment, Integer> enchantments = item.getEnchantments();
        for (Enchantment enchant : enchantments.keySet()) {
            if (enchant instanceof CustomEnchantment) {
                CustomEnchantment en = (CustomEnchantment) enchant;
                lore.add(en.loreString(enchantments.get(enchant)));
            }
        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
