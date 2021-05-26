package DevJam.Enchantments;

import DevJam.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Tamer extends CustomEnchantment {
    private final static HashMap<Class, List<Material>> tamingItems = new HashMap<Class, List<Material>>() {{
        put(Wolf.class, Arrays.asList(Material.BONE));
        put(Cat.class, Arrays.asList(Material.COD, Material.SALMON)); // Tamer will allow cooked cod and salmon to tame cats (feature not bug kek)
        put(Horse.class, Arrays.asList(Material.GOLDEN_APPLE, Material.HAY_BLOCK, Material.CARROT, Material.APPLE, Material.WHEAT, Material.SUGAR, Material.BREAD)); // Tamer will allow regular carrots to tame horses, donkeys, and mules (feature not bug once again)
        put(Donkey.class, Arrays.asList(Material.GOLDEN_APPLE, Material.HAY_BLOCK, Material.CARROT, Material.APPLE, Material.WHEAT, Material.SUGAR, Material.BREAD));
        put(Mule.class, Arrays.asList(Material.GOLDEN_APPLE, Material.HAY_BLOCK, Material.CARROT, Material.APPLE, Material.WHEAT, Material.SUGAR, Material.BREAD));
        put(Llama.class, Arrays.asList(Material.GOLDEN_APPLE, Material.HAY_BLOCK, Material.CARROT, Material.APPLE, Material.WHEAT, Material.SUGAR, Material.BREAD));
        put(Parrot.class, Arrays.asList(Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.WHEAT_SEEDS, Material.PUMPKIN_SEEDS, Material.COCOA_BEANS));
    }};

    public Tamer() {
        super("tamer", "Tamer");
        targetItem = EnchantmentTarget.ARMOR;
    }

    @Override
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event, int level) {
        Material mainItem = event.getPlayer().getInventory().getItemInMainHand().getType();
        Material offItem = event.getPlayer().getInventory().getItemInOffHand().getType();
        Entity entity = event.getRightClicked();

        if (entity instanceof Tameable) {
            Tameable animal = (Tameable) entity;

            if (!animal.isTamed()) {
                for (Class clazz : tamingItems.keySet()) {
                    if (clazz.isInstance(animal)) {
                        if (tamingItems.get(clazz).contains(mainItem) || tamingItems.get(clazz).contains(offItem)) {
                            animal.setOwner(event.getPlayer());
                        }
                    }
                }
            }
        }
    }
}
