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

package club.deltapvp.api.datastructure;

import java.util.Objects;

/**
 * TriConsumer
 * Primarily meant for {@link BiMap}.
 *
 * @param <T>  Type
 * @param <U>  Value 1
 * @param <U1> Value 2
 */
@FunctionalInterface
public interface TriConsumer<T, U, U1> {

    void accept(T t, U u, U1 u1);

    default TriConsumer<T, U, U1> andThen(TriConsumer<? super T, ? super U, ? super U1> after) {
        Objects.requireNonNull(after);

        return (l, r, r1) -> {
            accept(l, r, r1);
            after.accept(l, r, r1);
        };
    }
}
