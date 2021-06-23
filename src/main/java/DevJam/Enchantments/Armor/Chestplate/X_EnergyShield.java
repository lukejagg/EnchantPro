package DevJam.Enchantments.Armor.Chestplate;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.event.player.PlayerTeleportEvent;

public class X_EnergyShield extends CustomEnchantment {
    public X_EnergyShield() {
        super("energy_shield", "Energy Shield");
        setTargetItems(CustomEnchantmentTarget.CHESTPLATE);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
