package club.deltapvp.deltacore.api.utilities.message.file;

import club.deltapvp.deltacore.api.DeltaPlugin;
import club.deltapvp.deltacore.api.utilities.DeltaUtils;
import lombok.Data;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * File Based Message Utility
 *
 * @author Negative
 * @since September 28th, 2021
 * <p>
 * This is a message system which allows a plugin to have a
 * configurable message system without the need for developers to put in the extra
 * effort to make said messages configurable.
 * <p>
 * All messages created in the plugin using this will have a file with its ID created under
 * the messages folder
 * <p>
 * Example:
 * no-permission.yml
 * <p>
 * That yaml file will contain a StringList of the message, which can be modified by the
 * server owner
 */
@Data
public class Message {

    private final String id;
    private final MessageConfiguration configuration;
    private final LinkedList<String> defaultContent;
    private List<String> modifiableContent = new ArrayList<>();

    /**
     * Initialize the message and any default values
     *
     * @param plugin        Plugin instance
     * @param id            ID of the Message which will be used to identify and/or create its YAML file.
     * @param defaultValues Any default values to be printed in the YAML on creation
     */
    public Message(DeltaPlugin plugin, String id, String... defaultValues) {
        this.id = id;

        configuration = new MessageConfiguration(plugin, id, defaultValues);
        defaultContent = new LinkedList<>();

        defaultContent.addAll(configuration.getConfig().getStringList("content"));
        reset();

        MessageManager.registerMessage(plugin, this);
    }

    /**
     * Replaces {@param s} with {@param s1} in the entire message
     *
     * @param s  Value to be replaced
     * @param s1 Replaced value
     * @return Continuation of {@link Message}
     */
    public Message replace(String s, String s1) {
        List<String> currentContent = getModifiableContent();
        List<String> modifiedContent = new ArrayList<>();
        currentContent.forEach(line -> {
            line = line.replaceAll(s, s1);
            modifiedContent.add(line);
        });
        setModifiableContent(modifiedContent);
        return this;
    }

    /**
     * Sends the message to a {@link CommandSender}.
     * This can either be a {@link org.bukkit.entity.Player} or a
     * {@link org.bukkit.command.ConsoleCommandSender}.
     *
     * @param sender Command Sender
     */
    public void send(CommandSender sender) {
        modifiableContent.forEach(s -> sender.sendMessage(DeltaUtils.color(s)));
        reset();
    }

    /**
     * Broadcasts the message to the entire server.
     */
    public void broadcast() {
        modifiableContent.forEach(DeltaUtils::broadcast);
        reset();
    }

    /**
     * Resets the modifiable content
     */
    private void reset() {
        modifiableContent.clear();
        modifiableContent.addAll(defaultContent);
    }

    /**
     * Reloads the message and resets the modifiable content
     */
    public void reload() {
        configuration.reload();
        defaultContent.clear();
        defaultContent.addAll(configuration.getConfig().getStringList("content"));
        reset();
    }

    /**
     * Gets the modifiable content for... modifying use
     *
     * @return List of Strings
     */
    private List<String> getModifiableContent() {
        return modifiableContent;
    }

    /**
     * Sets the modifiable content
     *
     * @param modifiableContent List of Strings
     */
    private void setModifiableContent(List<String> modifiableContent) {
        this.modifiableContent = modifiableContent;
    }
}
