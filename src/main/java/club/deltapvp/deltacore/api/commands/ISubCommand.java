package club.deltapvp.deltacore.api.commands;

import club.deltapvp.deltacore.api.DeltaAPI;
import club.deltapvp.deltacore.api.commands.annotation.CommandInfo;
import club.deltapvp.deltacore.api.utilities.message.Message;
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
 * @apiNote Must be added to a ICommand class in order to work!
 */
public abstract class ISubCommand {

    // subcommands of subcommands lol
    @Getter
    private final List<ISubCommand> subCommands = new ArrayList<>();
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

        DeltaAPI api = DeltaAPI.getInstance();
        cannotUseThis = api.createMessage("&cYou cannot use this!");
        commandDisabled = api.createMessage("&cThis command is currently disabled.");
        noPerm = api.createMessage("&cYou do not have permission to use this.");

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

        } else {
            throw new NullPointerException("Sub-Command does not have @CommandInfo annotation.");
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
     * Checks to see if a Player is online
     * @param name Name
     * @return Optional
     */
    public Optional<Player> getPlayer(String name) {
        return Optional.ofNullable(Bukkit.getPlayer(name));
    }

}
