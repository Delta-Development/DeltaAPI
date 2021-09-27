package club.deltapvp.deltacore.api.commands;

import club.deltapvp.deltacore.api.DeltaAPI;
import club.deltapvp.deltacore.api.commands.annotation.CommandInfo;
import club.deltapvp.deltacore.api.utilities.message.iface.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;
import java.util.function.BiFunction;

public abstract class ICommand extends Command {
    @Getter
    private final List<ISubCommand> subCommands = new ArrayList<>();
    private final Message cannotUseThis;
    private final Message commandDisabled;
    private final Message noPerm;
    @Getter
    @Setter
    public boolean consoleOnly = false;
    @Getter
    @Setter
    public boolean playerOnly = false;
    @Getter
    @Setter
    public boolean disabled = false;
    @Getter
    @Setter
    public String permissionNode = "";
    private TabCompleter completer;

    public ICommand() {
        this("1");
    }

    public ICommand(String name) {
        this(name, "", Collections.emptyList());
    }

    public ICommand(String name, String description) {
        this(name, description, Collections.emptyList());
    }

    public ICommand(String name, List<String> aliases) {
        this(name, "", aliases);
    }

    public ICommand(String name, String description, List<String> aliases) {
        super(name, description, "/" + name, aliases);

        DeltaAPI api = DeltaAPI.getInstance();
        cannotUseThis = api.createMessage("&cYou cannot use this!");
        commandDisabled = api.createMessage("&cThis command is currently disabled.");
        noPerm = api.createMessage("&cYou do not have permission to use this.");

        boolean hasInfo = getClass().isAnnotationPresent(CommandInfo.class);
        if (hasInfo) {
            CommandInfo annotation = getClass().getAnnotation(CommandInfo.class);
            setName(annotation.name());

            if (annotation.consoleOnly())
                setConsoleOnly(true);

            if (annotation.playerOnly())
                setPlayerOnly(true);

            if (annotation.disabled())
                setDisabled(true);

            List<String> a = new ArrayList<>(Arrays.asList(annotation.aliases()));
            // There will always be an empty index even if no arguments are
            // set. So the way you identify if there are actual arguments in the command
            // is you check if the first index is empty.
            if (!a.get(0).isEmpty()) {
                setAliases(a);
            }

            if (!annotation.permission().isEmpty())
                setPermissionNode(annotation.permission());
        } else {
            throw new NullPointerException("Command `" + getClass().getSimpleName() + "` does not have @CommandInfo annotation.");
        }

    }

    public abstract void onCommand(CommandSender sender, String[] args);

    public void onCommand(CommandSender sender, String label, String[] args) {
        onCommand(sender, args);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        // If the Command is disabled, send this message

        if (isDisabled()) {
            commandDisabled.send(sender);
            return true;
        }

        if (isPlayerOnly() && !(sender instanceof Player)) {
            cannotUseThis.send(sender);
            return true;
        }

        if (isConsoleOnly() && sender instanceof Player) {
            cannotUseThis.send(sender);
            return true;
        }

        // If the permission node is not null and not empty
        // but, if the user doesn't have permission for the command
        // send this message
        if (!getPermissionNode().isEmpty() && !sender.hasPermission(getPermissionNode())) {
            noPerm.send(sender);
            return true;
        }


        // If there are no SubCommands for this Command
        // execute the regular command
        List<ISubCommand> subCommands = getSubCommands();
        if (subCommands.isEmpty()) {
            onCommand(sender, label, args);
            return true;
        }

        // If there are no args, execute the regular command
        if (args.length == 0) {
            onCommand(sender, label, args);
            return true;
        }

        // Gets argument 0
        String arg = args[0];
        // Removes argument 0
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
            return true;
        }
        // If all else fails, execute the regular command
        onCommand(sender, label, args);

        return true;
    }

    /**
     * Run SubCommand Function
     *
     * @param subCommand SubCommand
     * @param sender     Player/Sender
     * @param args       Arguments
     */
    private void runSubCommand(ISubCommand subCommand, CommandSender sender, String[] args) {
        subCommand.execute(sender, args);
    }

    /**
     * @param sender - Sender of the command. Usually a player
     * @param perm   - Permission node
     * @return - Returns whether the player has the permission provided in the "perm" String
     */
    public boolean hasPermission(CommandSender sender, String perm) {
        Server server = Bukkit.getServer();
        Player p = server.getPlayer(sender.getName());
        if (p == null) {
            return server.getConsoleSender().hasPermission(perm);
        } else {
            return p.hasPermission(perm);
        }
    }

    /**
     * Checks if a player is online
     *
     * @param name Player Name
     * @return Optional Player
     */
    public Optional<Player> getPlayer(String name) {
        return Optional.ofNullable(Bukkit.getPlayer(name));
    }

    /**
     * Add one or more SubCommands to
     * a command
     *
     * @param subCommands SubCommand(s)
     */
    public void addSubCommands(ISubCommand... subCommands) {
        this.subCommands.addAll(Arrays.asList(subCommands));
    }

    public void setTabComplete(BiFunction<CommandSender, String[], List<String>> function) {
        this.completer = (sender, command, alias, args) -> {
            if (alias.equalsIgnoreCase(getName()) || getAliases().contains(alias.toLowerCase())) {
                return function.apply(sender, args);
            }
            return null;
        };
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (completer == null || this.completer.onTabComplete(sender, this, alias, args) == null) {
            String lastWord = args[args.length - 1];
            Player senderPlayer = sender instanceof Player ? (Player) sender : null;
            ArrayList<String> matchedPlayers = new ArrayList<>();
            sender.getServer().getOnlinePlayers().stream()
                    .filter(player -> senderPlayer == null || senderPlayer.canSee(player) && StringUtil.startsWithIgnoreCase(player.getName(), lastWord))
                    .forEach(player -> matchedPlayers.add(player.getName()));

            matchedPlayers.sort(String.CASE_INSENSITIVE_ORDER);
            return matchedPlayers;
        }
        return this.completer.onTabComplete(sender, this, alias, args);
    }
}
