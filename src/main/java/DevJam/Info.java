package DevJam;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;

/**
 * This class will be removed in the 3.0.0 or 4.0.0 refractor since it deeply violates the principles of OOP.
 *
 * Shared class where constants are provided.
 * @since 1.0
 */
public class Info {

    /**
     * Instance of the Enchantments_plus plugin to be used by the rest of the classes
     * @since 2.0.0
     */
    public static EnchantPro plugin;

    /**
     * Represents the Brand of the plugin, please change it in case you fork the plugin to mark that you have forked it. <br>
     * It's currently only used by the `/ench version` command.
     * @since 1.0.0
     */
    public static final String BRAND = "EnchantPro";

    /**
     * Represents the way the plugin was obtained, the reason behind is purely for analytical purposes. <br>
     * It's currently only used by the `/ench version` command.
     * @since 1.0.0
     */
    public static final String DISTRIBUTION = "self-compiled";

    /**
     * A coled text-based logo for the plugin, used mainly for command responses, but can be used for other stuff. <br>
     * Note: The ChatColor being used after this string will be "reset" to ChatColor.AQUA.
     * Has a space afterwards.
     * @since 1.0.0
     */
    public static final String LOGO = ChatColor.BLUE + "[" + ChatColor.DARK_AQUA + "Enchant" + ChatColor.RED + "Pro"
            + ChatColor.BLUE + "] " + ChatColor.AQUA;

    /**
     * A coled text-based logo for the plugin, used mainly for the command line, but can be used for other stuff. <br>
     * Note: Due to the nature of this String, it is recommended to use it before a ChatColor.RESET or similar. (end of the character is red).
     * It also doesn't have a space afterwards
     * @since 1.0.0
     */
    public static final String MINILOGO = ChatColor.DARK_AQUA + "Enchant" + ChatColor.RED + "Pro";

    /**
     * Marks the used version of the plugin. The version is gathered during the onEnable() function at runtime and is implicitly set via
     * the plugin.yml where it's collected from.
     * The usual format is MAJOR.MINOR.PATCH, however it may be annotated with a single character to mark reuploads.
     * @since 1.0.0
     */
    public static String version = "";

    /**
     * Container for the cardinal block faces, i. e. block faces that directly touch the current block like UP DOWN or NORTH.
     * @since 1.0.0
     */
    public static final BlockFace[] BLOCK_FACES = {
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST,
    };

    public static final BlockFace[] COMPASS_FACES = {
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST,
    };
}