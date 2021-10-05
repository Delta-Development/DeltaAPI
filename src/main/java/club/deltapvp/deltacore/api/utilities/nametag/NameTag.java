package club.deltapvp.deltacore.api.utilities.nametag;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.function.Function;

public abstract class NameTag {

    @Getter @Setter
    private Function<Player, String> prefix;
    @Getter @Setter
    private Function<Player, String> suffix;

    public abstract String handle();

    public abstract int priority();


}
