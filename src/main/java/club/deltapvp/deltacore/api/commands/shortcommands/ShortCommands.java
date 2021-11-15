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

package club.deltapvp.deltacore.api.commands.shortcommands;

import club.deltapvp.deltacore.api.commands.Command;
import club.deltapvp.deltacore.api.commands.SubCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public abstract class ShortCommands {

    @Getter @Setter
    private static ShortCommands instance;

    public abstract void addShortCommand(Command command, String[] commands);

    public abstract void addShortSubCommand(SubCommand command, String[] commands);

    public abstract Optional<Command> getCommand(String cmd);

    public abstract Optional<SubCommand> getSubCommand(String cmd);
}
