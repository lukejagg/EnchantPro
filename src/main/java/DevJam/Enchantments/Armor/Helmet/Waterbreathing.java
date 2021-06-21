package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityAirChangeEvent;

public class Waterbreathing extends CustomEnchantment {
    public Waterbreathing() {
        super("waterbreathing", "Waterbreathing");
        setTargetItems(CustomEnchantmentTarget.HELMET);
    }

    @Override
    public void onEntityAirChange(EntityAirChangeEvent event, int level) { // TODO this and cancelling not working
        LivingEntity entity = (LivingEntity) event.getEntity();
        event.setAmount(entity.getMaximumAir());
    }
}
