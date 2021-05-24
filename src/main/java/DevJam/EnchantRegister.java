package DevJam;

import DevJam.Enchantments.Test;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class EnchantRegister {

    /**
     * Method that registers all custom enchantments
     */
    public static void registerEnchantments() {
        EnchantRegister.register(Test.getInstance());
    }

    private static ArrayList<CustomEnchantment> enchants = new ArrayList<CustomEnchantment>();

    /**
     * Checks if enchantments can be registered
     */
    private static boolean allowRegistrations() { // Returns true upon success
        try {
            // Using Java reflection to set a private variable
            // https://www.spigotmc.org/threads/making-a-custom-enchantment.226403/
            Field fieldAcceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
            fieldAcceptingNew.setAccessible(true);
            fieldAcceptingNew.set(null, true);
            fieldAcceptingNew.setAccessible(false);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Registers a CustomEnchantment
     * @param enchant
     */
    private static void register(CustomEnchantment enchant) {
        if (allowRegistrations()) {
            Enchantment.registerEnchantment(enchant);
            enchants.add(enchant);
            Enchantment.stopAcceptingRegistrations();
        } else {
            EnchantPro.Instance.getLogger().warning("Enchantment registration failed!");
        }
    }

    /**
     * Returns a custom enchantment based on index
     */
    public static CustomEnchantment getEnchant(int id) {
        return enchants.get(id);
    }
}
