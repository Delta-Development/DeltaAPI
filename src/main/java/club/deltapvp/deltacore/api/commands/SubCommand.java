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

package club.deltapvp.deltacore.api.commands;

import club.deltapvp.deltacore.api.DeltaAPI;
import club.deltapvp.deltacore.api.commands.annotation.CommandInfo;
import club.deltapvp.deltacore.api.commands.shortcommands.ShortCommands;
import club.deltapvp.deltacore.api.utilities.message.iface.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * SubCommand
 *
 * @author Negative
 * @apiNote Must be added to a {@link Command} class in order to work!
 */
public abstract class SubCommand {

    // subcommands of subcommands lol
    @Getter
    private final List<SubCommand> subCommands = new ArrayList<>();
    private final Message cannotUseThis;
    private final Message commandDisabled;
    private final Message noPerm;
    @Setter
    @Getter
    private String argument;
    @Getter
    @Setter
    private List<String> aliases;
    @Getter
    @Setter
    private String permission = "";
    @Getter
    @Setter
    private boolean consoleOnly = false;
    @Getter
    @Setter
    private boolean playerOnly = false;
    @Getter
    @Setter
    private boolean disabled;


    public SubCommand() {
        this(null, null);
    }

    /**
     * SubCommand Constructor
     *
     * @param argument Argument of the SubCommand
     * @apiNote SubCommand argument and aliases are equalsIgnoreCase!
     * @apiNote There are no aliases for this constructor!
     */
    public SubCommand(String argument) {
        this(argument, Collections.emptyList());
    }

    /**
     * SubCommand Constructor
     *
     * @param argument Argument of the SubCommand
     * @param aliases  Aliases of the SubCommand
     * @apiNote SubCommand argument and aliases are equalsIgnoreCase!
     */
    public SubCommand(String argument, List<String> aliases) {
        this.argument = argument;
        this.aliases = aliases;

        DeltaAPI api = DeltaAPI.getInstance();
        cannotUseThis = api.createMessage("&cYou cannot use this!");
        commandDisabled = api.createMessage("&cThis command is currently disabled.");
        noPerm = api.createMessage("&cYou do not have permission to use this.");

        if (this.getClass().isAnnotationPresent(CommandInfo.class)) {
            CommandInfo annotation = this.getClass().getAnnotation(CommandInfo.class);
            setArgument(annotation.name());

            List<String> a = new ArrayList<>(Arrays.asList(annotation.aliases()));
            // There will always be an empty index even if no arguments are
            // set. So the way you identify if there are actual arguments in the command
            // is you check if the first index is empty.
            if (!a.get(0).isEmpty()) {
                setAliases(a);
            }

            if (!annotation.permission().isEmpty())
                setPermission(annotation.permission());

            List<String> shortCmds = new ArrayList<>(Arrays.asList(annotation.shortCommands()));
            if (!shortCmds.get(0).isEmpty()) {
                ShortCommands.getInstance().addShortSubCommand(this, annotation.shortCommands());
            }
        }
    }

    public void execute(CommandSender sender, String[] args) {
        // If the Command is disabled, send this message

        if (isDisabled()) {
            commandDisabled.send(sender);
            return;
        }

        if (isPlayerOnly() && !(sender instanceof Player)) {
            cannotUseThis.send(sender);
            return;
        }

        if (isConsoleOnly() && sender instanceof Player) {
            cannotUseThis.send(sender);
            return;
        }

        // If the permission node is not null and not empty
        // but, if the user doesn't have permission for the command
        // send this message
        if (!getPermission().isEmpty()) {
            if (!sender.hasPermission(getPermission())) {
                noPerm.send(sender);
                return;
            }
        }

        // Checks if the SubCommand SubCommands are empty (subcommand seption)
        // if so, execute regular command
        List<SubCommand> subCommands = getSubCommands();
        if (args.length == 0 || subCommands.isEmpty()) {
            runCommand(sender, args);
            return;
        }

        // Gets args 0
        String arg = args[0];
        // Removes args 0
        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        Optional<SubCommand> command = subCommands.stream().filter(subCmd -> {
            if (subCmd.getArgument().equalsIgnoreCase(arg))
                return true;

            List<String> aliases = subCmd.getAliases();
            if (aliases == null || aliases.isEmpty())
                return false;

            return aliases.contains(arg.toLowerCase());
        }).findFirst();

        if (command.isPresent())
            runSubCommand(command.get(), sender, newArgs);
        else
            runCommand(sender, args);
    }

    public abstract void runCommand(CommandSender sender, String[] args);

    /**
     * Adds SubCommand to the SubCommand's subcommands
     * SubCommand seption
     *
     * @param subCommands SubCommand(s)
     */
    public void addSubCommands(SubCommand... subCommands) {
        this.subCommands.addAll(Arrays.asList(subCommands));
    }

    /**
     * Runs a SubCommand
     *
     * @param subCommand SubCommand
     * @param sender     Sender
     * @param args       Arguments
     */
    private void runSubCommand(SubCommand subCommand, CommandSender sender, String[] args) {
        subCommand.execute(sender, args);
    }

    /**
     * Checks to see if a Player is online
     *
     * @param name Name
     * @return Optional
     */
    public Optional<Player> getPlayer(String name) {
        return Optional.ofNullable(Bukkit.getPlayer(name));
    }

}
