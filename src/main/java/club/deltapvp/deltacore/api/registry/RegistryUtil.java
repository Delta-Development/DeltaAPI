package club.deltapvp.deltacore.api.registry;

import lombok.experimental.UtilityClass;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RegistryUtil {

    /**
     * Registers and initializes all classes with the @Registry annotation.
     *
     * @param plugin JavaPlugin
     */
    public void register(JavaPlugin plugin) {
        List<Class<?>> classes = ClassLoaderUtil.getClasses(plugin, Registry.class.getName());

        RegistryType[] values = RegistryType.values();

        List<RegistryType> collect = Arrays.stream(values)
                .sorted(Comparator.comparingInt(RegistryType::getPriority).reversed())
                .collect(Collectors.toList());

        collect.forEach(type -> {
            classes.stream().filter(aClass -> aClass.getDeclaredAnnotation(Registry.class).type().equals(type))
                    .sorted(Comparator.comparingInt(value -> value.getDeclaredAnnotation(Registry.class).priority().getPriority()))
                    .forEach(aClass -> type.register(plugin, aClass));
        });
    }
}
