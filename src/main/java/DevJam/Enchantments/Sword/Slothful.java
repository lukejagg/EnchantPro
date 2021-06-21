package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Slothful extends CustomEnchantment {
    public Slothful() {
        super("slothful", "Slothful");
        setTargetItems(CustomEnchantmentTarget.SWORD);
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        LivingEntity attacked = (LivingEntity) event.getEntity();
        attacked.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 0));
    }
}
