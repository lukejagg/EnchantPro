package DevJam;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class CommandProcessor {
    public final static String PREFIX = "ep";

    public static class TabCompletion implements TabCompleter {

        @Override
        public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
            return null;
        }
    }

    public static boolean onCommand(CommandSender sender, @SuppressWarnings("unused") Command command, String commandPrefix, String[] args) {
        if (commandPrefix.equalsIgnoreCase(PREFIX)) {
            String label = args.length == 0 ? "" : args[0].toLowerCase();
            switch (label) {
                case "version":
                    return versionInfo(sender);
                case "help":
                default:
                    return false; //helpEnchantment(sender, label) || enchant(sender, args);
            }
        }
        return true;
    }


    // Commands
    private static boolean versionInfo(CommandSender sender) {
        sender.sendMessage(Info.LOGO + " Using " + Info.MINILOGO + ChatColor.AQUA + " with version " + ChatColor.RED
                + Info.version +  ChatColor.AQUA + ".");
        sender.sendMessage(Info.LOGO + " Brand: " + ChatColor.RED + Info.BRAND);
        sender.sendMessage(Info.LOGO + " Distribution: " + ChatColor.RED + Info.DISTRIBUTION);
        sender.sendMessage(Info.LOGO + " Download it here:" + ChatColor.DARK_GREEN + " https://github.com/lukejagg/EnchantPro");
        return true;
    }
}
