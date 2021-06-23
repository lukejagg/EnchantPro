package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityAirChangeEvent;

public class Waterbreathing extends CustomEnchantment {
    public Waterbreathing() {
        super("waterbreathing", "Waterbreathing");
        setTargetItems(CustomEnchantmentTarget.HELMET);
        updateDelay = 10;
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        event.entity.setRemainingAir(event.entity.getMaximumAir());
    }
}
