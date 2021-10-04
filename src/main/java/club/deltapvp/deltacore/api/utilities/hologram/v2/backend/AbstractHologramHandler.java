package club.deltapvp.deltacore.api.utilities.hologram.v2.backend;

import club.deltapvp.deltacore.api.DeltaPlugin;
import club.deltapvp.deltacore.api.utilities.hologram.v2.Hologram;
import org.bukkit.Location;

import java.util.List;

public abstract class AbstractHologramHandler {

    public abstract void create(Hologram hologram);

    public abstract void remove(Hologram hologram);

    public abstract void update(Hologram hologram);


}
