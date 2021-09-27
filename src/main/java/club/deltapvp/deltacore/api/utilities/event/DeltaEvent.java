package club.deltapvp.deltacore.api.utilities.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * DeltaEvent
 *
 * @author Negative
 * @since September 19th, 2021
 * <p>
 * DeltaEvent is an extension off of {@link Event}
 * which does not require you to add the Handlers to your class
 * every time you make a new event, and also has a built-in call method
 * which calls the event.
 */
public abstract class DeltaEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public DeltaEvent call() {
        Bukkit.getPluginManager().callEvent(this);
        return this;
    }

}
