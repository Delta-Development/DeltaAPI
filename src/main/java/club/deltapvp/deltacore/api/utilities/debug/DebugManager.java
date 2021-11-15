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
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Debug Manager
 *
 * @author Negative
 * @since September 28th, 2021
 * <p>
 * Debug Manager is a manager for the {@link Debug} system.
 * Developers can register their plugin to the debugger, and it will create or add
 * to the debug.yml file with their plugin name and a value.
 * <p>
 * True meaning debugging for that plugin is enabled, False meaning
 * debugging is false for that plugin
 * <p>
 * Although the code is kind of dirt, I might improve this at a later date.
 */
@UtilityClass
public class DebugManager {

    private DebugConfiguration configuration;

    public void registerPlugin(DeltaPlugin plugin) {
        attemptCreateFile(plugin);

        FileConfiguration config = configuration.getConfig();
        Object o = config.get(plugin.getName());
        if (o == null) {
            config.set(plugin.getName(), false);
            configuration.save();
            configuration.reload();
        }
    }

    public void toggleDebug(DeltaPlugin plugin, boolean value) {
        FileConfiguration config = configuration.getConfig();
        config.set(plugin.getName(), !getCurrentState(plugin));
        configuration.save();
        configuration.reload();
    }

    public boolean getCurrentState(DeltaPlugin plugin) {
        FileConfiguration config = configuration.getConfig();
        return config.getBoolean(plugin.getName(), false);
    }

    private void attemptCreateFile(DeltaPlugin plugin) {
        configuration = new DebugConfiguration(plugin);
    }
}
