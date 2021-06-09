package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Util.EntityUtil;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Disjunction extends CustomEnchantment {
    public Disjunction() {
        super("disjunction", "Disjunction");
        setTargetItems(EnchantmentTarget.WEAPON);
        maxLevel = 5;
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        EntityType entityType = event.getEntityType();
        if (EntityUtil.entityTypeIsAWither(entityType) || EntityUtil.entityTypeIsAnEnder(entityType)) {
            event.setDamage(event.getDamage() + 2.5 * level);
        }
    }
}
