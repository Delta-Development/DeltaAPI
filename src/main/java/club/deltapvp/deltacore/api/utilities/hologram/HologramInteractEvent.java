package club.deltapvp.deltacore.api.utilities.hologram;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HologramInteractEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final Player player;
    @Getter
    private final Hologram hologram;
    @Getter
    private final boolean attacked;
    @Getter
    @Setter
    private boolean cancel;

    public HologramInteractEvent(Player player, Hologram hologram, boolean attacked) {
        this.player = player;
        this.hologram = hologram;
        this.attacked = attacked;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
