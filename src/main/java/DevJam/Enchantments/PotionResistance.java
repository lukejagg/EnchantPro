package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;

public class PotionResistance extends CustomEnchantment {
    private static final ArrayList<PotionEffectType> badEffects = new ArrayList<PotionEffectType>() {{
       add(PotionEffectType.UNLUCK);
       add(PotionEffectType.BAD_OMEN);
       add(PotionEffectType.BLINDNESS);
       add(PotionEffectType.POISON);
       add(PotionEffectType.HUNGER);
       add(PotionEffectType.SLOW_DIGGING);
       add(PotionEffectType.CONFUSION);
       add(PotionEffectType.SLOW);
       add(PotionEffectType.WEAKNESS);
       add(PotionEffectType.WITHER);
    }};

    public PotionResistance() {
        super("potionresistance", "Potion Resistance");
        targetItem = EnchantmentTarget.ARMOR;
        maxLevel = 3;
        updateDelay = 3; // Should be quick to prevent unwanted effects
    }

    @Override
    public void update(UpdateItemEvent event) {
        for (PotionEffect effect : event.entity.getActivePotionEffects()) {
            int maximumDuration = 20 * (16 - 5 * event.level); // // At Level 1 => 11 second maximum, Level 3 => 1 second maximum
            if (badEffects.contains(effect.getType()) && effect.getDuration() > maximumDuration) {
                event.entity.removePotionEffect(effect.getType());
                event.entity.addPotionEffect(new PotionEffect(effect.getType(), maximumDuration, effect.getAmplifier()));
            }
        }
    }
}
