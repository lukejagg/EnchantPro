package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class LifeLeech extends CustomEnchantment {
    public LifeLeech() {
        super("life_leech", "Life Leech");
        setTargetItems(CustomEnchantmentTarget.SWORD);
        maxLevel = 3;
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        LivingEntity damager = (LivingEntity) event.getDamager();
        double maxHealth = Objects.requireNonNull(damager.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        double recoveredHealth = 0.1 * level * event.getFinalDamage();
        damager.setHealth(Math.min(maxHealth, damager.getHealth() + recoveredHealth));
    }
}
