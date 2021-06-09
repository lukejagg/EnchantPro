package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Surprise extends CustomEnchantment {
    private static final Random RANDOM = new Random();

    public Surprise() {
        super("surprise", "Surprise");
        setTargetItems(EnchantmentTarget.WEAPON);
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        LivingEntity attacked = (LivingEntity) event.getEntity();
        PotionEffectType[] effects = PotionEffectType.values();
        PotionEffectType effectType = effects[RANDOM.nextInt(effects.length)];
        attacked.addPotionEffect(new PotionEffect(effectType, 20 * 20, 0));
    }
}
