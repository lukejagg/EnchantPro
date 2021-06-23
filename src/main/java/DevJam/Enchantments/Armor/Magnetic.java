package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Data.EnchantData;
import DevJam.Enums.CustomEnchantmentTarget;
import DevJam.Events.UpdateItemEvent;
import DevJam.Info;
import org.bukkit.Location;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class Magnetic extends CustomEnchantment {
    final double range = 5;

    public Magnetic() {
        super("magnetic", "Magnetic");
        setTargetItems(CustomEnchantmentTarget.ARMOR);
        enchantData = new EnchantData(1, 25, 2, 0, 0);
        updateDelay = 2;
    }

    @Override
    public void update(UpdateItemEvent event, int level) {
        List<Entity> entities = event.entity.getNearbyEntities(range, range, range);

        for (Entity e : entities) {
            if (e instanceof Item) {
                Item item = (Item)e;

                Location p1 = event.entity.getLocation();
                Location p2 = item.getLocation();
                Location p3 = p1.subtract(p2);

                item.setVelocity(item.getVelocity()
                        .add(p3.toVector().multiply(0.05f)));
            }
        }
    }
}
