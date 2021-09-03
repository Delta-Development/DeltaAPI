package club.deltapvp.deltacore.api.utilities.sign;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiPredicate;

public interface VirtualSign {

    void setText(List<String> lines);

    void reOpenIfFail(boolean b);

    void open(Player player);

    void close(Player player);

    void setResponse(BiPredicate<Player, String[]> response);
}
