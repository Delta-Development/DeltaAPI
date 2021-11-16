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

package club.deltapvp.api.utilities.builder;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

/**
 * Text Component Utility
 *
 * @author Negative
 * @since June 2021
 */
public class TextComponentUtil {

    private final TextComponent textComponent;

    /**
     * Create a new Text Component
     *
     * @param text Chat message
     */
    public TextComponentUtil(String text) {
        this.textComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', text));
    }

    /**
     * Set a click event for the text component
     *
     * @param action Click Action such as RUN_COMMAND, OPEN_URL, SUGGEST_COMMAND, etc.
     * @param value  Value of the given action
     * @return Returns component
     */
    public TextComponentUtil setClickEvent(ClickEvent.Action action, String value) {
        textComponent.setClickEvent(new ClickEvent(action, value));
        return this;
    }

    /**
     * Set a hover text for the text component
     *
     * @param text Display of the text
     * @return Returns component
     * @apiNote Use "\n" for a new line!
     */
    public TextComponentUtil setHoverText(String text) {
        ComponentBuilder componentBuilder = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', text));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, componentBuilder.create()));
        return this;
    }

    /**
     * Finally, returns the text component for later use.
     *
     * @return Returns TextComponent
     */
    public TextComponent getTextComponent() {
        return textComponent;
    }

    /**
     * Sends the Text Component to a player
     *
     * @param player The player
     */
    public void send(Player player) {
        player.spigot().sendMessage(getTextComponent());
    }

    /**
     * Sends the Text Component to a list of players
     *
     * @param players The players
     */
    public void send(Player... players) {
        for (Player player : players) {
            player.spigot().sendMessage(getTextComponent());
        }
    }
}
