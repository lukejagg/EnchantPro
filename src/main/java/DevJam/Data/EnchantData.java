package DevJam.Data;

public class EnchantData {
    /**
     * Chance of getting this enchantment at low level enchanting
     * Level 1
     */
    public double lowWeight;

    /**
     * Chance of getting this enchantment at high level enchanting
     * Level 30
     */
    public double highWeight;

    /**
     * How significant this enchantment is.
     * significance = 1 is normal
     * 0 = not worth anything
     * negative = gives more enchantments
     */
    public double significance;

    /** Max level of enchantment at level 30 = (maxLevel - maxLevelBias)
     *
     */
    public double maxLevelBias;

    /** How random the selected level is.
     *
     */
    public double levelSpread;

    public EnchantData() {
        lowWeight = 100;
        highWeight = 100;
        significance = 1;
        maxLevelBias = 0;
        levelSpread = 0.5;
    }

    public EnchantData(double lowWeight, double highWeight, double significance, double maxLevelBias, double levelSpread) {
        this.lowWeight = lowWeight;
        this.highWeight = highWeight;
        this.significance = significance;
        this.maxLevelBias = maxLevelBias;
        this.levelSpread = levelSpread;
    }
}
