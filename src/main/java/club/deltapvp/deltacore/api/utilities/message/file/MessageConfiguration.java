package club.deltapvp.deltacore.api.utilities.message.file;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Message Configuration
 *
 * @author Negative
 * @since September 28th, 2021
 * <p>
 * Configuration Manager class for {@link Message}
 */
public class MessageConfiguration {

    private final File file;
    private FileConfiguration config;

    /**
     * Initialize the message and any default values
     *
     * @param plugin   Plugin instance
     * @param id       ID of the Message which will be used to identify and/or create its YAML file.
     * @param defaults Any default values to be printed in the YAML on creation
     */
    @SneakyThrows
    public MessageConfiguration(DeltaPlugin plugin, String id, String... defaults) {
        plugin.getDataFolder().mkdir();
        file = new File(plugin.getDataFolder() + "/messages/", id + ".yml");
        file.getParentFile().mkdir();

        config = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            file.createNewFile();

            List<String> content = new ArrayList<>(Arrays.asList(defaults));

            config.set("content", content);

            config.save(file);
        }
    }

    /**
     * Saves the YAML file in case of any changes
     */
    @SneakyThrows
    public void save() {
        config.save(file);
    }

    /**
     * Gets the {@link FileConfiguration} class to be modified
     *
     * @return File Configuration
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Reloads the configuration in case of any changes
     */
    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }


}
