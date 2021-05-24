package DevJam;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;

public class EnchantRegister {
    public static boolean allowRegistrations() { // returns true upon success
        try { // using Java reflection to set a private variable - https://www.spigotmc.org/threads/making-a-custom-enchantment.226403/
            Field fieldAcceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
            fieldAcceptingNew.setAccessible(true);
            fieldAcceptingNew.set(null, true);
            fieldAcceptingNew.setAccessible(false);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void register(CustomEnchant e) {
        if (allowRegistrations()) {
            Enchantment.registerEnchantment(e);
            Enchantment.stopAcceptingRegistrations();
        } else {
            EnchantPro.Instance.getLogger().warning("Enchantment registration failed!");
        }
    }
}
