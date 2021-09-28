package club.deltapvp.deltacore.api.utilities.message.file;

import club.deltapvp.deltacore.api.DeltaPlugin;
import club.deltapvp.deltacore.api.utilities.DeltaUtils;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Prototype Message Utility
 *
 * @author Negative
 * @since September 28th, 2021
 * <p>
 * This is a prototype for a new message system which allows a plugin to have a
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

    public Message(DeltaPlugin plugin, String id, String... defaultValues) {
        this.id = id;

        configuration = new MessageConfiguration(plugin, id, defaultValues);
        defaultContent = new LinkedList<>();

        defaultContent.addAll(configuration.getConfig().getStringList("content"));
        reset();

        MessageManager.registerMessage(plugin, this);
    }

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

    public void send(CommandSender sender) {
        modifiableContent.forEach(s -> sender.sendMessage(t(s)));
        reset();
    }

    public void broadcast() {
        modifiableContent.forEach(DeltaUtils::broadcast);
        reset();
    }

    private void reset() {
        modifiableContent.clear();
        modifiableContent.addAll(defaultContent);
    }

    private String t(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public void reload() {
        configuration.reload();
        defaultContent.clear();
        defaultContent.addAll(configuration.getConfig().getStringList("content"));
        reset();
    }

    private List<String> getModifiableContent() {
        return modifiableContent;
    }

    private void setModifiableContent(List<String> modifiableContent) {
        this.modifiableContent = modifiableContent;
    }
}
