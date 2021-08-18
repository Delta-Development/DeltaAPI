package club.deltapvp.deltacore.api.registry;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class ClassLoader {

    @Getter @Setter
    private static ClassLoader instance;

    public abstract List<Class<?>> getClasses(JavaPlugin plugin, String annotationName);


}
