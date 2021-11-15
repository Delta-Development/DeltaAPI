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

package club.deltapvp.deltacore.api.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class DeltaConfiguration {

    @Getter
    private final File file;
    private YamlConfiguration config;

    public DeltaConfiguration(String fileName) {
        this(null, fileName);
    }

    public DeltaConfiguration(String path, String fileName) {
        this(path, fileName, (Object) null);
    }

    @SneakyThrows
    public DeltaConfiguration(String filePath, String fileName, Object... defaults) {
        file = new File(filePath, fileName);
        file.getParentFile().mkdir();

        if (!file.exists()) {
            file.createNewFile();

            if (defaults != null) {
                config = YamlConfiguration.loadConfiguration(file);
                Set<DefaultPair<String, Object>> pairSet = new HashSet<>();
                DefaultPair<String, Object> pair = new DefaultPair<>();

                for (int i = 0; i < defaults.length; i++) {
                    if (i % 2 == 0) {
                        pair.setPath((String) defaults[i]);
                    } else {
                        pair.setValue(defaults[i]);
                        pairSet.add(pair);
                        pair = new DefaultPair<>();
                    }
                }

                pairSet.forEach(match -> {
                    String path = match.getPath();
                    Object value = match.getValue();
                    config.set(path, value);
                });

                config.save(file);
            }
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

    @Data
    private static class DefaultPair<I, O> {
        private I path;
        private O value;
    }
}
