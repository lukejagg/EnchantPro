package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Venomous extends CustomEnchantment {
    public Venomous() {
        super("venomous", "Venomous");
        setTargetItems(EnchantmentTarget.WEAPON);
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        LivingEntity attacked = (LivingEntity) event.getEntity();
        attacked.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5 * 20, 0));
    }
}
