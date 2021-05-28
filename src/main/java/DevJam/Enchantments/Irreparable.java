package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Enums.TextColor;
import org.bukkit.enchantments.EnchantmentTarget;

public class Irreparable extends CustomEnchantment {
    public Irreparable() {
        super("irreparable", "Irreparable");
        setTargetItems(EnchantmentTarget.BREAKABLE);
        cursed = true;
        loreColor = TextColor.CURSED_LORE;
    }
}
