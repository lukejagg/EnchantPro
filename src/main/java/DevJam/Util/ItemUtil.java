package DevJam.Util;

import DevJam.Enums.EquipmentType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.HashMap;
import java.util.Iterator;

public class ItemUtil {
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

    private static final HashMap<Material, EquipmentType> IDEAL_TOOLS = new HashMap<Material, EquipmentType>() {{

    }};
    public static ItemStack getIdealTool(Block block) { // TODO
        return null;
    }
}
