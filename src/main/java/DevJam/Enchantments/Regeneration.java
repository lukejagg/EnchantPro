package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Info;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Regeneration extends CustomEnchantment {
    public Regeneration() {
        super("regeneration", "Regeneration");
        maxLevel = 2;
        targetItem = EnchantmentTarget.ARMOR;
    }

    @Override
    public boolean passive(LivingEntity entity, int level) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, level));
        return true;
    }
}
