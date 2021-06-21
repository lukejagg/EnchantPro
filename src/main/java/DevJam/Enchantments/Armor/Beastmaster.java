package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Enums.CustomEnchantmentTarget;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.entity.EntityTameEvent;

import java.util.Objects;

public class Beastmaster extends CustomEnchantment {
    public Beastmaster() {
        super("beastmaster", "Beastmaster");
        setTargetItems(CustomEnchantmentTarget.ARMOR);
    }

    @Override
    public void onEntityTame(EntityTameEvent event, int level) {
        try {
            Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).addModifier(new AttributeModifier("Beastmaster Health Boost", 1, AttributeModifier.Operation.MULTIPLY_SCALAR_1)); // double health
        } catch (Exception ignored) {

        }
        try {
            Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).addModifier(new AttributeModifier("Beastmaster Attack Boost", 1, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        } catch (Exception ignored) {

        }
        try {
            Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).addModifier(new AttributeModifier("Beastmaster Speed Boost", 0.10, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        } catch (Exception ignored) {

        }
    }
}
