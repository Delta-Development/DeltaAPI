package club.deltapvp.deltacore.api.utilities.hologram.v2.backend;

import club.deltapvp.deltacore.api.DeltaPlugin;
import club.deltapvp.deltacore.api.utilities.hologram.v2.Hologram;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class HologramManager {

    @Getter
    @Setter
    private static HologramManager instance;

    public abstract void registerHologram(DeltaPlugin plugin, Hologram hologram);

    public abstract void updateHologram(String hologramHandle, Player player, boolean all);

    public abstract void updateHologram(Hologram hologram, Player player, boolean all);

    public abstract void removeHologram(String hologramHandle, Player player, boolean all);

    public abstract void removeHologram(Hologram hologram, Player player, boolean all);

    public abstract List<Hologram> getHolograms(DeltaPlugin plugin);
}
