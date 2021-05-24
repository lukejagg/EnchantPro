package DevJam;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;

public class CustomEnchant extends Enchantment {
    private String name;
    private int maxLevel;
    private EnchantmentTarget target;
    private boolean treasure;
    private boolean cursed;
    private ArrayList<Enchantment> conflicts = new ArrayList<Enchantment>();

    public CustomEnchant(NamespacedKey key, String name) {
        super(key);
        this.name = name;
        maxLevel = 1;
        target = EnchantmentTarget.ALL;
        treasure = false;
        cursed = false;
    }

    public String getName() {
        return name;
    }

    public String loreString(int level) {
        String[] numerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        String numeral = " ";
        if (maxLevel != 1 && level > 0 && level <= maxLevel) {
            numeral += numerals[level - 1];
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
        return target;
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

    public static void build(ItemStack item) { // adds lore to item to reflect all the custom enchantments on it
        ArrayList<String> lore = new ArrayList<String>();

        Map<Enchantment, Integer> enchantments = item.getEnchantments();
        for (Enchantment enchant : enchantments.keySet()) {
            if (enchant instanceof CustomEnchant) {
                lore.add(((CustomEnchant) enchant).loreString(enchantments.get(enchant)));
            }
        }

        ItemMeta im = item.getItemMeta();
        im.setLore(lore);
        item.setItemMeta(im);
    }
}
