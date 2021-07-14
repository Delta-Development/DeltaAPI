package club.deltapvp.deltacore.api.utilities;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.BiConsumer;

public abstract class InputListener {

    @Getter
    @Setter
    private static InputListener instance;

    public abstract void listen(UUID uuid, BiConsumer<Player, String> function);

}
