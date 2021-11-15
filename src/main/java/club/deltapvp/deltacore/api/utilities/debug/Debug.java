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
 * <p>
 * This is the debug Object which will be used to send debug
 * messages to players or console.
 * <p>
 * If the plugin's debug state is false, it will not send a message.
 * <p>
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
