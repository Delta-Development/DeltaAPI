package club.deltapvp.deltacore.api.utilities.input;

import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.BiConsumer;

public interface InputListener {

    void listen(UUID uuid, BiConsumer<Player, String> function);

}
