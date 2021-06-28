package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends CustomEnchantment {
    public NightVision() {
        super("night_vision", "Night Vision");
        setTargetItems(CustomEnchantmentTarget.HELMET);
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 12 * 20, 0));
    }
}
