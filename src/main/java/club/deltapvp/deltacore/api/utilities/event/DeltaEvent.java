package club.deltapvp.deltacore.api.utilities.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class DeltaEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
