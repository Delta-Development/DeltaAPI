/*
 *       DeltaAPI is a Minecraft Java API.
 *       Copyright (C) 2021 DeltaDevelopment
 *
 *       This program is free software; you can redistribute it and/or modify
 *       it under the terms of the GNU General Public License as published by
 *       the Free Software Foundation; either version 2 of the License, or
 *       (at your option) any later version.
 *
 *       This program is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *       GNU General Public License for more details.
 */

package club.deltapvp.deltacore.api.commands.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CommandInfo
 * <p>
 * The ability to initialize your command without multiple
 * annotations or using the super methods
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

    // Name of the command
    // required
    String name();

    // Aliases of the command
    // not required
    String[] aliases() default "";

    // Permission of the command
    // not required
    String permission() default "";

    // Description of the command
    // not required

    /**
     * @apiNote Only works for {@link club.deltapvp.deltacore.api.commands.Command}!!
     */
    String description() default "";

    // Is this command console only?
    // not required
    boolean consoleOnly() default false;

    // Is this command player only?
    // not required
    boolean playerOnly() default false;

    // Is this command disabled?
    // not required
    boolean disabled() default false;

    String[] shortCommands() default "";

}
