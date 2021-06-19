package DevJam.Enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum EquipmentMaterial {
    WOOD,
    STONE,
    LEATHER,
    CHAINMAIL,
    IRON,
    GOLD,
    DIAMOND,
    NETHERITE,
    TURTLE,
    PRISMARINE,
    MEMBRANE;

    /**
     * Gets the EquipmentMaterial of the ItemStack
     * @param item the ItemStack to be checked
     * @return the EquipmentMaterial of the item; null if not equipment
     */
    public static EquipmentMaterial fromItemStack(ItemStack item) {
        EquipmentType type = EquipmentType.fromItemStack(item);
        if (type == null) return null;

        switch(type) {
            case CROSSBOW:
            case SHIELD:
            case FLINT_AND_STEEL:
            case SHEARS:
                return IRON;
            case ENCHANTED_BOOK:
            case BOOK:
                return LEATHER;
            case TRIDENT:
                return PRISMARINE;
            case BOW:
            case FISHING_ROD:
                return WOOD;
            case ELYTRA:
                return MEMBRANE;
            default: // General tool
                Material toolMaterial = item.getType();
                for (EquipmentMaterial equipmentMaterial : EquipmentMaterial.values()) {
                    if (toolMaterial.name().toLowerCase().contains(equipmentMaterial.name().toLowerCase())) {
                        return equipmentMaterial;
                    }
                }
                return null;
        }
    }
}
