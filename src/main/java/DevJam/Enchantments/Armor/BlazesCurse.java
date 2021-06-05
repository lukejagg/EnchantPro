package DevJam.Enchantments.Armor;

import DevJam.CustomEnchantment;
import DevJam.Enums.TextColor;
import DevJam.Events.UpdateItemEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class BlazesCurse extends CustomEnchantment {
    private static final ArrayList<Biome> BIOMES_WITHOUT_RAIN = new ArrayList<Biome>() {{
        add(Biome.DESERT);
        add(Biome.DESERT_HILLS);
        add(Biome.DESERT_LAKES);
        add(Biome.SAVANNA);
        add(Biome.SAVANNA_PLATEAU);
        add(Biome.SHATTERED_SAVANNA);
        add(Biome.SHATTERED_SAVANNA_PLATEAU);
    }};

    public BlazesCurse() {
        super("blazescurse", "Blaze's Curse");
        setTargetItems(EnchantmentTarget.ARMOR);
        cursed = true;
        loreColor = TextColor.CURSED_LORE;
        updateDelay = 20;
    }

    @Override
    public void update(UpdateItemEvent event) {
        event.entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 5 * 20, 0));

        World world = event.entity.getWorld();
        Location eyesLocation = event.entity.getEyeLocation();
        Location feetLocation = eyesLocation.add(0, -1, 0);
        Biome biome = world.getBiome(eyesLocation.getBlockX(), eyesLocation.getBlockY(), eyesLocation.getBlockZ());
        Material matTop = eyesLocation.getBlock().getType();
        Material matBottom = feetLocation.getBlock().getType();

        // Check if in water or in rain (is raining, in raining biome, no blocks above head)
        boolean inWater = matTop.equals(Material.WATER) || matBottom.equals(Material.WATER);
        boolean inRain = world.hasStorm() && !BIOMES_WITHOUT_RAIN.contains(biome) && world.getHighestBlockAt(eyesLocation).getY() < eyesLocation.getBlockY();
        if (inWater || inRain) {
            event.entity.damage(2 * updateDelay / 20.0); // 2 damage per second in water or rain
        }
    }
}
