package DevJam.Enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public enum EquipmentType { // Do not change enum names to allow fromItemStack to work properly
    PICKAXE(true, false),
    CROSSBOW(false, false),
    ENCHANTED_BOOK(false, false), // These first 3 must be before the others for fromItemStack to work properly
    AXE(true, false),
    SHOVEL(true, false),
    HOE(true, false),
    SWORD(false, false),
    TRIDENT(false, false),
    BOW(false, false),
    SHIELD(false, false),
    HELMET(false, true),
    CHESTPLATE(false, true),
    LEGGINGS(false, true),
    BOOTS(false, true),
    ELYTRA(false, false),
    FISHING_ROD(false, false),
    FLINT_AND_STEEL(false, false),
    SHEARS(false, false),
    BOOK(false, false);

    private final boolean isTool;
    private final boolean isArmor;

    EquipmentType(boolean isTool, boolean isArmor) {
        this.isTool = isTool;
        this.isArmor = isArmor;
    }

    public boolean isTool() {
        return isTool;
    }

    public boolean isArmor() {
        return isArmor;
    }

    private static final ArrayList<Material> EXPLICITLY_NULL = new ArrayList<Material>() {{ // These contain BOW or BOOK and would cause false positives if not handled
        add(Material.BOWL);
        add(Material.BOOKSHELF);
        add(Material.KNOWLEDGE_BOOK);
        add(Material.WRITABLE_BOOK);
        add(Material.WRITTEN_BOOK);
    }};
    /**
     * Gets the EquipmentType of the ItemStack
     * @param item the ItemStack to be checked
     * @return the EquipmentType of the item; null if not equipment
     */
    public static EquipmentType fromItemStack(ItemStack item) {
        if (item == null) return null;
        Material itemMaterial = item.getType();
        if (EXPLICITLY_NULL.contains(itemMaterial)) return null;
        for (EquipmentType type : EquipmentType.values()) {
            if (itemMaterial.name().contains(type.name())) {
                return type;
            }
        }
        return null;
    }
}
