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

package club.deltapvp.api.gui;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitTask;

public class BaseGUI implements InventoryHolder {

    @Getter
    private final GUI gui;

    @Getter
    @Setter
    private Inventory inventory;

    public BaseGUI(GUI gui) {
        this.gui = gui;
    }

    public void onClose(Player player, InventoryCloseEvent event) {
        if (gui.getOnClose() != null)
            gui.getOnClose().accept(player, event);

        gui.getActiveInventories().remove(player);

        BukkitTask autoRefreshTask = gui.getAutoRefreshTask();
        if (autoRefreshTask != null)
            autoRefreshTask.cancel();
    }

}
