package DevJam.Listeners;

import DevJam.CustomEnchantment;
import DevJam.Events.UpdateEvent;
import DevJam.Info;
import DevJam.Util.EntityUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class UpdateListener implements Listener {
    @EventHandler
    public void onUpdate(UpdateEvent event) {
        updateArmor();
    }

    /**
     * Applies passive ability to all enchanted armor. Needs refactoring.
     */
    private void updateArmor() {
        ArrayList<LivingEntity> livingEntities = EntityUtil.getLivingEntities();
        if (livingEntities.isEmpty()) return;

        for (LivingEntity entity : livingEntities) {
            EntityEquipment equipment = entity.getEquipment();
            if (equipment == null) continue;
            ItemStack[] armor = entity.getEquipment().getArmorContents();
            if (armor.length == 0) continue;

            for (ItemStack armorPiece : armor) {
                if (armorPiece == null) continue;
                ItemMeta meta = armorPiece.getItemMeta();
                if (meta == null) continue;
                Map<Enchantment, Integer> enchantmentsMap = meta.getEnchants();
                if (enchantmentsMap.isEmpty()) continue;
                Set<Enchantment> enchants = enchantmentsMap.keySet();
                if (enchants.isEmpty()) continue;

                for (Enchantment enchant : enchants) {
                    if (enchant instanceof CustomEnchantment) {
                        CustomEnchantment enchantment = (CustomEnchantment) enchant;
                        enchantment.passive(entity, enchantmentsMap.get(enchant));
                    }
                }
            }
        }
    }
}
