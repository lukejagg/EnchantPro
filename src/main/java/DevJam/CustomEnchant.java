package DevJam;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CustomEnchant extends Enchantment {
    private String name;
    private int maxLevel;
    private EnchantmentTarget target;
    private boolean treasure;
    private boolean cursed;
    private ArrayList<Enchantment> conflicts = new ArrayList<Enchantment>();

    public CustomEnchant(NamespacedKey key) {
        super(key);
        name = key.getKey().toUpperCase();
        maxLevel = 1;
        target = EnchantmentTarget.ALL;
        treasure = false;
        cursed = false;
    }

    public String getName() {
        return name;
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
}
