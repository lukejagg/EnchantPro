package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import DevJam.Info;
import DevJam.Util.ItemUtil;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Regeneration extends CustomEnchantment {
    public Regeneration() {
        super("regeneration", "Regeneration");
        maxLevel = 2;
        targetItem = EnchantmentTarget.ARMOR;
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, event.level - 1));
    }
}
