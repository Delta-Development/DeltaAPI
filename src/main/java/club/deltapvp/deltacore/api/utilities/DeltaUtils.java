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

    /**
     * Post/Upload text to HasteBin
     * @param text The text you want to be uploaded
     * @param raw Will the text be raw?
     * @return HasteBin link
     */
    @SneakyThrows
    public String postToHasteBin(String text, boolean raw) {
        return new HasteBin().post(text, raw);
    }

    /**
     * Takes an Integer and turns it into a fancy string
     * @param i Input
     * @return Output (Fancy String!)
     */
    public String decimalFormat(int i) {
        return df.format(i);
    }

    /**
     * Takes a Double and turns it into a fancy string
     * @param i Input
     * @return Output (Fancy String!)
     */
    public String decimalFormat(double i) {
        return df.format(i);
    }

    /**
     * Takes a Float and turns it into a fancy string
     * @param i Input
     * @return Output (Fancy String!)
     */
    public String decimalFormat(float i) {
        return df.format(i);
    }

    /**
     * Takes an Object and turns it into a fancy string
     * @param i Input
     * @return Output (Fancy String!)
     */
    public String decimalFormat(Object i) {
        return df.format(i);
    }

    /**
     * Get all online players in a List format
     * @return Returns all online players
     */
    public List<Player> getOnlinePlayers() {
        List<? extends Player> collect = new ArrayList<Player>(Bukkit.getOnlinePlayers());
        return new ArrayList<>(collect);
    }

    /**
     * Executes a command as the Console
     * @param command Command input
     */
    public void executeConsoleCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    /**
     * Broadcast a message to the server
     * @param message Message input
     */
    public void broadcast(String message) {
        Bukkit.broadcastMessage(color(message));
    }

    /**
     * Colorize a String
     * @param input Input String
     * @return Chat Color formatted String
     */
    public String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    /**
     * Colorize a List of Strings
     * @param input Input StringList
     * @return Chat Color formatted List of Strings
     */
    public List<String> color(List<String> input) {
        List<String> returnValue = new ArrayList<>();
        input.forEach(s -> returnValue.add(color(s)));
        return returnValue;
    }

}
