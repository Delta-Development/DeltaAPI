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

package club.deltapvp.api.utilities.hologram.v2;

import org.bukkit.Location;

import java.util.List;
import java.util.function.Consumer;

/**
 * Packet Based Hologram
 *
 * @author Negative
 * @since October 5th, 2021
 */
public abstract class Hologram {

    private Consumer<HologramInteractEvent> hologramInteractEventConsumer;

    public abstract String handle();

    public abstract List<String> lines();

    public abstract Location location();

    public void setHologramInteractEvent(Consumer<HologramInteractEvent> function) {
        this.hologramInteractEventConsumer = function;
    }

    public void onHologramInteract(HologramInteractEvent event) {
        if (hologramInteractEventConsumer == null)
            return;

        hologramInteractEventConsumer.accept(event);
    }
}
