package club.deltapvp.deltacore.api.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Registry Annotation
 * This is the key to registering a class without needing to manually
 * initialize it.
 * <p>
 * Using RegistryType you can determine what certain
 * classes will register as.
 *
 * @since August 11th, 2021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Registry {

    RegistryType type();

    RegistryPriority priority() default RegistryPriority.NORMAL;

}
