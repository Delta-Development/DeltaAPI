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

package club.deltapvp.api.utilities.hologram;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.function.BiConsumer;

/**
 * Hologram
 *
 * @author Negative
 * @apiNote I recommend using the packet based Hologram system. {@link club.deltapvp.api.utilities.hologram.v2.Hologram}
 * @since August 28th, 2021
 */
public interface Hologram {

    String getStringID();

    int getIntID();

    LinkedList<String> getLines();

    LinkedList<ArmorStand> getEntities();

    void update();

    void remove();

    void load();

    Location getLocation();

    void setLocation(Location location);

    void onInteraction(BiConsumer<Player, HologramInteractEvent> function);

    BiConsumer<Player, HologramInteractEvent> getInteractionFunction();

    void addLine(String text);

    void setLine(int index, String text);

    void setSpaceInBetween(double number);


}
