package club.deltapvp.deltacore.api.utilities.message.file;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@UtilityClass
public class MessageManager {

    private final HashMap<DeltaPlugin, ArrayList<Message>> registeredMessages = new HashMap<>();

    public void registerMessage(DeltaPlugin plugin, Message message) {
        ArrayList<Message> messages = registeredMessages.get(plugin);
        if (messages == null) messages = new ArrayList<>();

        Optional<Message> msg = getMessage(plugin, message.getId());
        if (msg.isPresent())
            return;

        messages.add(message);

        if (registeredMessages.containsKey(plugin))
            registeredMessages.replace(plugin, messages);
        else
            registeredMessages.put(plugin, messages);

    }

    public Optional<Message> getMessage(DeltaPlugin plugin, String id) {
        ArrayList<Message> messages = registeredMessages.get(plugin);
        if (messages == null)
            return Optional.empty();

        return messages.stream().filter(message -> message.getId().equalsIgnoreCase(id)).findFirst();
    }
}
