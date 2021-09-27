package club.deltapvp.deltacore.api.utilities;

import club.deltapvp.deltacore.api.utilities.hastebin.HasteBin;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * This class is used for some utility stuff that isn't large enough for
 * {@link club.deltapvp.deltacore.api.DeltaAPI}.
 *
 * @author Negative
 * @since September 27th, 2021
 */
@UtilityClass
public class DeltaUtils {

    @SneakyThrows
    public String postToHasteBin(String text, boolean raw) {
        return new HasteBin().post(text, raw);
    }


}
