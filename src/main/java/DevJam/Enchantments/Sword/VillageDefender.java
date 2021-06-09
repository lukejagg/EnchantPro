package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

public class VillageDefender extends CustomEnchantment {
    public VillageDefender() {
        super("villagedefender", "Village Defender");
        setTargetItems(EnchantmentTarget.WEAPON);
        maxLevel = 5;
    }

    private static final ArrayList<EntityType> ILLAGERS_AND_RAIDERS = new ArrayList<EntityType>(){{ // https://minecraft.fandom.com/wiki/Illager
        add(EntityType.EVOKER);
        add(EntityType.ILLUSIONER);
        add(EntityType.PILLAGER);
        add(EntityType.VINDICATOR);
        add(EntityType.RAVAGER);
        add(EntityType.VEX);
        add(EntityType.WITCH);
    }};
    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event, int level) {
        if (ILLAGERS_AND_RAIDERS.contains(event.getEntityType())) {
            event.setDamage(event.getDamage() + 2.5 * level);
        }
    }
}
