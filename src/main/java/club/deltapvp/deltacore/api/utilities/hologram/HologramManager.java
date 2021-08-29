package club.deltapvp.deltacore.api.utilities.hologram;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;

public interface HologramManager {

    Hologram createHologram(Location location, int id, String... text);

    Hologram createHologram(Location location, String id, String... text);

    Hologram createHologram(Location location, int id, double spaces, String... text);

    Hologram createHologram(Location location, String id, double spaces, String... text);

    Hologram getHologram(String id);

    Hologram getHologram(int id);

    Hologram getHologram(Location location);

    Hologram getHologram(ArmorStand armorStand);

    ArrayList<Hologram> getHolograms();

    void removeHologram(int id);

    void removeHologram(String id);

    void removeHologram(Hologram hologram);

}
