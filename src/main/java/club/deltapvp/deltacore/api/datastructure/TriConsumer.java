package club.deltapvp.deltacore.api.datastructure;

import java.util.Objects;

/**
 * TriConsumer
 * Primarily meant for BiMap.
 *
 * @param <T>
 * @param <U>
 * @param <U1>
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
