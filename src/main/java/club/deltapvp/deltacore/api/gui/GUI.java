package club.deltapvp.deltacore.api.gui;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Custom GUI
 */

public class GUI {

    // Item placement map
    @Getter
    private final Map<Integer, ItemStack> items;
    // Rows amount (9 * rows)
    @Getter
    private final int rows;
    // Title of the GUI
    @Getter
    private final String title;
    // Inventory Click event map
    @Getter
    private final HashMap<Integer, BiConsumer<Player, InventoryClickEvent>> clickEvents;

    // Map of active inventories
    @Getter
    private final HashMap<Player, Inventory> activeInventories;
    // Are people allowed to take items from the GUI?
    @Getter
    private final boolean allowTakeItems;
    @Getter
    @Setter
    private BiConsumer<Player, InventoryCloseEvent> onClose;

    /**
     * Constructor for GUI
     *
     * @param title Title
     * @param rows  Number of rows
     * @apiNote The title supports color codes automatically!
     * @apiNote By default, allowTakeItems is false.
     */
    public GUI(String title, int rows) {
        this(title, rows, false);
    }

    /**
     * Main constructor for the GUI
     *
     * @param title          Title
     * @param rows           Number of rows
     * @param allowTakeItems Allowed to take items from the menu?
     * @apiNote The title supports color codes automatically!
     */
    public GUI(String title, int rows, boolean allowTakeItems) {
        this.rows = rows;
        this.title = title;
        this.allowTakeItems = allowTakeItems;

        items = new HashMap<>();
        clickEvents = new HashMap<>();
        activeInventories = new HashMap<>();
    }

    /**
     * Open the GUI for the provided player
     *
     * @param player Player
     */
    public void open(Player player) {
        BaseGUI holder = new BaseGUI(this);
        Inventory inv = Bukkit.createInventory(holder, (9 * rows), ChatColor.translateAlternateColorCodes('&', title));

        items.forEach((index, item) -> {
            try {
                inv.setItem(index, item);
            } catch (Exception ignored) {
            }
        });

        player.openInventory(inv);
        activeInventories.put(player, inv);
    }

    /**
     * Set Item to a certain index in the GUI
     *
     * @param index Index/Placement of the Item in the GUI
     * @param item  ItemStack
     * @apiNote There is no click event linked to this item
     * @apiNote First slot of GUIs are 0
     */
    public void setItem(int index, ItemStack item) {
        setItemClickEvent(index, item, null);
    }

    /**
     * Set Item Click Event to a certain index in the GUI
     *
     * @param index    Index/Placement of the Item in the GUI
     * @param item     ItemStack
     * @param function Click Event of the Item
     */
    public void setItemClickEvent(int index, ItemStack item, BiConsumer<Player, InventoryClickEvent> function) {
        if (function != null)
            clickEvents.put(index, function);

        items.put(index, item);
    }

    /**
     * Add Item Click Event to the GUI
     *
     * @param item     ItemStack
     * @param function Click Event of the Item
     * @apiNote This adds the Item to the next available slot
     */
    public void addItemClickEvent(ItemStack item, BiConsumer<Player, InventoryClickEvent> function) {
        int i;
        for (i = 0; i < (9 * rows); i++) {
            if (function != null && !clickEvents.containsKey(i) && !items.containsKey(i))
                break;

            if (!items.containsKey(i))
                break;

        }
        setItemClickEvent(i, item, function);
    }

    /**
     * Add Item to the GUI
     *
     * @param item ItemStack
     * @apiNote This adds the Item to the next available slot
     */
    public void addItem(ItemStack item) {
        int i;
        for (i = 0; i < (9 * rows); i++) {
            if (!items.containsKey(i))
                break;
        }
        setItem(i, item);
    }

    public void refresh(Player player) {
        if (activeInventories.get(player) == null || !(player.getInventory() instanceof BaseGUI))
            return;

        items.forEach((slot, item) -> player.getInventory().setItem(slot, item));
    }

}
