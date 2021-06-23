package DevJam.Enchantments.Shield;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class X_Recoil extends CustomEnchantment {
    public X_Recoil() {
        super("recoil", "Recoil");
        enchantData = new EnchantData(100, 100, 1, 0, 0);
        setTargetItems(CustomEnchantmentTarget.SHIELD);
    }
}
