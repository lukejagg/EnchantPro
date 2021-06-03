package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import DevJam.Info;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityAirChangeEvent;

public class Waterbreathing extends CustomEnchantment {
    public Waterbreathing() {
        super("waterbreathing", "Waterbreathing");
        setTargetItems(EnchantmentTarget.ARMOR_HEAD);
    }

    @Override
    public void onEntityAirChange(EntityAirChangeEvent event, int level) { // TODO this and cancelling not working
        LivingEntity entity = (LivingEntity) event.getEntity();
        event.setAmount(entity.getMaximumAir());
    }
}
