package DevJam;

import DevJam.Enchantments.Armor.*;
import DevJam.Enchantments.Armor.Boots.Dolphin;
import DevJam.Enchantments.Armor.Boots.Olympian;
import DevJam.Enchantments.Armor.Chestplate.Enderman;
import DevJam.Enchantments.Armor.Helmet.FreezingGlance;
import DevJam.Enchantments.Armor.Helmet.NightVision;
import DevJam.Enchantments.Armor.Helmet.PigGod;
import DevJam.Enchantments.Armor.Helmet.Waterbreathing;
import DevJam.Enchantments.Irreparable;
import DevJam.Enchantments.Rejuvenation;
import DevJam.Enchantments.Sword.*;
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
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

            helmet.addEnchantment(new PotionResistance(), 1);
            helmet.addEnchantment(new NightVision(), 1);
            helmet.addEnchantment(new PigGod(), 1);
            helmet.addEnchantment(new BlazesCurse(), 1);
            helmet.addEnchantment(new FreezingGlance(), 1);
            helmet.addEnchantment(new Waterbreathing(), 1);
            chestPlate.addEnchantment(new Enderman(), 1);
            chestPlate.addEnchantment(new Regeneration(), 2);
            chestPlate.addEnchantment(new Life(), 2);
            chestPlate.addEnchantment(new WellFed(), 3);
            chestPlate.addEnchantment(new Tamer(), 1);
            chestPlate.addEnchantment(new Beastmaster(), 1);
            chestPlate.addEnchantment(new PotionResistance(), 3);
            leggings.addEnchantment(new PotionResistance(), 2);
            leggings.addEnchantment(new LastStand(), 1);
            leggings.addEnchantment(new Heavy(), 1);
            boots.addEnchantment(new LastStand(), 1);
            boots.addEnchantment(new Rejuvenation(), 1);
            boots.addEnchantment(new Irreparable(), 1);
            boots.setDurability((short) 50);
            boots.addEnchantment(new Olympian(), 2);
            boots.addEnchantment(new Dolphin(), 1);
            boots.addEnchantment(new SelfDestruction(), 5);
            sword.addEnchantment(new Venomous(), 1);
            sword.addEnchantment(new Slothful(), 1);
            sword.addEnchantment(new Blinding(), 1);
            sword.addEnchantment(new Withering(), 1);
            sword.addEnchantment(new LifeLeech(), 3);
            sword.addEnchantment(new Lucky(), 5);

            CustomEnchantment.updateMeta(helmet);
            CustomEnchantment.updateMeta(chestPlate);
            CustomEnchantment.updateMeta(leggings);
            CustomEnchantment.updateMeta(boots);
            CustomEnchantment.updateMeta(sword);

            player.getInventory().addItem(helmet);
            player.getInventory().addItem(chestPlate);
            player.getInventory().addItem(leggings);
            player.getInventory().addItem(boots);
            player.getInventory().addItem(sword);
        }
        return true;
    }
}
