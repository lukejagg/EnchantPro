package DevJam.Commands;

import DevJam.Info;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandVersionInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(Info.LOGO + " Using " + Info.MINILOGO + ChatColor.AQUA + " with version " + ChatColor.RED
                + Info.version +  ChatColor.AQUA + ".");
        sender.sendMessage(Info.LOGO + " Brand: " + ChatColor.RED + Info.BRAND);
        sender.sendMessage(Info.LOGO + " Distribution: " + ChatColor.RED + Info.DISTRIBUTION);
        sender.sendMessage(Info.LOGO + " Download it here:" + ChatColor.DARK_GREEN + " https://github.com/lukejagg/EnchantPro");
        return true;
    }
}
