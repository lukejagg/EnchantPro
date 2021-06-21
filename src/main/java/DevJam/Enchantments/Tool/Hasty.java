package DevJam.Enchantments.Tool;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hasty extends CustomEnchantment {
    public Hasty() {
        super("hasty", "Hasty");
        maxLevel = 2;
        setTargetItems(CustomEnchantmentTarget.TOOL);
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 5 * 20, event.level - 1));
    }
}
