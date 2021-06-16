package DevJam.Enchantments.Armor.Boots;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Olympian extends CustomEnchantment {
    public Olympian() {
        super("olympian", "Olympian");
        setTargetItems(EnchantmentTarget.ARMOR_FEET);
        maxLevel = 2;
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5 * 20, event.level - 1));
    }
}
