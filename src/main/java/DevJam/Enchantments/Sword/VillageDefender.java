package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Util.EntityUtil;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class VillageDefender extends CustomEnchantment {
    public VillageDefender() {
        super("villagedefender", "Village Defender");
        setTargetItems(CustomEnchantmentTarget.SWORD);
        maxLevel = 5;
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        if (EntityUtil.entityTypeIsAnIllagerOrRaider(event.getEntityType())) {
            event.setDamage(event.getDamage() + 2.5 * level);
        }
    }
}
