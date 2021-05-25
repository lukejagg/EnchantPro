package DevJam;

import DevJam.Enchantments.Regeneration;
import DevJam.Enchantments.Test;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestCommand implements CommandExecutor { // TODO remove - temporary
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
            item.addEnchantment(new Regeneration(), 2);
            CustomEnchantment.apply(item);

            player.getInventory().addItem(item);
        }
        return true;
    }
}
