package DevJam.Listeners;

import DevJam.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Map;

/**
 * Listens for actions that enchantments use
 */
public class ActionListener implements Listener {
    // Todo: Refactor
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        EntityEquipment equipment = event.getEntity().getEquipment();

        for (ItemStack armorPiece : equipment.getArmorContents()) {
            if (armorPiece == null) continue;
            Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
            for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                if (enchant instanceof CustomEnchantment) {
                    int level = enchantmentIntegerMap.get(enchant);
                    CustomEnchantment enchantment = (CustomEnchantment) enchant;
                    enchantment.onFoodLevelChange(event, level);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        EntityEquipment equipment = event.getPlayer().getEquipment();

        for (ItemStack armorPiece : equipment.getArmorContents()) {
            if (armorPiece == null) continue;
            Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
            for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                if (enchant instanceof CustomEnchantment) {
                    int level = enchantmentIntegerMap.get(enchant);
                    CustomEnchantment enchantment = (CustomEnchantment) enchant;
                    enchantment.onPlayerInteractEntity(event, level);
                }
            }
        }
    }

    @EventHandler
    public void onEntityTame(EntityTameEvent event) {
        if (event.getOwner() instanceof Player) {
            Player player = (Player) event.getOwner();
            EntityEquipment equipment = player.getEquipment();

            for (ItemStack armorPiece : equipment.getArmorContents()) {
                if (armorPiece == null) continue;
                Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
                for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                    if (enchant instanceof CustomEnchantment) {
                        int level = enchantmentIntegerMap.get(enchant);
                        CustomEnchantment enchantment = (CustomEnchantment) enchant;
                        enchantment.onEntityTame(event, level);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            EntityEquipment equipment = player.getEquipment();

            for (ItemStack armorPiece : equipment.getArmorContents()) {
                if (armorPiece == null) continue;
                Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
                for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                    if (enchant instanceof CustomEnchantment) {
                        int level = enchantmentIntegerMap.get(enchant);
                        CustomEnchantment enchantment = (CustomEnchantment) enchant;
                        enchantment.onEntityDamage(event, level);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        EntityEquipment equipment = event.getPlayer().getEquipment();

        for (ItemStack armorPiece : equipment.getArmorContents()) {
            if (armorPiece == null) continue;
            Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
            for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                if (enchant instanceof CustomEnchantment) {
                    int level = enchantmentIntegerMap.get(enchant);
                    CustomEnchantment enchantment = (CustomEnchantment) enchant;
                    enchantment.onPlayerTeleport(event, level);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // Handle enchantments on equipped items of dead entity
        if (event.getEntity() instanceof Player) {
            EntityEquipment equipment = event.getEntity().getEquipment();

            for (ItemStack armorPiece : equipment.getArmorContents()) {
                if (armorPiece == null) continue;
                Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
                for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                    if (enchant instanceof CustomEnchantment) {
                        int level = enchantmentIntegerMap.get(enchant);
                        CustomEnchantment enchantment = (CustomEnchantment) enchant;
                        enchantment.onEntityDeath(event, level);
                    }
                }
            }
        } else { // Is a mob
            ArrayList<ItemStack> drops = new ArrayList<ItemStack>(event.getDrops()); // Prevents concurrent modification error
            for (ItemStack drop : drops) {
                if (drop == null) continue;
                Map<Enchantment, Integer> enchantmentIntegerMap = drop.getEnchantments();
                for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                    if (enchant instanceof CustomEnchantment) {
                        int level = enchantmentIntegerMap.get(enchant);
                        CustomEnchantment enchantment = (CustomEnchantment) enchant;
                        enchantment.onEntityDeath(event, level);
                    }
                }
            }
        }

        // Handle enchantments on killer
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;
        EntityEquipment equipment = killer.getEquipment();
        ItemStack heldItem = equipment.getItemInMainHand();
        Map<Enchantment, Integer> enchantmentIntegerMap = heldItem.getEnchantments();
        for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
            if (enchant instanceof CustomEnchantment) {
                int level = enchantmentIntegerMap.get(enchant);
                CustomEnchantment enchantment = (CustomEnchantment) enchant;
                enchantment.onEntityDeath(event, level);
            }
        }
    }

    @EventHandler
    public void onEntityAirChange(EntityAirChangeEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity();
        EntityEquipment equipment = entity.getEquipment();

        for (ItemStack armorPiece : equipment.getArmorContents()) {
            if (armorPiece == null) continue;
            Map<Enchantment, Integer> enchantmentIntegerMap = armorPiece.getEnchantments();
            for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                if (enchant instanceof CustomEnchantment) {
                    int level = enchantmentIntegerMap.get(enchant);
                    CustomEnchantment enchantment = (CustomEnchantment) enchant;
                    enchantment.onEntityAirChange(event, level);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof LivingEntity) {
            LivingEntity damager = (LivingEntity) event.getDamager();
            ItemStack item = damager.getEquipment().getItemInMainHand();
            Map<Enchantment, Integer> enchantmentIntegerMap = item.getEnchantments();
            for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
                if (enchant instanceof CustomEnchantment) {
                    int level = enchantmentIntegerMap.get(enchant);
                    CustomEnchantment enchantment = (CustomEnchantment) enchant;
                    enchantment.onEntityDamageByEntity(event, level);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player breaker = event.getPlayer();
        EntityEquipment equipment = breaker.getEquipment();
        ItemStack heldItem = equipment.getItemInMainHand();
        Map<Enchantment, Integer> enchantmentIntegerMap = heldItem.getEnchantments();
        for (Enchantment enchant : enchantmentIntegerMap.keySet()) {
            if (enchant instanceof CustomEnchantment) {
                int level = enchantmentIntegerMap.get(enchant);
                CustomEnchantment enchantment = (CustomEnchantment) enchant;
                enchantment.onBlockBreak(event, level);
            }
        }
    }
}