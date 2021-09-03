package club.deltapvp.deltacore.api.utilities.sign;

import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiPredicate;

public abstract class AbstractSignMenu {

    public final List<String> text;

    public BiPredicate<Player, String[]> response;
    public boolean reopenIfFail;

    public BlockPosition position;

    public boolean forceClose;

    public AbstractSignMenu(List<String> text) {
        this.text = text;
    }

    public abstract AbstractSignMenu reOpenIfFail(boolean value);

    public abstract AbstractSignMenu response(BiPredicate<Player, String[]> response);

    public abstract void open(Player player);

    public abstract void close(Player player, boolean force);

    public void close(Player player) {
        this.close(player, false);
    }

    private String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

}
