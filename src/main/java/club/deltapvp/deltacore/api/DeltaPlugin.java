package club.deltapvp.deltacore.api;

import club.deltapvp.deltacore.api.utilities.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public abstract class DeltaPlugin extends JavaPlugin {

    @Override
    public abstract void onEnable();

    @Override
    public abstract void onDisable();

    public void loadFiles(JavaPlugin plugin, String... names) {
        Arrays.stream(names).forEach(name -> {
            File file = new File(plugin.getDataFolder(), name);
            FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

            if (!file.exists())
                FileUtils.loadResource(plugin, name);

            try {
                fileConfig.load(file);
            } catch (Exception e3) {
                e3.printStackTrace();
            }

            fileConfig.getKeys(false).forEach(priceString -> fileConfig.set(priceString, fileConfig.getString(priceString)));
        });
    }
}
