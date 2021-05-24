package DevJam.Enchantments;

import DevJam.CustomEnchantment;

public class Test extends CustomEnchantment {
    public static Test Instance;

    private Test() {
        super("test", "Test Enchantment");
    }

    public static Test getInstance() {
        if (Instance == null) {
            Instance = new Test();
        }
        return Instance;
    }
}
