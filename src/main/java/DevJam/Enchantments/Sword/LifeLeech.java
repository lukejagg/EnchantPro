package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LifeLeech extends CustomEnchantment {
    public LifeLeech() {
        super("lifeleech", "Life Leech");
        setTargetItems(EnchantmentTarget.WEAPON);
        maxLevel = 3;
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        LivingEntity damager = (LivingEntity) event.getDamager();
        double maxHealth = damager.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double recoveredHealth = 0.1 * level * event.getFinalDamage();
        damager.setHealth(Math.min(maxHealth, damager.getHealth() + recoveredHealth));
    }
}
