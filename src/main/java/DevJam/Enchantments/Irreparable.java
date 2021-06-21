package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Enums.TextColor;

public class Irreparable extends CustomEnchantment {
    public Irreparable() {
        super("irreparable", "Irreparable");
        setTargetItems(CustomEnchantmentTarget.BREAKABLE);
        cursed = true;
        loreColor = TextColor.CURSED_LORE;
    }
}
