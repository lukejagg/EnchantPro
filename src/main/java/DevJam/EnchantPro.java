package DevJam;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantPro extends JavaPlugin {
    public static EnchantPro Instance;

    public EnchantPro() {
        Instance = this;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        return CommandProcessor.onCommand(sender, command, commandLabel, args);
    }

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        EnchantRegister.register(new CustomEnchant(new NamespacedKey(this, "test")));
        Enchantment[] enchants = Enchantment.values();
        for (Enchantment enchant : enchants) {
            getLogger().info(enchant.getName() + " | " + enchant.getKey());
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }


}
