package DevJam.Enchantments.Armor.Boots;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Dolphin extends CustomEnchantment {
    public Dolphin() {
        super("dolphin", "Dolphin");
        setTargetItems(EnchantmentTarget.ARMOR_FEET);
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 5 * 20, 0));
    }
}
