package club.deltapvp.deltacore.api.utilities.hologram;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.function.BiConsumer;

public interface Hologram {

    String getStringID();

    int getIntID();

    LinkedList<String> getLines();

    LinkedList<ArmorStand> getEntities();

    void update();

    void remove();

    void load();

    Location getLocation();

    void setLocation(Location location);

    void onInteraction(BiConsumer<Player, HologramInteractEvent> function);

    BiConsumer<Player, HologramInteractEvent> getInteractionFunction();

    void addLine(String text);

    void setLine(int index, String text);

    void setSpaceInBetween(double number);


}
