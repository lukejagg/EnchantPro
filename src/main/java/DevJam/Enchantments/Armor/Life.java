package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

public class Life extends CustomEnchantment {
    public Life() {
        super("life", "Life");
        maxLevel = 2;
        setTargetItems(CustomEnchantmentTarget.ARMOR);
    }

    @Override
    public void applyEnchant(ItemMeta meta, int level) {
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("Max Health", 2 * level, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    }
}
