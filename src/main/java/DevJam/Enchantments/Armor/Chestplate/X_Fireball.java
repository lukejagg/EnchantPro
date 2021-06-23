package DevJam.Enchantments.Armor.Chestplate;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.event.player.PlayerTeleportEvent;

public class X_Fireball extends CustomEnchantment {
    public X_Fireball() {
        super("fireball", "fireball");
        setTargetItems(CustomEnchantmentTarget.CHESTPLATE);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
