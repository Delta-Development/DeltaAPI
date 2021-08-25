package club.deltapvp.deltacore.api.utilities.checker;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public interface UpdateChecker {

    void commence(JavaPlugin plugin, int resourceID, Consumer<String> function);

}
