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

package club.deltapvp.deltacore.api.utilities.builder;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PotionEffectBuilder {

    private final PotionEffectType type;
    private int duration;
    private int amp;

    public PotionEffectBuilder(PotionEffectType type) {
        this.type = type;
    }

    public PotionEffectBuilder setDuration(int seconds) {
        this.duration = seconds;
        return this;
    }

    public PotionEffectBuilder setAmplifier(int amplifier) {
        this.amp = amplifier;
        return this;
    }

    public PotionEffect build() {
        return new PotionEffect(type, 20 * duration, amp - 1);
    }

    public void apply(Player player) {
        player.addPotionEffect(build());
    }

    public void apply(List<Player> players) {
        players.forEach(this::apply);
    }

}
