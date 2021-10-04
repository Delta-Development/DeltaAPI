package club.deltapvp.deltacore.api.utilities.hologram.v2.backend;

import club.deltapvp.deltacore.api.DeltaPlugin;
import club.deltapvp.deltacore.api.utilities.hologram.v2.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class AbstractHologramHandler {

    public abstract void create(Player player, Hologram hologram, boolean all);

    public abstract void remove(Player player, Hologram hologram, boolean all);

    public abstract void update(Player player, Hologram hologram, boolean all);


}
