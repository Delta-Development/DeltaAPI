package club.deltapvp.deltacore.api.utilities.file;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public interface FileLoader {

    File loadFile(JavaPlugin plugin, String name);

}
