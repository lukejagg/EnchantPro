package DevJam;

import DevJam.Tasks.Update;
import org.apache.commons.lang.time.StopWatch;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
        Info.plugin = this;
        Info.version = this.getDescription().getVersion();

        // Benchmark
        StopWatch w = new StopWatch();
        w.start();

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
