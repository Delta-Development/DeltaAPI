package club.deltapvp.deltacore.api.utilities.debug;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Debug Manager
 *
 * @author Negative
 * @since September 28th, 2021
 *
 * Debug Manager is a manager for the {@link Debug} system.
 * Developers can register their plugin to the debugger, and it will create or add
 * to the debug.yml file with their plugin name and a value.
 *
 * True meaning debugging for that plugin is enabled, False meaning
 * debugging is false for that plugin
 *
 * Although the code is kind of dirt, I might improve this at a later date.
 */
@UtilityClass
public class DebugManager {

    private DebugConfiguration configuration;

    public void registerPlugin(DeltaPlugin plugin) {
        attemptCreateFile(plugin);

        FileConfiguration config = configuration.getConfig();
        Object o = config.get(plugin.getName());
        if (o == null) {
            config.set(plugin.getName(), false);
            configuration.save();
            configuration.reload();
        }
    }

    public void toggleDebug(DeltaPlugin plugin, boolean value) {
        FileConfiguration config = configuration.getConfig();
        config.set(plugin.getName(), !getCurrentState(plugin));
        configuration.save();
        configuration.reload();
    }

    public boolean getCurrentState(DeltaPlugin plugin) {
        FileConfiguration config = configuration.getConfig();
        return config.getBoolean(plugin.getName(), false);
    }

    private void attemptCreateFile(DeltaPlugin plugin) {
        configuration = new DebugConfiguration(plugin);
    }
}
