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

package club.deltapvp.api.utilities.hologram.v2.backend;

import club.deltapvp.api.DeltaPlugin;
import club.deltapvp.api.utilities.hologram.v2.Hologram;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class HologramManager {

    @Getter
    @Setter
    private static HologramManager instance;

    public abstract void registerHologram(DeltaPlugin plugin, Hologram hologram);

    public abstract void updateHologram(String hologramHandle, Player player, boolean all);

    public abstract void updateHologram(Hologram hologram, Player player, boolean all);

    public abstract void removeHologram(String hologramHandle, Player player, boolean all);

    public abstract void removeHologram(Hologram hologram, Player player, boolean all);

    public abstract List<Hologram> getHolograms(DeltaPlugin plugin);
}
