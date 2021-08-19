package club.deltapvp.deltacore.api.gui;

import club.deltapvp.deltacore.api.utilities.builder.ItemBuilder;
import club.deltapvp.deltacore.api.utilities.version.VersionChecker;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

    @Getter @Setter
    private BiConsumer<Player, InventoryCloseEvent> onClose;

    // Are people allowed to take items from the GUI?
    @Getter
    private final boolean allowTakeItems;

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

        items.forEach(inv::setItem);

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

    public void refreshItems(Player player) {
        if (activeInventories.get(player) == null || !(player.getInventory() instanceof BaseGUI))
            return;

        items.forEach((slot, item) -> player.getInventory().setItem(slot, item));
    }

    /**
     * Set "Fillers" from one index slot to another
     *
     * @param indexMin Minimum index slot
     * @param indexMax Maximum index slot
     * @apiNote Default Data for filler is 15!
     */
    public void setFillers(int indexMin, int indexMax) {
        setFillers(15, indexMin, indexMax);
    }

    /**
     * Set "Fillers" from one index to another
     *
     * @param data     Data of the filler (what color it is)
     * @param indexMin Minimum index slot
     * @param indexMax Maximum index slot
     */
    public void setFillers(int data, int indexMin, int indexMax) {
        for (int i = indexMin; i < indexMax; i++) {
            setFiller(data, i);
        }
    }

    /**
     * Set "Fillers" from one index to another
     *
     * @param material Material of the Filler
     * @param indexMin Minimum index slot
     * @param indexMax Maximum index slot
     */
    public void setFillers(Material material, int indexMin, int indexMax) {
        for (int i = indexMin; i < indexMax; i++) {
            setFiller(material, i);
        }
    }

    /**
     * Set a "Filler" at a certain index slot
     *
     * @param data  Data of the filler (what color it is)
     * @param index Index slot
     */
    public void setFiller(int data, int index) {
        setItemClickEvent(index, getFiller(data), (player, event) -> event.setCancelled(true));
    }

    /**
     * Set a "Filler" at a certain index slot
     *
     * @param material Material of the filler
     * @param index    Index slot
     * @apiNote Recommended for modern versions of Minecraft!
     */
    public void setFiller(Material material, int index) {
        setItemClickEvent(index, getFiller(material), (player, event) -> event.setCancelled(true));
    }

    /**
     * Get the Filler ItemStack
     *
     * @return Filler Item
     * @apiNote Default data of filler is 15 (what color it is)!
     */
    public ItemStack getFiller() {
        return getFiller(15);
    }

    /**
     * Set a "Filler" at a certain index slot
     *
     * @param index Index slot
     * @apiNote Default data of filler is 15 (what color it is)!
     */
    public void setFiller(int index) {
        setFiller(15, index);
    }

    /**
     * Get the Filler ItemStack
     *
     * @param data Data of the filler (what color it is)
     * @return Filler Item
     */
    public ItemStack getFiller(int data) {
        return (VersionChecker.getInstance().isModern() ? new ItemBuilder(Material.valueOf("BLACK_STAINED_GLASS_PANE"))
                .setName("         ").build() : new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) data)
                .setName("             ").build());
    }

    /**
     * Get the Filler ItemStack
     *
     * @param material Material of the filler
     * @return Filler Item
     * @apiNote Recommended for Modern verions of Minecraft!
     */
    public ItemStack getFiller(Material material) {
        return new ItemBuilder(material).setName("          ").build();
    }
}
