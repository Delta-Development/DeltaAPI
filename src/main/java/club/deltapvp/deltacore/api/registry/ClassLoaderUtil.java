package club.deltapvp.deltacore.api.registry;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import lombok.experimental.UtilityClass;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
@Deprecated
public class ClassLoaderUtil {

    /**
     * Get classes with a specific annotation name
     *
     * @param annotationName Annotation Name
     * @return List of classes
     * @author Raymond#0001 (modified by Negative)
     */
    public List<Class<?>> getClasses(JavaPlugin plugin, String annotationName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            String[] pack = plugin.getClass().getPackage().getName().split("\\.");
            try (ScanResult result = new ClassGraph().enableAllInfo().acceptPackages(pack[0] + "." + pack[1]).enableClassInfo().scan()) {
                List<Class<?>> clazzes = result.getClassesWithAnnotation(annotationName).loadClasses();
                classes.addAll(clazzes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }


}
