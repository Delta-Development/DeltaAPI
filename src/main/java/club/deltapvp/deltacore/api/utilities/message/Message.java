package club.deltapvp.deltacore.api.utilities.message;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface Message {

    void send(CommandSender sender, String... replacers);

    void send(CommandSender sender);

    void send(List<Player> players, String... replacers);

    void send(List<Player> players);

    void broadcast(String... replacers);

    void broadcast();

}
