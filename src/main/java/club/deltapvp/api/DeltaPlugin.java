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

package club.deltapvp.api;

import club.deltapvp.api.utilities.runnable.RunnableSettings;
import club.deltapvp.api.utilities.version.VersionChecker;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

/**
 * DeltaPlugin
 * <p>
 * An extension off of {@link JavaPlugin} which can be used rather than
 * using {@link JavaPlugin} which also provide some useful methods that
 * allow you to register commands without the need of plugin.yml, the ability
 * to register any {@link BukkitRunnable} that annotates {@link RunnableSettings} for
 * quality of life. And more!
 *
 * @author Negative
 * @since August 18th, 2021
 */
public abstract class DeltaPlugin extends JavaPlugin {

    @Override
    public abstract void onEnable();

    @Override
    public abstract void onDisable();

    /**
     * Load custom files with pre-written data.
     *
     * @param plugin Plugin instance
     * @param names  File names
     */
    public void loadFiles(@NonNull JavaPlugin plugin, @NonNull String... names) {
        File dataFolder = plugin.getDataFolder();
        DeltaAPI api = DeltaAPI.getInstance();
        Arrays.stream(names).forEach(name -> {
            File file = new File(dataFolder, name);
            FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

            if (!file.exists())
                api.getFileLoader().loadFile(plugin, name);

            try {
                fileConfig.load(file);
            } catch (Exception e3) {
                e3.printStackTrace();
            }

            fileConfig.getKeys(false).forEach(priceString -> fileConfig.set(priceString, fileConfig.getString(priceString)));
        });

    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void registerCommands(@NonNull Command... commands) {
        VersionChecker versionChecker = DeltaAPI.getInstance().getVersionChecker();

        Server server = Bukkit.getServer();
        Field field = server.getClass().getDeclaredField("commandMap");
        field.setAccessible(true);

        Arrays.stream(commands).forEach(iCommand -> {
            try {
                CommandMap commandMap = (CommandMap) field.get(server);

                String name = iCommand.getName();

                org.bukkit.command.Command command = commandMap.getCommand(name);
                if (command != null) {
                    Map<String, Command> map;
                    if (versionChecker.isLegacy()) {
                        Field commandField = commandMap.getClass().getDeclaredField("knownCommands");
                        commandField.setAccessible(true);
                        map = (Map<String, Command>) commandField.get(commandMap);
                    } else {
                        map = (Map<String, Command>) commandMap.getClass().getDeclaredMethod("getKnownCommands").invoke(commandMap);
                    }
                    command.unregister(commandMap);
                    map.remove(name);
                    iCommand.getAliases().forEach(map::remove);
                }

                commandMap.register(name, iCommand);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Registers Listener classes
     *
     * @param listeners Listeners
     */
    public void registerListeners(@NonNull Listener... listeners) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        Arrays.stream(listeners).forEach(listener -> pluginManager.registerEvents(listener, this));
    }

    /**
     * Register {@link BukkitRunnable} classes to the server
     * which annotate {@link RunnableSettings}.
     *
     * @param runnables Runnables
     */
    public void registerRunnables(@NonNull BukkitRunnable... runnables) {
        Arrays.stream(runnables).filter(runnable -> runnable.getClass().isAnnotationPresent(RunnableSettings.class))
                .forEach(runnable -> {
                    RunnableSettings annotation = runnable.getClass().getAnnotation(RunnableSettings.class);
                    boolean async = annotation.async();
                    int delay = annotation.delay();
                    int speed = annotation.tickSpeed();

                    if (async)
                        runnable.runTaskTimerAsynchronously(this, delay, speed);
                    else
                        runnable.runTaskTimer(this, delay, speed);
                });
    }
}
