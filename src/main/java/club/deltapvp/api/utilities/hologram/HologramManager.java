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

import java.util.ArrayList;

public interface HologramManager {

    Hologram createHologram(Location location, int id, String... text);

    Hologram createHologram(Location location, String id, String... text);

    Hologram createHologram(Location location, int id, double spaces, String... text);

    Hologram createHologram(Location location, String id, double spaces, String... text);

    Hologram getHologram(String id);

    Hologram getHologram(int id);

    Hologram getHologram(Location location);

    Hologram getHologram(ArmorStand armorStand);

    ArrayList<Hologram> getHolograms();

    void removeHologram(int id);

    void removeHologram(String id);

    void removeHologram(Hologram hologram);

}
