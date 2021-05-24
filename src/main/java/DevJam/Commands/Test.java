package DevJam.Commands;

import DevJam.CustomEnchant;
import DevJam.EnchantRegister;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Test implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            sword.addEnchantment(EnchantRegister.enchants.get(0), 1);
            CustomEnchant.build(sword);

            player.getInventory().addItem(sword);
        }
        return true;
    }
}
