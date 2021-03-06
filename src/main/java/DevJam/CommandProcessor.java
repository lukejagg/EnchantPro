package DevJam;

import DevJam.Enums.EquipmentType;
import DevJam.Enums.PermissionType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandProcessor {
    public final static String PREFIX = "ep", COMMAND = "enchantpro";
    public final static String[] COMMANDS = {"set", "enchant", "help", "version", "refresh", "clear", "remove"};

    public static boolean isCommandPrefix(String str) {
        return str.equalsIgnoreCase(PREFIX) || str.equalsIgnoreCase(COMMAND);
    }

    public static int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    //region Autocomplete
    public static class TabCompletion implements TabCompleter {

        public static boolean startsWith(String str, String prefix) {
            return str.toLowerCase().startsWith(prefix.toLowerCase());
        }

        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            if (args.length == 0 || !(sender instanceof Player))
                return null;

            String label = args[0].toLowerCase();
            List<String> results = new ArrayList<>();

            switch (label) {
                case "set":
                    if (!PermissionType.SET.hasPermission(sender))
                        return results;

                    // Player argument
                    if (args.length == 2) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player == null) continue;
                            if (startsWith(player.getName(), args[1])) {
                                results.add(player.getName());
                            }
                        }
                    }
                    // Enchantment name
                    else if (args.length == 3) {
                        // Todo: add support for @a, etc.
                        //results.addAll(Arrays.asList("@a", "@p", "@r", "@s"));
                        for (CustomEnchantment e : EnchantManager.getEnchants()) {
                            if (startsWith(e.getKeyName(), args[2])) {
                                results.add(e.getKeyName());
                            }
                        }
                    }
                    // Level
                    else if (args.length == 4) {
                        CustomEnchantment e = EnchantManager.getEnchant(args[2]);
                        if (e == null)
                            return results;

                        if (args[3].length() == 0)
                            results.add(e.getMaxLevel() + "");
                        else
                            results.add("[<level>]");
                    }
                    return results;
                case "enchant":
                    if (!PermissionType.ENCHANT.hasPermission(sender))
                        return results;

                    // Player argument
                    if (args.length == 2) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player == null) continue;
                            if (startsWith(player.getName(), args[1])) {
                                results.add(player.getName());
                            }
                        }
                    }
                    // Enchantment name
                    else if (args.length == 3) {
                        // Todo: add support for @a, etc.
                        //results.addAll(Arrays.asList("@a", "@p", "@r", "@s"));

                        Player target = Bukkit.getPlayer(args[1]);
                        if (target == null || target.getInventory().getItemInMainHand().getAmount() == 0) {

                        }
                        else {
                            ItemStack item = target.getInventory().getItemInMainHand();
                            for (CustomEnchantment e : EnchantManager.getEnchants()) {
                                if (e.canEnchantItem(item) && startsWith(e.getKeyName(), args[2])) {
                                    results.add(e.getKeyName());
                                }
                            }
                        }
                    }
                    // Level
                    else if (args.length == 4) {
                        CustomEnchantment e = EnchantManager.getEnchant(args[2]);
                        if (e == null)
                            return results;

                        if (args[3].length() == 0)
                            results.add(e.getMaxLevel() + "");
                        else
                            results.add("[<level>]");
                    }
                    return results;
                case "remove":
                    if (!PermissionType.REMOVE.hasPermission(sender))
                        return results;

                    // Player argument
                    if (args.length == 2) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player == null) continue;
                            if (startsWith(player.getName(), args[1])) {
                                results.add(player.getName());
                            }
                        }
                    }
                    // Enchantment name
                    else if (args.length == 3) {
                        // Todo: add support for @a, etc.
                        //results.addAll(Arrays.asList("@a", "@p", "@r", "@s"));
                        for (CustomEnchantment e : EnchantManager.getEnchants()) {
                            if (startsWith(e.getKeyName(), args[2])) {
                                results.add(e.getKeyName());
                            }
                        }
                    }
                    return results;
                case "clear":
                    if (!PermissionType.CLEAR.hasPermission(sender))
                        return results;

                    // Player argument
                    if (args.length == 2) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player == null) continue;
                            if (startsWith(player.getName(), args[1])) {
                                results.add(player.getName());
                            }
                        }
                    }
                    return results;
                case "refresh":
                    if (!PermissionType.REFRESH.hasPermission(sender)) return results;
                    // Player argument
                    if (args.length == 2) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player == null) continue;
                            if (startsWith(player.getName(), args[1])) {
                                results.add(player.getName());
                            }
                        }
                    }
                    return results;
                default:
                    Collections.addAll(results, COMMANDS);
                    return results;
            }
        }
    }
    //endregion

    /**
     * Called when a command is run.
     * Each sub-command is listed in the switch statement.
     */
    public static boolean onCommand(CommandSender sender, @SuppressWarnings("unused") Command command, String commandPrefix, String[] args) {
        if (isCommandPrefix(commandPrefix)) {
            String label = args.length == 0 ? "" : args[0].toLowerCase();
            switch (label) {
                case "version":
                    return versionInfo(sender);
                case "set":
                    return set(sender, args);
                case "enchant":
                    return enchant(sender, args);
                case "remove":
                    return remove(sender, args);
                case "clear":
                    return clear(sender, args);
                case "refresh":
                    return refresh(sender, args);
                case "help":
                default:
                    return false; //helpEnchantment(sender, label) || enchant(sender, args);
            }
        }
        return true;
    }

    //region Commands
    private static boolean versionInfo(CommandSender sender) {
        sender.sendMessage(Info.LOGO + " Using " + Info.MINILOGO + ChatColor.AQUA + " with version " + ChatColor.RED
                + Info.version +  ChatColor.AQUA + ".");
        sender.sendMessage(Info.LOGO + " Brand: " + ChatColor.RED + Info.BRAND);
        sender.sendMessage(Info.LOGO + " Distribution: " + ChatColor.RED + Info.DISTRIBUTION);
        sender.sendMessage(Info.LOGO + " Download it here:" + ChatColor.DARK_GREEN + " https://github.com/lukejagg/EnchantPro");
        return true;
    }

    private static boolean set(CommandSender sender, String[] args) {
        if (!PermissionType.SET.hasPermission(sender)) {
            sender.sendMessage(Info.LOGO + "You do not have permission to do this!");
            return true;
        }

        if (args.length < 3) return true;

        Player target = Bukkit.getPlayer(args[1]);
        CustomEnchantment enchantment = EnchantManager.getEnchant(args[2]);
        int level = 1;
        if (args.length >= 4)
            level = tryParseInt(args[3], 1);

        if (target != null && enchantment != null) {
            ItemStack item = target.getInventory().getItemInMainHand();

            if (item.getAmount() == 0)
                return true;

            if (EquipmentType.fromItemStack(item) == EquipmentType.BOOK)
                item = new ItemStack(Material.ENCHANTED_BOOK, 1);

            if (EquipmentType.fromItemStack(item) == EquipmentType.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                assert meta != null;
                meta.addStoredEnchant(enchantment, level, true);
                item.setItemMeta(meta);
            }
            else {
                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                meta.addEnchant(enchantment, level, true);
                item.setItemMeta(meta);
            }

            CustomEnchantment.updateMeta(item);
            target.getInventory().setItemInMainHand(item);
        }

        return true;
    }

    private static boolean enchant(CommandSender sender, String[] args) {
        if (!PermissionType.ENCHANT.hasPermission(sender)) {
            sender.sendMessage(Info.LOGO + "You do not have permission to do this!");
            return true;
        }

        if (args.length < 3) return true;

        Player target = Bukkit.getPlayer(args[1]);
        CustomEnchantment enchantment = EnchantManager.getEnchant(args[2]);
        int level = 1;
        if (args.length >= 4)
            level = tryParseInt(args[3], 1);

        if (target != null && enchantment != null) {

            if (level < 1)
                level = 1;
            if (level > enchantment.getMaxLevel())
                level = enchantment.getMaxLevel();

            ItemStack item = target.getInventory().getItemInMainHand();

            if (!enchantment.canEnchantItem(item)) {
                sender.sendMessage(Info.LOGO + "This enchantment can not be added to that item.");
                return true;
            }

            if (item.getAmount() == 0)
                return true;

            if (EquipmentType.fromItemStack(item) == EquipmentType.BOOK)
                item = new ItemStack(Material.ENCHANTED_BOOK, 1);

            if (EquipmentType.fromItemStack(item) == EquipmentType.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                assert meta != null;
                meta.addStoredEnchant(enchantment, level, true);
                item.setItemMeta(meta);
            }
            else {
                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                meta.addEnchant(enchantment, level, true);
                item.setItemMeta(meta);
            }

            CustomEnchantment.updateMeta(item);
            target.getInventory().setItemInMainHand(item);
        }

        return true;
    }

    private static boolean remove(CommandSender sender, String[] args) {
        if (!PermissionType.REMOVE.hasPermission(sender)) {
            sender.sendMessage(Info.LOGO + "You do not have permission to do this!");
            return true;
        }

        if (args.length < 3) return true;

        Player target = Bukkit.getPlayer(args[1]);
        CustomEnchantment enchantment = EnchantManager.getEnchant(args[2]);

        if (target != null && enchantment != null) {
            ItemStack item = target.getInventory().getItemInMainHand();

            if (item.getAmount() == 0)
                return true;

            if (EquipmentType.fromItemStack(item) == EquipmentType.BOOK)
                item = new ItemStack(Material.ENCHANTED_BOOK, 1);

            if (EquipmentType.fromItemStack(item) == EquipmentType.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                assert meta != null;
                meta.removeStoredEnchant(enchantment);
                item.setItemMeta(meta);
            }
            else {
                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                meta.removeEnchant(enchantment);
                item.setItemMeta(meta);
            }

            CustomEnchantment.updateMeta(item);
            target.getInventory().setItemInMainHand(item);
        }

        return true;
    }

    private static boolean clear(CommandSender sender, String[] args) {
        if (!PermissionType.CLEAR.hasPermission(sender)) {
            sender.sendMessage(Info.LOGO + "You do not have permission to do this!");
            return true;
        }

        if (args.length < 2) return true;

        Player target = Bukkit.getPlayer(args[1]);

        if (target != null) {
            ItemStack item = target.getInventory().getItemInMainHand();

            if (item.getAmount() == 0)
                return true;

            if (EquipmentType.fromItemStack(item) == EquipmentType.BOOK)
                item = new ItemStack(Material.ENCHANTED_BOOK, 1);

            if (EquipmentType.fromItemStack(item) == EquipmentType.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                assert meta != null;
                meta.getEnchants().forEach((f, l) -> meta.removeStoredEnchant(f));
                item.setItemMeta(meta);
            }
            else {
                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                meta.getEnchants().forEach((f, l) -> meta.removeEnchant(f));
                item.setItemMeta(meta);
            }

            CustomEnchantment.updateMeta(item);
            target.getInventory().setItemInMainHand(item);
        }

        return true;
    }

    private static boolean refresh(CommandSender sender, String[] args) {
        if (!PermissionType.REFRESH.hasPermission(sender)) {
            sender.sendMessage(Info.LOGO + "You do not have permission to do this!");
            return true;
        }

        Player target = Bukkit.getPlayer(args.length >= 2 ? args[1] : sender.getName());
        if (target != null) {
            ItemStack item = target.getInventory().getItemInMainHand();
            if (item.getAmount() == 0)
                return true;

            CustomEnchantment.updateMeta(item);
            target.getInventory().setItemInMainHand(item);
        }

        return true;
    }
    //endregion
}