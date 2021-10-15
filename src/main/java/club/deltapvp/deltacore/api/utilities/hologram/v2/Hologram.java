package club.deltapvp.deltacore.api.utilities.hologram.v2;

import org.bukkit.Location;

import java.util.List;
import java.util.function.Consumer;

/**
 * Packet Based Hologram
 *
 * @author Negative
 * @since October 5th, 2021
 */
public abstract class Hologram {

    private Consumer<HologramInteractEvent> hologramInteractEventConsumer;

    public abstract String handle();

    public abstract List<String> lines();

    public abstract Location location();

    public void setHologramInteractEvent(Consumer<HologramInteractEvent> function) {
        this.hologramInteractEventConsumer = function;
    }

    public void onHologramInteract(HologramInteractEvent event) {
        if (hologramInteractEventConsumer == null)
            return;

        hologramInteractEventConsumer.accept(event);
    }
}
