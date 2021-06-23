package DevJam;

import DevJam.Enchantments.Armor.*;
import DevJam.Enchantments.Armor.Boots.Dolphin;
import DevJam.Enchantments.Armor.Boots.Olympian;
import DevJam.Enchantments.Armor.Chestplate.Enderman;
import DevJam.Enchantments.Armor.Helmet.FreezingGlance;
import DevJam.Enchantments.Armor.Helmet.NightVision;
import DevJam.Enchantments.Armor.Helmet.PigGod;
import DevJam.Enchantments.Armor.Helmet.Waterbreathing;
import DevJam.Enchantments.*;
import DevJam.Enchantments.Sword.*;
import DevJam.Enchantments.Tool.*;
import DevJam.Enchantments.Tool.Axe.Arborist;
import DevJam.Listeners.ActionListener;
import DevJam.Listeners.EnchantListener;
import DevJam.Listeners.UpdateListener;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class EnchantManager {
    private static boolean registered = false;
    private static final ArrayList<CustomEnchantment> enchants = new ArrayList<>();

    //region Initialization
    public static void start() {
        registerEnchantments();
        registerListeners();
        registerTasks();
    }

    public static void stop() {

    }
    //endregion

    //region Enchantment Registration
    /**
     * Method that registers all custom enchantments
     */
    private static void registerEnchantments() {
        if (!registered) {
            allowRegistrations();

            // Enchantments
            EnchantManager.register(new Regeneration());
            EnchantManager.register(new Life());
            EnchantManager.register(new WellFed());
            EnchantManager.register(new Tamer());
            EnchantManager.register(new Beastmaster());
            EnchantManager.register(new PotionResistance());
            EnchantManager.register(new LastStand());
            EnchantManager.register(new Rejuvenation());
            EnchantManager.register(new Heavy());
            EnchantManager.register(new Irreparable());
            EnchantManager.register(new NightVision());
            EnchantManager.register(new PigGod());
            EnchantManager.register(new Enderman());
            EnchantManager.register(new Olympian());
            EnchantManager.register(new Dolphin());
            EnchantManager.register(new SelfDestruction());
            EnchantManager.register(new BlazesCurse());
            EnchantManager.register(new FreezingGlance());
            EnchantManager.register(new Waterbreathing());
            EnchantManager.register(new Venomous());
            EnchantManager.register(new Slothful());
            EnchantManager.register(new Blinding());
            EnchantManager.register(new Withering());
            EnchantManager.register(new LifeLeech());
            EnchantManager.register(new Lucky());
            EnchantManager.register(new Beheading());
            EnchantManager.register(new DoubleStrike());
            EnchantManager.register(new Surprise());
            EnchantManager.register(new VillageDefender());
            EnchantManager.register(new Disjunction());
            EnchantManager.register(new AutoSmelting());
            EnchantManager.register(new Flight());
            EnchantManager.register(new Regain());
            EnchantManager.register(new Harvesting());
            EnchantManager.register(new Hasty());
            EnchantManager.register(new LuckyMiner());
            EnchantManager.register(new Ethereal());
            EnchantManager.register(new Volatile());

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
    private static void allowRegistrations() { // Returns true upon success
        try {
            // Using Java reflection to set a private variable
            // https://www.spigotmc.org/threads/making-a-custom-enchantment.226403/
            Field fieldAcceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
            fieldAcceptingNew.setAccessible(true);
            fieldAcceptingNew.set(null, true);
            //fieldAcceptingNew.setAccessible(false);
        } catch (Exception e) {
            Info.error("Cannot register custom enchantments!");
        }
    }

    /**
     * Registers a CustomEnchantment
     * @param enchant enchantment
     */
    private static void register(CustomEnchantment enchant) {
        if (enchant.enabled) {
            Enchantment.registerEnchantment(enchant);
            enchants.add(enchant);

            Info.plugin.getLogger().info(enchant.getKey().getKey() + " enchantment registered");
        }
    }
    //endregion

    //region Listeners
    private static void registerListeners() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new EnchantListener(), Info.plugin);
        manager.registerEvents(new ActionListener(), Info.plugin);
    }

    private static void registerTasks() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncRepeatingTask(Info.plugin, new UpdateListener(), 0, 1);
    }
    //endregion

    public static CustomEnchantment getEnchant(String str) {
        for (CustomEnchantment e : enchants) {
            if (e.getKeyName().equalsIgnoreCase(str)) {
                return e;
            }
        }

        return null;
    }

    public static ArrayList<CustomEnchantment> getEnchants() {
        return enchants;
    }
}
