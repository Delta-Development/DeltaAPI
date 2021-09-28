package club.deltapvp.deltacore.api.utilities.debug;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Debug Configuration
 *
 * @author Negative
 * @since September 28th, 2021
 *
 * This is the configuration for {@link Debug} and {@link DebugManager} and when a new instance
 * of this class is called it will check to see if the file "debug.yml" exists, if not, create a new
 * file.
 *
 * The file is where the debug data for each registered plugin is stored.
 */
public class DebugConfiguration {

    private File file;
    private YamlConfiguration config;

    @SneakyThrows
    public DebugConfiguration(DeltaPlugin plugin) {
        File temp = plugin.getDataFolder();
        String path = null;
        for (int i = 0; i < 10; i++) {
            if (temp.getParentFile().isDirectory() && temp.getParentFile().getName().equalsIgnoreCase("plugins")) {
                path = temp.getParentFile().getParentFile().getPath();
                break;
            }

            temp = temp.getParentFile();
        }

        if (path == null) {
            System.out.println("Something went wrong while trying to make the debug.yml file!");
            return;
        }

        file = new File(path, "debug.yml");
        if (!file.exists()) {
            file.createNewFile();

        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    @SneakyThrows
    public void save() {
        config.save(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
