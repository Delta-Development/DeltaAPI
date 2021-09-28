package club.deltapvp.deltacore.api.utilities.debug;

import club.deltapvp.deltacore.api.DeltaPlugin;
import club.deltapvp.deltacore.api.utilities.DeltaUtils;
import lombok.Data;
import org.bukkit.entity.Player;

/**
 * Debug System
 *
 * @author Negative
 * @since September 28th, 2021
 *
 * This is the debug Object which will be used to send debug
 * messages to players or console.
 *
 * If the plugin's debug state is false, it will not send a message.
 *
 * Register your plugin in your onEnable method. {@link DebugManager}
 */
@Data
public class Debug {

    private final DeltaPlugin plugin;
    private final String message;

    public void sendToConsole() {
        boolean state = DebugManager.getCurrentState(plugin);
        if (state)
            System.out.println("[DEBUG] " + message);
    }

    public void broadcast() {
        boolean state = DebugManager.getCurrentState(plugin);
        if (state)
            DeltaUtils.broadcast("&c&l[DEBUG] &r" + message);
    }

    public void sendToPlayer(Player player) {
        boolean state = DebugManager.getCurrentState(plugin);
        if (state)
            player.sendMessage(DeltaUtils.color("&c&l[DEBUG] &r" + message));
    }
}
