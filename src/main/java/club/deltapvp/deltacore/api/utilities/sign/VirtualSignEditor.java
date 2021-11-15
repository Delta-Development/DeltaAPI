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

package club.deltapvp.deltacore.api.utilities.sign;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiPredicate;

public interface VirtualSignEditor {

    AbstractSignMenu createSign(List<String> lines);

    AbstractSignMenu createSign(List<String> lines, boolean reOpenIfFail);

    AbstractSignMenu createSign(List<String> lines, boolean reOpenIfFail, BiPredicate<Player, String[]> response);

    AbstractSignMenu createSign(List<String> lines, BiPredicate<Player, String[]> response);
}
