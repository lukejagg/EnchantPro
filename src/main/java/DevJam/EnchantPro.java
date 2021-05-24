package DevJam;

import DevJam.Commands.Test;
import DevJam.Commands.VersionInfo;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantPro extends JavaPlugin {
    public static EnchantPro Instance;

    public EnchantPro() {
        Instance = this;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandPrefix, String[] args) {
        return CommandProcessor.onCommand(sender, command, commandPrefix, args);
    }

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");

        // Register commands
        this.getCommand("test").setExecutor(new Test());
        this.getCommand("versioninfo").setExecutor(new VersionInfo());

        // Register self as command (/ep)
        getCommand("ep").setExecutor(this);

        // Custom enchantments
        EnchantRegister.register(new CustomEnchant(new NamespacedKey(this, "test"), "Test Enchantment"));


    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
