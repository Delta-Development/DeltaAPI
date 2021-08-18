package club.deltapvp.deltacore.api.registry;

import club.deltapvp.deltacore.api.commands.ICommand;
import club.deltapvp.deltacore.api.utilities.version.VersionChecker;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * RegistryType
 * One of the essential parts to registering a class
 * without needing to manually initialize it.
 * <p>
 * This will determine what type of registry this is.
 */
public enum RegistryType {

    // Declares the class as an Implementation class
    // which means it has some importance to the structure
    // of the project. Such as data, or object management.
    IMPLEMENTATION("Implementation", 5),

    // Declares the class a Listener
    // and once initializes will register it as
    // a bukkit listener.
    LISTENER("Listener", 4),

    // Declares the class a Command
    // and once it's initialized it will
    // register as a command and print out
    // in console.
    COMMAND("Command", 3),


    RUNNABLE("Runnable", 2),

    // Declares the class as Other. Which has little
    // to no importance.
    OTHER("", 1);
    @Getter
    private final String name;
    @Getter
    private final int priority;

    /**
     * @param name     Name of the type. This will be printed to console when registered
     * @param priority Priority of registration. Highest to Lowest
     */
    RegistryType(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     * Registers the class. Supports special cases such as Listeners or Kits.
     *
     * @param clazz Class
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void register(JavaPlugin plugin, Class<?> clazz) {
        switch (this) {
            case LISTENER: {
                Bukkit.getPluginManager().registerEvents((Listener) clazz.newInstance(), plugin);
                break;
            }

            case RUNNABLE: {
                if (!clazz.isAnnotationPresent(RunnableSettings.class))
                    return;

                RunnableSettings settings = clazz.getAnnotation(RunnableSettings.class);
                boolean async = settings.async();
                int tick = settings.tickSpeed();
                int delay = settings.delay();

                BukkitRunnable runnable = (BukkitRunnable) clazz.newInstance();
                if (async)
                    runnable.runTaskTimerAsynchronously(plugin, delay, tick);
                else
                    runnable.runTaskTimer(plugin, delay, tick);

                break;
            }


            case COMMAND: {

                Object c = clazz.newInstance();
                ICommand cmd = (ICommand) c;

                try {
                    Server server = Bukkit.getServer();
                    Field field = server.getClass().getDeclaredField("commandMap");
                    field.setAccessible(true);
                    CommandMap commandMap = (CommandMap) field.get(server);

                    String name = this.getName();

                    Command command = commandMap.getCommand(name);
                    if (command != null) {
                        Map<String, Command> map;
                        if (VersionChecker.getInstance().isLegacy()) {
                            Field commandField = commandMap.getClass().getDeclaredField("knownCommands");
                            commandField.setAccessible(true);
                            map = (Map<String, Command>) commandField.get(commandMap);
                        } else {
                            map = (Map<String, Command>) commandMap.getClass().getDeclaredMethod("getKnownCommands").invoke(commandMap);
                        }
                        command.unregister(commandMap);
                        map.remove(name);
                        cmd.getAliases().forEach(map::remove);
                    }

                    commandMap.register(name, cmd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                try {
//                    Server server = Bukkit.getServer();
//                    Field field = server.getClass().getDeclaredField("commandMap");
//                    field.setAccessible(true);
//                    CommandMap commandMap = (CommandMap) field.get(server);
//                    commandMap.register(cmd.getName(), cmd);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            default: {
                clazz.newInstance();
            }
        }
        send(plugin, clazz);
    }

    /**
     * Prints the log to console that the class has been initialized.
     *
     * @param clazz Class
     */
    private void send(JavaPlugin plugin, Class<?> clazz) {
        String s = (getName().isEmpty() ? "" : " " + getName());
        System.out.println("[" + plugin.getDescription().getName() + "] Registered" + s + " `" + clazz.getSimpleName() + "`");
    }
}
