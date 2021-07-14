package club.deltapvp.deltacore.api.commands;

import club.deltapvp.deltacore.api.commands.annotation.*;
import club.deltapvp.deltacore.api.utilities.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiFunction;

public abstract class ICommand extends Command {
    @Getter
    private final List<ISubCommand> subCommands = new ArrayList<>();
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

        boolean hasInfo = getClass().isAnnotationPresent(CommandInfo.class);
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
            setPermissionNode(getClass().getAnnotation(Permission.class).perm());

        if (hasInfo) {
            CommandInfo annotation = getClass().getAnnotation(CommandInfo.class);
            if (annotation.consoleOnly())
                setConsoleOnly(true);

            if (annotation.playerOnly())
                setPlayerOnly(true);

            if (annotation.disabled())
                setDisabled(true);

            if (annotation.aliases().length != 0) {
                List<String> a = new ArrayList<>(Arrays.asList(annotation.aliases()));
                setAliases(a);
            }

            setName(annotation.name());

            if (!annotation.permission().isEmpty())
                setPermissionNode(annotation.permission());
        }

        registerCommand();
    }

    public abstract void onCommand(CommandSender sender, String[] args);

    public void onCommand(CommandSender sender, String label, String[] args) {
        onCommand(sender, args);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        // If the Command is disabled, send this message

        Message cannotUseThis = new Message("&cYou cannot use this");
        Message disabled = new Message("&cThis command is disabled.");
        Message noPerm = new Message("&cYou do not have permission to use this!");

        if (isDisabled()) {
            disabled.send(sender);
            return true;
        }

        if (isPlayerOnly()) {
            if (!(sender instanceof Player)) {
                cannotUseThis.send(sender);
                return true;
            }
        }

        if (isConsoleOnly()) {
            if (sender instanceof Player) {
                cannotUseThis.send(sender);
                return true;
            }
        }

        // If the permission node is not null and not empty
        // but, if the user doesn't have permission for the command
        // send this message
        if (!getPermissionNode().isEmpty()) {
            if (!sender.hasPermission(getPermissionNode())) {
                noPerm.send(sender);
                return true;
            }
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
     * Command Register
     */
    @SuppressWarnings("unchecked")
    private void registerCommand() {

        try {
            Server server = Bukkit.getServer();
            Field field = server.getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(server);

            String name = this.getName();

            Command command = commandMap.getCommand(name);
            if (command != null) {
                Map<String, Command> map;
                Field commandField = commandMap.getClass().getDeclaredField("knownCommands");
                commandField.setAccessible(true);
                map = (Map<String, Command>) commandField.get(commandMap);

                command.unregister(commandMap);
                map.remove(name);
                this.getAliases().forEach(map::remove);
            }

            commandMap.register(name, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param s - String-name of the player we are trying to find
     * @return - Returns whether or not the player has been found
     */
    public boolean findPlayer(String s) {
        Player target = Bukkit.getServer().getPlayer(s);
        return target != null;
    }

    /**
     * @param sender - Sender of the command. Usually a player
     * @param perm   - Permission node
     * @return - Returns whether or not the player has the permission provided in the "perm" String
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
        if (completer == null) {
            String lastWord = args[args.length - 1];
            Player senderPlayer = sender instanceof Player ? (Player) sender : null;
            ArrayList<String> matchedPlayers = new ArrayList<String>();
            for (Player player : sender.getServer().getOnlinePlayers()) {
                String name = player.getName();
                if ((sender == null || senderPlayer.canSee(player)) && StringUtil.startsWithIgnoreCase(name, lastWord)) {
                    matchedPlayers.add(name);
                }
            }

            matchedPlayers.sort(String.CASE_INSENSITIVE_ORDER);
            return matchedPlayers;
        }
        return this.completer.onTabComplete(sender, this, alias, args);
    }
}
