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

package club.deltapvp.api.utilities.debug;

import club.deltapvp.api.DeltaPlugin;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Debug Configuration
 *
 * @author Negative
 * @since September 28th, 2021
 * <p>
 * This is the configuration for {@link Debug} and {@link DebugManager} and when a new instance
 * of this class is called it will check to see if the file "debug.yml" exists, if not, create a new
 * file.
 * <p>
 * The file is where the debug data for each registered plugin is stored.
 */
public class DebugConfiguration {

    private File file;
    private YamlConfiguration config;

    @SneakyThrows
    public DebugConfiguration(DeltaPlugin plugin) {
        File temp = plugin.getDataFolder();
        String path = null;
        for (int i = 0; i < 10; i++) {
            if (temp.getParentFile().isDirectory() && temp.getParentFile().getName().equalsIgnoreCase("plugins")) {
                path = temp.getParentFile().getParentFile().getPath();
                break;
            }

            temp = temp.getParentFile();
        }

        if (path == null) {
            System.out.println("Something went wrong while trying to make the debug.yml file!");
            return;
        }

        file = new File(path, "debug.yml");
        if (!file.exists()) {
            file.createNewFile();

        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    @SneakyThrows
    public void save() {
        config.save(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
