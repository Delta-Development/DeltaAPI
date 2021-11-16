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

package club.deltapvp.api.utilities.message.iface;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Interface based Message class
 *
 * @author Negative
 * <p>
 * This class is an interfaced based version of {@link club.deltapvp.api.utilities.message.object.Message}
 * which is an older legacy version of this one.
 * <p>
 * This one is typically recommended, but both work and will be updated!
 */
public interface Message {

    void send(CommandSender sender, String... replacers);

    void send(CommandSender sender);

    void send(List<Player> players, String... replacers);

    void send(List<Player> players);

    void broadcast(String... replacers);

    void broadcast();

    String getMessage();

    String getMessage(String... replacers);

}
