package club.deltapvp.deltacore.api.utilities.nametag;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * NameTag API
 *
 * @author Negative
 * @since October 4th, 2021
 *
 * This NameTag API allows you to have a prefix and/or a suffix above your name.
 *
 */
public abstract class NameTag {


    @Getter
    private BiFunction<Player, Player, String> prefix;
    @Getter @Setter
    private BiFunction<Player, Player, String> suffix;

    public abstract String handle();

    public abstract int priority();

    /**
     * Sets the prefix of the NameTag
     *
     * The first Player parameter is the Player that it will be applied on.
     * The second Player parameter is the Viewer that is looking at the Player
     * @param prefix The function of the prefix
     */
    public void setPrefix(BiFunction<Player, Player, String> prefix) {
        this.prefix = prefix;
    }

    /**
     * Sets the suffix of the NameTag
     *
     * The first Player parameter is the Player that it will be applied on.
     * The second Player parameter is the Viewer that is looking at the Player
     * @param suffix The function of the suffix
     */
    public void setSuffix(BiFunction<Player, Player, String> suffix) {
        this.suffix = suffix;
    }
}
