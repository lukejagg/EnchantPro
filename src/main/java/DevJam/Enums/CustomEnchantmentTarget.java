package DevJam.Enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomEnchantmentTarget {
    ARMOR {
        public boolean includes(Material item) {
            return BOOTS.includes(item) || LEGGINGS.includes(item) ||  CHESTPLATE.includes(item) || HELMET.includes(item);
        }
    },
    BOOTS {
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_BOOTS) || item.equals(Material.CHAINMAIL_BOOTS) || item.equals(Material.IRON_BOOTS) || item.equals(Material.DIAMOND_BOOTS) || item.equals(Material.GOLDEN_BOOTS) || item.equals(Material.NETHERITE_BOOTS);
        }
    },
    LEGGINGS {
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_LEGGINGS) || item.equals(Material.CHAINMAIL_LEGGINGS) || item.equals(Material.IRON_LEGGINGS) || item.equals(Material.DIAMOND_LEGGINGS) || item.equals(Material.GOLDEN_LEGGINGS) || item.equals(Material.NETHERITE_LEGGINGS);
        }
    },
    CHESTPLATE {
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_CHESTPLATE) || item.equals(Material.CHAINMAIL_CHESTPLATE) || item.equals(Material.IRON_CHESTPLATE) || item.equals(Material.DIAMOND_CHESTPLATE) || item.equals(Material.GOLDEN_CHESTPLATE) || item.equals(Material.NETHERITE_CHESTPLATE);
        }
    },
    HELMET {
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_HELMET) || item.equals(Material.CHAINMAIL_HELMET) || item.equals(Material.DIAMOND_HELMET) || item.equals(Material.IRON_HELMET) || item.equals(Material.GOLDEN_HELMET) || item.equals(Material.TURTLE_HELMET) || item.equals(Material.NETHERITE_HELMET);
        }
    },
    TOOL {
        public boolean includes(Material item) {
            return SHOVEL.includes(item) || PICKAXE.includes(item) || AXE.includes(item) || HOE.includes(item);
        }
    },
    BOW {
        public boolean includes(Material item) {
            return item.equals(Material.BOW);
        }
    },
    FISHING_ROD {
        public boolean includes(Material item) {
            return item.equals(Material.FISHING_ROD);
        }
    },
    BREAKABLE {
        public boolean includes(Material item) {
            return item.getMaxDurability() > 0 && item.getMaxStackSize() == 1;
        }
    },
    WEARABLE {
        public boolean includes(Material item) {
            return ARMOR.includes(item) || item.equals(Material.ELYTRA) || item.equals(Material.CARVED_PUMPKIN) || item.equals(Material.JACK_O_LANTERN) || item.equals(Material.SKELETON_SKULL) || item.equals(Material.WITHER_SKELETON_SKULL) || item.equals(Material.ZOMBIE_HEAD) || item.equals(Material.PLAYER_HEAD) || item.equals(Material.CREEPER_HEAD) || item.equals(Material.DRAGON_HEAD);
        }
    },
    TRIDENT {
        public boolean includes(Material item) {
            return item.equals(Material.TRIDENT);
        }
    },
    CROSSBOW {
        public boolean includes(Material item) {
            return item.equals(Material.CROSSBOW);
        }
    },
    VANISHABLE {
        public boolean includes(Material item) {
            return BREAKABLE.includes(item) || WEARABLE.includes(item) && !item.equals(Material.ELYTRA) || item.equals(Material.COMPASS);
        }
    },
    ELYTRA {
        public boolean includes(Material item) {
            return item.equals(Material.ELYTRA);
        }
    },
    SHIELD {
        public boolean includes(Material item) {
            return item.equals(Material.SHIELD);
        }
    },
    SHOVEL {
        public boolean includes(Material item) {
            return item.equals(Material.WOODEN_SHOVEL) || item.equals(Material.STONE_SHOVEL) || item.equals(Material.IRON_SHOVEL) || item.equals(Material.DIAMOND_SHOVEL) || item.equals(Material.GOLDEN_SHOVEL) || item.equals(Material.NETHERITE_SHOVEL);
        }
    },
    AXE {
        public boolean includes(Material item) {
            return item.equals(Material.WOODEN_AXE) || item.equals(Material.STONE_AXE) || item.equals(Material.IRON_AXE) || item.equals(Material.DIAMOND_AXE) || item.equals(Material.GOLDEN_AXE) || item.equals(Material.NETHERITE_AXE);
        }
    },
    PICKAXE {
        public boolean includes(Material item) {
            return item.equals(Material.WOODEN_PICKAXE) || item.equals(Material.STONE_PICKAXE) || item.equals(Material.IRON_PICKAXE) || item.equals(Material.DIAMOND_PICKAXE) || item.equals(Material.GOLDEN_PICKAXE) || item.equals(Material.NETHERITE_PICKAXE);
        }
    },
    SWORD {
        public boolean includes(Material item) {
            return item.equals(Material.WOODEN_SWORD) || item.equals(Material.STONE_SWORD) || item.equals(Material.IRON_SWORD) || item.equals(Material.DIAMOND_SWORD) || item.equals(Material.GOLDEN_SWORD) || item.equals(Material.NETHERITE_SWORD);
        }
    },
    HOE {
        public boolean includes(Material item) {
            return item.equals(Material.WOODEN_HOE) || item.equals(Material.STONE_HOE) || item.equals(Material.IRON_HOE) || item.equals(Material.DIAMOND_HOE) || item.equals(Material.GOLDEN_HOE) || item.equals(Material.NETHERITE_HOE);
        }
    },
    FLINT_AND_STEEL {
        public boolean includes(Material item) {
            return item.equals(Material.FLINT_AND_STEEL);
        }
    },
    SHEARS {
        public boolean includes(Material item) {
            return item.equals(Material.SHEARS);
        }
    };

    public abstract boolean includes(Material item);

    public boolean includes(ItemStack stack) {
        if (stack == null) return false;
        return this.includes(stack.getType());
    }
}
