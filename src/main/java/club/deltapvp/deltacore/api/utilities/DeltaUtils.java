package club.deltapvp.deltacore.api.utilities;

import club.deltapvp.deltacore.api.utilities.hastebin.HasteBin;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for some utility stuff that isn't large enough for
 * {@link club.deltapvp.deltacore.api.DeltaAPI}.
 *
 * @author Negative
 * @since September 27th, 2021
 */
@UtilityClass
public class DeltaUtils {

    private final DecimalFormat df;

    static {
        df = new DecimalFormat("###,###,###,###,###,###,###,###.##");
    }

    @SneakyThrows
    public String postToHasteBin(String text, boolean raw) {
        return new HasteBin().post(text, raw);
    }

    public String decimalFormat(int i) {
        return df.format(i);
    }

    public String decimalFormat(double i) {
        return df.format(i);
    }

    public String decimalFormat(float i) {
        return df.format(i);
    }

    public String decimalFormat(Object i) {
        return df.format(i);
    }

    public List<Player> getOnlinePlayers() {
        List<? extends Player> collect = new ArrayList<Player>(Bukkit.getOnlinePlayers());
        return new ArrayList<>(collect);
    }

    public void executeConsoleCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
