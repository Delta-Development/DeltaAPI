package club.deltapvp.deltacore.api.utilities.message.iface;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Interface based Message class
 *
 * @author Negative
 * <p>
 * This class is an interfaced based version of {@link club.deltapvp.deltacore.api.utilities.message.object.Message}
 * which is an older legacy version of this one.
 * <p>
 * This one is typically recommended, but both work and will be updated!
 */
public interface Message {

    void send(CommandSender sender, String... replacers);

    void send(CommandSender sender);

    void send(List<Player> players, String... replacers);

    void send(List<Player> players);

    void broadcast(String... replacers);

    void broadcast();

    String getMessage();

    String getMessage(String... replacers);

}
