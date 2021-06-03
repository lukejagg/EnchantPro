package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.EnchantmentTarget;

public class FreezingGlance extends CustomEnchantment {
    public FreezingGlance() {
        super("freezingglance", "Freezing Glance");
        setTargetItems(EnchantmentTarget.ARMOR_HEAD);
        updateDelay = 1; // idk how long to make this, may lag TODO
    }

    @Override
    public void update(UpdateItemEvent event) { // TODO worldguard/grief prevention support; Source water vs. flowing
        Block target = event.entity.getTargetBlock(null, 200);
        if (target.getType().equals(Material.WATER)) {
            target.setType(Material.FROSTED_ICE);
        }
    }
}
