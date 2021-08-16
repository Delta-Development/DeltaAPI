package club.deltapvp.deltacore.api.bungeecord;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public abstract class BungeecordUtil {

    @Getter
    @Setter
    private static BungeecordUtil instance;

    public abstract void sendPlayerToServer(Player player, String server);

}
