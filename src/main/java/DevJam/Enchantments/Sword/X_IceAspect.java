package DevJam.Enchantments.Sword;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class X_IceAspect extends CustomEnchantment {
    public X_IceAspect() {
        super("ice_aspect", "Ice Aspect");
        setTargetItems(CustomEnchantmentTarget.SWORD);
        enchantData = new EnchantData(100, 100, 1, 0, 0);
    }
}
