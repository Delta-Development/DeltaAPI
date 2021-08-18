package club.deltapvp.deltacore.api.registry;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class ClassRegistry {

    @Getter
    @Setter
    private static ClassRegistry instance;

    public abstract void register(JavaPlugin plugin);
}
