package club.deltapvp.deltacore.api.bungeecord;

import org.bukkit.entity.Player;

/**
 * BungeeCord Utility
 * Do things with bungeecord without needing to actually use a BungeeCord plugin.
 * Such as sending a player to another server.
 *
 * @author Negative
 * @since August 25h, 2021
 */
public interface BungeeCord {

    /**
     * Sends a player to a server
     *
     * @param player     Player
     * @param serverName Server Name
     */
    void sendPlayerToServer(Player player, String serverName);

    /**
     * Sends a player to a server after a given amount of ticks
     *
     * @param player     Player
     * @param serverName Server Name
     * @param delay      Delay in Ticks
     * @apiNote 20 ticks are in a second.
     */
    void sendPlayerToServer(Player player, String serverName, int delay);
}
