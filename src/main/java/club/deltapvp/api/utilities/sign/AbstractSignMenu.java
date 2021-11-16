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

import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiPredicate;

public abstract class AbstractSignMenu {

    public final List<String> text;

    public BiPredicate<Player, String[]> response;
    public boolean reopenIfFail;

    public BlockPosition position;

    public boolean forceClose;

    public AbstractSignMenu(List<String> text) {
        this.text = text;
    }

    public abstract AbstractSignMenu reOpenIfFail(boolean value);

    public abstract AbstractSignMenu response(BiPredicate<Player, String[]> response);

    public abstract void open(Player player);

    public abstract void close(Player player, boolean force);

    public void close(Player player) {
        this.close(player, false);
    }

    private String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

}
