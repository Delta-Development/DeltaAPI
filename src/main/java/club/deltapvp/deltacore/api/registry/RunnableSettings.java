package club.deltapvp.deltacore.api.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RunnableSettings {

    int tickSpeed();

    int delay() default 0;

    boolean async() default false;

}
