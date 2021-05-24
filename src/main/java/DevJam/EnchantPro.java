package DevJam;

import DevJam.Commands.Test;
import DevJam.Commands.VersionInfo;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantPro extends JavaPlugin {
    public static EnchantPro Instance;

    public EnchantPro() {
        Instance = this;
    }

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        this.getCommand("test").setExecutor(new Test());
        this.getCommand("versioninfo").setExecutor(new VersionInfo());
        EnchantRegister.register(new CustomEnchant(new NamespacedKey(this, "test"), "Test Enchantment"));
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
