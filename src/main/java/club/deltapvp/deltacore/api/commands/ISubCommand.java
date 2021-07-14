package club.deltapvp.deltacore.api.commands;

import club.deltapvp.deltacore.api.commands.annotation.*;
import club.deltapvp.deltacore.api.utilities.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * SubCommand
 *
 * @author Negative
 * @apiNote Must be added to a ICommand class in order to work!
 */
public abstract class ISubCommand {

    // subcommands of subcommands lol
    @Getter
    private final List<ISubCommand> subCommands = new ArrayList<>();
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


    public ISubCommand() {
        this(null, null);
    }

    /**
     * SubCommand Constructor
     *
     * @param argument Argument of the SubCommand
     * @apiNote SubCommand argument and aliases are equalsIgnoreCase!
     * @apiNote There are no aliases for this constructor!
     */
    public ISubCommand(String argument) {
        this(argument, Collections.emptyList());
    }

    /**
     * SubCommand Constructor
     *
     * @param argument Argument of the SubCommand
     * @param aliases  Aliases of the SubCommand
     * @apiNote SubCommand argument and aliases are equalsIgnoreCase!
     */
    public ISubCommand(String argument, List<String> aliases) {
        this.argument = argument;
        this.aliases = aliases;

        boolean hasDisabled = getClass().isAnnotationPresent(Disabled.class);
        boolean hasPlayerOnly = getClass().isAnnotationPresent(PlayerOnly.class);
        boolean hasConsoleOnly = getClass().isAnnotationPresent(ConsoleOnly.class);
        boolean hasPerm = getClass().isAnnotationPresent(Permission.class);
        if (hasDisabled)
            setDisabled(true);

        if (hasPlayerOnly)
            setPlayerOnly(true);

        if (hasConsoleOnly)
            setConsoleOnly(true);

        if (hasPerm)
            setPermission(getClass().getAnnotation(Permission.class).perm());

        if (this.getClass().isAnnotationPresent(CommandInfo.class)) {
            CommandInfo annotation = this.getClass().getAnnotation(CommandInfo.class);
            setArgument(annotation.name());

            if (annotation.aliases().length != 0) {
                String[] alias = annotation.aliases();
                List<String> a = new ArrayList<>(Arrays.asList(alias));
                setAliases(a);
            }

            if (!annotation.permission().isEmpty())
                setPermission(annotation.permission());

        }
    }

    public void execute(CommandSender sender, String[] args) {
        // If the Command is disabled, send this message

        if (isDisabled()) {
            new Message("&cThis command is disabled.").send(sender);
            return;
        }

        if (isPlayerOnly()) {
            new Message("&cYou cannot use this.").send(sender);
            return;
        }

        if (isConsoleOnly()) {
            new Message("&cYou cannot use this.").send(sender);
            return;
        }

        // If the permission node is not null and not empty
        // but, if the user doesn't have permission for the command
        // send this message
        if (!getPermission().isEmpty()) {
            Message message = new Message("&cYou do not have permission to use this!");
            if (!sender.hasPermission(getPermission())) {
                message.send(sender);
                return;
            }
        }

        // Checks if the SubCommand SubCommands are empty (subcommand seption)
        // if so, execute regular command
        List<ISubCommand> subCommands = getSubCommands();
        if (subCommands.isEmpty()) {
            runCommand(sender, args);
            return;
        }

        // Checks if args is 0
        // if so, execute regular command
        if (args.length == 0) {
            runCommand(sender, args);
            return;
        }

        // Gets args 0
        String arg = args[0];
        // Removes args 0
        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        ISubCommand subCommand = subCommands.stream().filter(iSubCommand -> {
            if (iSubCommand.getArgument().equalsIgnoreCase(arg))
                return true;

            List<String> aliases = iSubCommand.getAliases();
            // Checking if aliases is null or empty, if so, skip
            if (aliases == null || aliases.isEmpty())
                return false;

            return aliases.contains(arg.toLowerCase());
        }).findFirst().orElse(null);

        if (subCommand != null) {
            runSubCommand(subCommand, sender, newArgs);
            return;
        }

        // If all else fails, run the command!
        runCommand(sender, args);
    }

    public abstract void runCommand(CommandSender sender, String[] args);

    /**
     * Adds SubCommand to the SubCommand's subcommands
     * SubCommand seption
     *
     * @param subCommands SubCommand(s)
     */
    public void addSubCommands(ISubCommand... subCommands) {
        this.subCommands.addAll(Arrays.asList(subCommands));
    }

    /**
     * Runs a SubCommand
     *
     * @param subCommand SubCommand
     * @param sender     Sender
     * @param args       Arguments
     */
    private void runSubCommand(ISubCommand subCommand, CommandSender sender, String[] args) {
        subCommand.execute(sender, args);
    }

    /**
     * @param s - String-name of the player we are trying to find
     * @return - Returns whether or not the player has been found
     */
    public boolean findPlayer(String s) {
        Player target = Bukkit.getServer().getPlayer(s);
        return target != null;
    }

}
