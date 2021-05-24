package DevJam;

import DevJam.Commands.Test;
import DevJam.Commands.VersionInfo;
import org.apache.commons.lang.time.StopWatch;
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
        Info.plugin = this;
        Info.version = this.getDescription().getVersion();

        // Benchmark
        StopWatch w = new StopWatch();
        w.start();

        // Register commands
        getCommand("test").setExecutor(new Test());
        getCommand("versioninfo").setExecutor(new VersionInfo());

        // Register self as command (/ep)
        getCommand(CommandProcessor.PREFIX).setExecutor(this);
        getCommand(CommandProcessor.PREFIX).setTabCompleter(new CommandProcessor.TabCompletion());

        // Custom enchantments
        EnchantRegister.register(new CustomEnchant(new NamespacedKey(this, "test"), "Test Enchantment"));

        // Finish initialization
        w.stop();
        getLogger().info(Info.BRAND + " v" + Info.version + " started up in " + w.getTime() + "ms");
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
