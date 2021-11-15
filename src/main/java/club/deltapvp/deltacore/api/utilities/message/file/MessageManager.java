/*
 *       DeltaAPI is a Minecraft Java API.
 *       Copyright (C) 2021 DeltaDevelopment
 *
 *       This program is free software; you can redistribute it and/or modify
 *       it under the terms of the GNU General Public License as published by
 *       the Free Software Foundation; either version 2 of the License, or
 *       (at your option) any later version.
 *
 *       This program is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *       GNU General Public License for more details.
 */

package club.deltapvp.deltacore.api.utilities.message.file;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Message Manager
 *
 * @author Negative
 * @since September 28th, 2021
 * <p>
 * Management class for {@link Message}
 * <p>
 * This class will manage all the messages registered by plugins
 */
@UtilityClass
public class MessageManager {


    private final HashMap<DeltaPlugin, ArrayList<Message>> registeredMessages = new HashMap<>();

    /**
     * Registers the Message to be put in a cache
     *
     * @param plugin  Plugin instance
     * @param message Message instance
     */
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

    /**
     * Gets the Message object from a plugin instance
     *
     * @param plugin Plugin instance
     * @param id     Message ID
     * @return A potential Message object if it exists inside the plugin's message cache
     */
    public Optional<Message> getMessage(DeltaPlugin plugin, String id) {
        ArrayList<Message> messages = registeredMessages.get(plugin);
        if (messages == null)
            return Optional.empty();

        return messages.stream().filter(message -> message.getId().equalsIgnoreCase(id)).findFirst();
    }
}
