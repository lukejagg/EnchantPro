package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends CustomEnchantment {
    public NightVision() {
        super("nightvision", "Night Vision");
        setTargetItems(EnchantmentTarget.ARMOR_HEAD);
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 12 * 20, 0));
    }
}
