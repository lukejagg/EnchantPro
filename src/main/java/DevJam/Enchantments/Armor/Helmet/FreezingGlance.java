package DevJam.Enchantments.Armor.Helmet;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import DevJam.WorldGuardHook;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class FreezingGlance extends CustomEnchantment {
    public FreezingGlance() {
        super("freezing_glance", "Freezing Glance");
        setTargetItems(CustomEnchantmentTarget.HELMET);
        updateDelay = 1; // idk how long to make this, may lag TODO
    }

    @Override
    public void update(UpdateItemEvent event, int level) { // TODO worldguard/grief prevention support; Source water vs. flowing
        Player plr = (Player)event.entity;
        if (plr != null) {
            Block target = event.entity.getTargetBlock(null, 200);
            if (target.getType().equals(Material.WATER)) {
                if (WorldGuardHook.hasPermission(plr, target.getLocation()))
                    target.setType(Material.FROSTED_ICE);
            }
        }
    }
}
