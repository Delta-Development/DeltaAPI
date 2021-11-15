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

package club.deltapvp.deltacore.api.utilities.placeholder;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Deprecated // Will be removed
public abstract class PAPIPlaceholder {

    private final ArrayList<PAPIPlaceholder> subPlaceholders = new ArrayList<>();

    public abstract int triggerOnArgument();

    public abstract String getIdentifier();

    public String request(Player player, String[] params, int checkingIndex) {
        Optional<PAPIPlaceholder> first = subPlaceholders.stream().filter(papiPlaceholder -> {
            String identifier = papiPlaceholder.getIdentifier();
            return (checkingIndex == triggerOnArgument() && params[checkingIndex].equalsIgnoreCase(identifier));
        }).findFirst();

        if (!first.isPresent())
            return onRequest(player, params);

        PAPIPlaceholder papiPlaceholder = first.get();
        String request = papiPlaceholder.request(player, params, checkingIndex + 1);
        return (request == null ? papiPlaceholder.onRequest(player, params) : request);
    }

    public abstract String onRequest(Player player, String[] params);

    public void addSubPlaceholders(PAPIPlaceholder... placeholders) {
        Arrays.stream(placeholders)
                .filter(papiPlaceholder -> papiPlaceholder.getClass().isAnnotationPresent(SubPlaceholder.class))
                .forEach(subPlaceholders::add);
    }
}
