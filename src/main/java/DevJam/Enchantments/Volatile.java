package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Enums.TextColor;
import org.bukkit.event.entity.EntitySpawnEvent;

public class Volatile extends CustomEnchantment {
    public Volatile() {
        super("volatile", "Volatile");
        setTargetItems(CustomEnchantmentTarget.BREAKABLE);
        cursed = true;
        loreColor = TextColor.CURSED_LORE;
    }

    @Override
    public void onEntitySpawn(EntitySpawnEvent event, int level) {
        event.getEntity().remove();
    }
}
