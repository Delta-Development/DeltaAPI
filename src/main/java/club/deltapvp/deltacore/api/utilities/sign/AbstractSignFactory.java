package club.deltapvp.deltacore.api.utilities.sign;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSignFactory {

    public static final int ACTION_INDEX = 9;
    public static final int SIGN_LINES = 4;

    public static final String NBT_FORMAT = "{\"text\":\"%s\"}";
    public static final String NBT_BLOCK_ID = "minecraft:sign";

    public final Plugin plugin;

    public final Map<Player, AbstractSignMenu> inputs;

    public AbstractSignFactory(Plugin plugin) {
        this.plugin = plugin;
        this.inputs = new HashMap<>();
        this.listen();
    }

    public abstract AbstractSignMenu newMenu(List<String> text);

    public abstract void listen();
}
