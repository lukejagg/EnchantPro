package DevJam.Data;

public class EnchantData {
    /**
     * Chance of getting thsi enchantment at low level enchanting
     * Level 1
     */
    public double lowWeight = 100;

    /**
     * Chance of getting this enchantment at high level enchanting
     * Level 30
     */
    public double highWeight = 100;

    /**
     * How significant this enchantment is.
     * significance = 1 is normal
     * 0 = not worth anything
     * negative = gives more enchantments
     */
    public double significance = 1;

    /** Max level of enchantment at level 30 = (maxLevel - maxLevelBias)
     *
     */
    public double maxLevelBias = 0;

    /** How random the selected level is.
     *
     */
    public double levelSpread = 0.5;

    public EnchantData(double lowWeight, double highWeight, double significance, double maxLevelBias, double levelSpread) {
        this.lowWeight = lowWeight;
        this.highWeight = highWeight;
        this.significance = significance;
        this.maxLevelBias = maxLevelBias;
        this.levelSpread = levelSpread;
    }
}
