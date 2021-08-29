package club.deltapvp.deltacore.api.utilities.hologram;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
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

    void setLocation(Location location);

    Location getLocation();

    void onInteraction(BiConsumer<Player, HologramInteractEvent> function);

    void addLine(String text);

    void setLine(int index, String text);

    void setSpaceInBetween(double number);

}
