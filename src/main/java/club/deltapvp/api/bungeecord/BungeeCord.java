/*
 *       DeltaAPI is a Minecraft Java API.
 *       Copyright (C) 2021 DeltaDevelopment
 *
 *       This program is free software; you can redistribute it and/or modify
 *       it under the terms of the GNU General Public License as published by
 *       the Free Software Foundation; either version 2 of the License, or
 *       (at your option) any later version.
 *
 *       This program is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *       GNU General Public License for more details.
 */

package club.deltapvp.api.bungeecord;

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
