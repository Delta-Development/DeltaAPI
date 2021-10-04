package club.deltapvp.deltacore.api.utilities.hologram.v2.backend;

import org.bukkit.Location;

import java.util.List;

public abstract class AbstractHologramHandler {

    public abstract String handle();

    public abstract List<String> lines();

    public abstract Location location();

    public abstract void load();

    public abstract void remove();


}
