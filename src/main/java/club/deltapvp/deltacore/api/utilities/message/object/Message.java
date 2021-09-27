package club.deltapvp.deltacore.api.utilities.message.object;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Object based Message class
 *
 * @author Negative
 * <p>
 * This is the object based message class, meaning you can just do
 * new Message("my message").send(commandSender); without needing to reference
 * {@link club.deltapvp.deltacore.api.DeltaAPI} and create a message.
 * <p>
 * Although {@link club.deltapvp.deltacore.api.utilities.message.iface.Message} is recommended, but
 * this will be kept up to date!
 */
public class Message {

    @Getter
    private final String initial;
    @Getter
    @Setter
    private String message;

    /**
     * Message Constructor (as String)
     *
     * @param msg Message
     */
    public Message(String... msg) {
        String actual = String.join("\n", msg);
        setMessage(actual);
        this.initial = actual;
    }

    /**
     * Replacer
     * <p>
     * Simply replaces object1 with object2.
     * Could be a string, int, double, whatever
     *
     * @param o1 - Object 1
     * @param o2 - Object 2
     * @return - Returns replaced value
     */
    public Message replace(Object o1, Object o2) {
        if (o2 instanceof Integer || o2 instanceof Double || o2 instanceof Long) {
            DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,###,###.##");
            o2 = df.format(o2);
        }

        String newMSG = this.message;

        newMSG = newMSG.replaceAll(String.valueOf(o1), String.valueOf(o2));

        setMessage(newMSG);
        return this;
    }

    /**
     * Send Message
     * <p>
     * Sends the message to a CommandSender.
     * Usually a player or console
     *
     * @param sender - Sender
     */
    public void send(CommandSender sender) {
        if (this.message.contains("\n")) {
            String[] msg = getMessage().split("\n");
            for (String s : msg) {
                sender.sendMessage(translate(s));
            }
            this.message = initial;
            return;
        }
        sender.sendMessage(translate(getMessage()));
        setMessage(getInitial());
    }

    /**
     * Send Message to a list of players
     * <p>
     * Simply sends the message to a list of players
     *
     * @param players - List of players
     */
    public void send(List<Player> players) {
        players.forEach(this::send);
    }

    /**
     * Broadcast message
     * <p>
     * Simply broadcasts the message for
     * all to see!
     */
    public void broadcast() {
        if (getMessage().contains("\n")) {
            String[] msg = getMessage().split("\n");
            for (String s : msg) {
                Bukkit.broadcastMessage(translate(s));
            }
            setMessage(getInitial());
            return;
        }
        Bukkit.broadcastMessage(translate(getMessage()));
        setMessage(getInitial());
    }

    private String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
