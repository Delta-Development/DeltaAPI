package club.deltapvp.deltacore.api.utilities.time;

import club.deltapvp.deltacore.api.DeltaAPI;

/**
 * @deprecated Please use {@link TimeUtil}
 * @apiNote This class and method {@link DeltaAPI#getTimeConverter()} will be removed in upcoming versions
 */
@Deprecated
public interface TimeConversion {

    String format(long l1, long l2);

    String format(long l1, long l2, boolean shortened);

    Long fromString(String string);

}
