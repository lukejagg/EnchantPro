package DevJam;

import DevJam.Enchantments.Regeneration;
import DevJam.Enchantments.Test;
import DevJam.Listeners.EnchantListener;
import DevJam.Listeners.UpdateListener;
import DevJam.Tasks.Update;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class EnchantManager {
    private static boolean registered = false;
    private static ArrayList<CustomEnchantment> enchants = new ArrayList<CustomEnchantment>();


    public static void start() {
        registerEnchantments();
        registerListeners();
        registerTasks();
    }

    public static void stop() {

    }

    //region Enchantment Registration
    /**
     * Method that registers all custom enchantments
     */
    private static void registerEnchantments() {
        if (!registered) {
            allowRegistrations();

            // Enchantments
            EnchantManager.register(new Test());
            EnchantManager.register(new Regeneration());

            /* Why is this necessary? */
            //Enchantment.stopAcceptingRegistrations();

            /* List registered enchantments */
            Info.plugin.getLogger().info("Listing custom enchantments:");
            for (Enchantment e : Enchantment.values())
                if (e instanceof CustomEnchantment)
                    Info.plugin.getLogger().info("[ENCHANTMENT] " + e.getKey().getKey());

            registered = true;
        }
    }

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
            //fieldAcceptingNew.setAccessible(false);
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
        Enchantment.registerEnchantment(enchant);
        enchants.add(enchant);

        Info.plugin.getLogger().info(enchant.getKey().getKey() + " enchantment registered");
    }
    //endregion

    private static void registerListeners() {
        PluginManager manager = Info.plugin.getServer().getPluginManager();
        manager.registerEvents(new EnchantListener(), Info.plugin);
        manager.registerEvents(new UpdateListener(), Info.plugin);
    }

    private static void registerTasks() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncRepeatingTask(Info.plugin, new Update(), 0, 20);
    }

    /**
     * Returns a custom enchantment based on index
     */
    public static CustomEnchantment getEnchant(int id) {
        return enchants.get(id);
    }
}
