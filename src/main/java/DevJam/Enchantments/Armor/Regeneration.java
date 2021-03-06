package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Regeneration extends CustomEnchantment {
    public Regeneration() {
        super("regeneration", "Regeneration");
        maxLevel = 2;
        setTargetItems(CustomEnchantmentTarget.ARMOR);
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, event.level - 1));
    }
}
