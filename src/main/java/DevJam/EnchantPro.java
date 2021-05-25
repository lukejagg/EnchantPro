package DevJam;

import org.apache.commons.lang.time.StopWatch;
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
    @SuppressWarnings({"ConstantConditions"})
    public void onEnable() {
        Info.plugin = this;
        Info.config = this.getConfig();
        Info.version = this.getDescription().getVersion();

        // Benchmark
        StopWatch w = new StopWatch();
        w.start();

        // Config
        getConfig().addDefault("updatePeriod", 20);
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Register test commands
        getCommand("test").setExecutor(new TestCommand());

        // Register self as command (/ep)
        getCommand(CommandProcessor.PREFIX).setExecutor(this);
        getCommand(CommandProcessor.PREFIX).setTabCompleter(new CommandProcessor.TabCompletion());

        // Custom enchantments
        EnchantManager.start();

        // Finish initialization
        w.stop();
        getLogger().info(Info.BRAND + " v" + Info.version + " started up in " + w.getTime() + "ms");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling EnchantPro");

        EnchantManager.stop();
    }
}
