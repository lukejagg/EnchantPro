package DevJam;

import DevJam.Enchantments.*;
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

            ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
            ItemStack chestPlate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

            helmet.addEnchantment(new PotionResistance(), 1);
            chestPlate.addEnchantment(new Regeneration(), 2);
            chestPlate.addEnchantment(new Life(), 2);
            chestPlate.addEnchantment(new WellFed(), 3);
            chestPlate.addEnchantment(new Tamer(), 1);
            chestPlate.addEnchantment(new Beastmaster(), 1);
            chestPlate.addEnchantment(new PotionResistance(), 3);
            leggings.addEnchantment(new PotionResistance(), 2);

            CustomEnchantment.updateMeta(helmet);
            CustomEnchantment.updateMeta(chestPlate);
            CustomEnchantment.updateMeta(leggings);
            CustomEnchantment.updateMeta(boots);

            player.getInventory().addItem(helmet);
            player.getInventory().addItem(chestPlate);
            player.getInventory().addItem(leggings);
            player.getInventory().addItem(boots);
        }
        return true;
    }
}
