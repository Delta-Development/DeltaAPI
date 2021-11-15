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

package club.deltapvp.deltacore.api.utilities.event.items.damage;

import club.deltapvp.deltacore.api.utilities.event.DeltaEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * PlayerDamageByPlayerEvent
 *
 * @author Negative
 * @since September 28th, 2021
 * <p>
 * This event is similar to {@link EntityDamageByEntityEvent} and {@link PlayerDamageByEntityEvent}
 * but it only applies to players so developers do not need to make specific checks to see if a player is being
 * damaged by another player and not any other entity.
 */
@RequiredArgsConstructor
@Getter
@Setter
public class PlayerDamageByPlayerEvent extends DeltaEvent {

    private final Player player;
    private final Player damager;
    private final EntityDamageEvent.DamageCause cause;
    private boolean cancelled;
    private double damage;

}
