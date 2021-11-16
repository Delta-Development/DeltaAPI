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

package club.deltapvp.api.utilities.sign;

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
