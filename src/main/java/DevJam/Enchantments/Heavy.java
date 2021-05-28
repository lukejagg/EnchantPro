package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Enums.TextColor;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Heavy extends CustomEnchantment {
    public Heavy() {
        super("heavy", "Heavy");
        targetItem = EnchantmentTarget.ARMOR;
        cursed = true;
        loreColor = TextColor.CURSED_LORE;
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 0));
    }
}