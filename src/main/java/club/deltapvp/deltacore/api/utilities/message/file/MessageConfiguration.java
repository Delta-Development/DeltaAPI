package club.deltapvp.deltacore.api.utilities.message.file;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageConfiguration {

    private final File file;
    private FileConfiguration config;

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
