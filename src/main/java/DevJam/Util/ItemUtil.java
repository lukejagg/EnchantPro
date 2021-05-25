package DevJam.Util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {
    //region Tools
    public static boolean isAxe(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_AXE || mat == Material.WOODEN_AXE
                || mat == Material.GOLDEN_AXE || mat == Material.IRON_AXE
                || mat == Material.STONE_AXE || mat == Material.NETHERITE_AXE;
    }

    public static boolean isPickaxe(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_PICKAXE || mat == Material.WOODEN_PICKAXE
                || mat == Material.GOLDEN_PICKAXE || mat == Material.IRON_PICKAXE
                || mat == Material.STONE_PICKAXE || mat == Material.NETHERITE_PICKAXE;
    }

    public static boolean isShovel(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_SHOVEL || mat == Material.WOODEN_SHOVEL
                || mat == Material.GOLDEN_SHOVEL || mat == Material.IRON_SHOVEL
                || mat == Material.STONE_SHOVEL || mat == Material.NETHERITE_SHOVEL;
    }

    public static boolean isHoe(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_HOE || mat == Material.WOODEN_HOE
                || mat == Material.GOLDEN_HOE || mat == Material.IRON_HOE
                || mat == Material.STONE_HOE || mat == Material.NETHERITE_HOE;
    }

    public static boolean isTool(ItemStack item) {
        return isAxe(item) || isPickaxe(item) || isShovel(item) || isHoe(item);
    }
    //endregion

    //region Weapons
    public static boolean isSword(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_SWORD || mat == Material.WOODEN_SWORD
                || mat == Material.GOLDEN_SWORD || mat == Material.IRON_SWORD
                || mat == Material.STONE_SWORD || mat == Material.NETHERITE_SWORD;
    }

    public static boolean isTrident(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.TRIDENT;
    }

    public static boolean isBow(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.BOW;
    }

    public static boolean isCrossbow(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.CROSSBOW;
    }

    public static boolean isShield(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.SHIELD;
    }
    //endregion

    //region Armor
    public static boolean isHelmet(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_HELMET || mat == Material.CHAINMAIL_HELMET
                || mat == Material.GOLDEN_HELMET || mat == Material.IRON_HELMET
                || mat == Material.LEATHER_HELMET || mat == Material.NETHERITE_HELMET
                || mat == Material.TURTLE_HELMET;
    }

    public static boolean isChestplate(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_CHESTPLATE || mat == Material.CHAINMAIL_CHESTPLATE
                || mat == Material.GOLDEN_CHESTPLATE || mat == Material.IRON_CHESTPLATE
                || mat == Material.LEATHER_CHESTPLATE || mat == Material.NETHERITE_CHESTPLATE;
    }

    public static boolean isLeggings(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_LEGGINGS || mat == Material.CHAINMAIL_LEGGINGS
                || mat == Material.GOLDEN_LEGGINGS || mat == Material.IRON_LEGGINGS
                || mat == Material.LEATHER_LEGGINGS || mat == Material.NETHERITE_LEGGINGS;
    }

    public static boolean isBoots(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.DIAMOND_BOOTS || mat == Material.CHAINMAIL_BOOTS
                || mat == Material.GOLDEN_BOOTS || mat == Material.IRON_BOOTS
                || mat == Material.LEATHER_BOOTS || mat == Material.NETHERITE_BOOTS;
    }

    public static boolean isArmor(ItemStack item) {
        return isHelmet(item) || isChestplate(item) || isLeggings(item) || isBoots(item);
    }

    public static boolean isElytra(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.ELYTRA;
    }
    //endregion

    //region Miscellaneous
    public static boolean isFishingRod(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.FISHING_ROD;
    }

    public static boolean isFlintAndSteel(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.FLINT_AND_STEEL;
    }

    public static boolean isShears(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.SHEARS;
    }

    public static boolean isBook(ItemStack item) {
        Material mat = item.getType();
        return mat == Material.BOOK || mat == Material.ENCHANTED_BOOK;
    }
    //endregion

    public static boolean isEnchantable(ItemStack item) {
        return isTool(item) || isArmor(item)
                || isSword(item) || isTrident(item) || isShield(item)
                || isBow(item) || isCrossbow(item)
                || isFishingRod(item) || isFlintAndSteel(item) || isShears(item)
                || isBook(item);
    }
}
