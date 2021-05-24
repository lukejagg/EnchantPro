package DevJam;

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

            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            sword.addEnchantment(new Test(), 1);
            CustomEnchantment.apply(sword);

            player.getInventory().addItem(sword);
        }
        return true;
    }
}
