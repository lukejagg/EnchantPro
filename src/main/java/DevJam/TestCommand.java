package DevJam;

import DevJam.Enchantments.Life;
import DevJam.Enchantments.Regeneration;
import DevJam.Enchantments.Test;
import DevJam.Enchantments.WellFed;
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
            item.addEnchantment(new Life(), 2);
            item.addEnchantment(new WellFed(), 3);
            CustomEnchantment.apply(item);

            player.getInventory().addItem(item);
        }
        return true;
    }
}
