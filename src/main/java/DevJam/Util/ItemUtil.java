package DevJam.Util;

import DevJam.Enums.EquipmentMaterial;
import DevJam.Enums.EquipmentType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;

import java.util.HashMap;
import java.util.Iterator;

public class ItemUtil {
    //region Material Groupings
    // Use MaterialChoice.test(ItemStack) to see if the item is in the category
    public static final RecipeChoice.MaterialChoice CROPS = new RecipeChoice.MaterialChoice(Material.WHEAT_SEEDS, Material.WHEAT, Material.BEETROOT_SEEDS, Material.BEETROOT, Material.CARROT, Material.POTATO, Material.POISONOUS_POTATO, Material.MELON, Material.PUMPKIN, Material.BAMBOO, Material.COCOA_BEANS, Material.SUGAR_CANE, Material.SWEET_BERRIES, Material.CACTUS, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.KELP, Material.SEA_PICKLE, Material.NETHER_WART, Material.CHORUS_FRUIT, Material.CRIMSON_FUNGUS, Material.WARPED_FUNGUS);
    public static final RecipeChoice.MaterialChoice LEAVES = new RecipeChoice.MaterialChoice(Material.ACACIA_LEAVES, Material.BIRCH_LEAVES, Material.DARK_OAK_LEAVES, Material.JUNGLE_LEAVES, Material.OAK_LEAVES, Material.SPRUCE_LEAVES);
    //endregion

    //region Enchanting
    /**
     * Checks if the item type is enchantable
     * @param item item
     * @return returns if the item type is enchantable
     */
    public static boolean isEnchantable(ItemStack item) {
        return EquipmentType.fromItemStack(item) != null;
    }
    //endregion

    //region Slots
    public static boolean slotIsArmor(EquipmentSlot slot) {
        return slot == EquipmentSlot.HEAD || slot == EquipmentSlot.CHEST
                || slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
    }

    public static boolean slotIsHands(EquipmentSlot slot) {
        return slot == EquipmentSlot.HAND || slot == EquipmentSlot.OFF_HAND;
    }
    //endregion

    /**
     * Gets the smelted version of the item.
     * @param input the item to be smelted
     * @return the smelted item if able to smelted, else it returns the unsmelted input
     */
    public static ItemStack getSmelted(ItemStack input) {
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();

            if (recipe instanceof FurnaceRecipe) {
                FurnaceRecipe furnaceRecipe = (FurnaceRecipe) recipe;

                if (furnaceRecipe.getInputChoice().test(input)) {
                    return recipe.getResult();
                }
            }
        }
        return input;
    }

    private static final HashMap<EquipmentMaterial, RecipeChoice.MaterialChoice> RAW_MATERIALS = new HashMap<EquipmentMaterial, RecipeChoice.MaterialChoice>() {{
        put(EquipmentMaterial.WOOD, new RecipeChoice.MaterialChoice(Material.ACACIA_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.OAK_LOG, Material.SPRUCE_LOG));
        put(EquipmentMaterial.STONE, new RecipeChoice.MaterialChoice(Material.STONE, Material.GRANITE, Material.DIORITE, Material.ANDESITE));
        put(EquipmentMaterial.LEATHER, new RecipeChoice.MaterialChoice(Material.LEATHER));
        put(EquipmentMaterial.CHAINMAIL, new RecipeChoice.MaterialChoice(Material.CHAIN));
        put(EquipmentMaterial.IRON, new RecipeChoice.MaterialChoice(Material.IRON_ORE)); // TODO 1.17 allow copper ore to give it more of a use
        put(EquipmentMaterial.GOLD, new RecipeChoice.MaterialChoice(Material.GOLD_ORE, Material.NETHER_GOLD_ORE));
        put(EquipmentMaterial.DIAMOND, new RecipeChoice.MaterialChoice(Material.DIAMOND_ORE, Material.EMERALD_ORE));
        put(EquipmentMaterial.NETHERITE, new RecipeChoice.MaterialChoice(Material.ANCIENT_DEBRIS, Material.DIAMOND_ORE, Material.EMERALD_ORE));
        put(EquipmentMaterial.TURTLE, new RecipeChoice.MaterialChoice(Material.TURTLE_EGG));
        put(EquipmentMaterial.PRISMARINE, new RecipeChoice.MaterialChoice(Material.PRISMARINE, Material.PRISMARINE_BRICK_SLAB, Material.PRISMARINE_BRICK_STAIRS, Material.PRISMARINE_BRICKS, Material.PRISMARINE_SLAB, Material.PRISMARINE_STAIRS, Material.PRISMARINE_WALL, Material.DARK_PRISMARINE, Material.DARK_PRISMARINE_SLAB, Material.DARK_PRISMARINE_STAIRS));
        put(EquipmentMaterial.MEMBRANE, new RecipeChoice.MaterialChoice(Material.SHULKER_BOX));
    }};
    /**
     * Gets the ore used to create the tool
     * @param tool the tool in question
     * @return the ore as an ItemStack
     */
    public static RecipeChoice.MaterialChoice getRawMaterialForTool(ItemStack tool) {
        return RAW_MATERIALS.get(EquipmentMaterial.fromItemStack(tool));
    }
}
