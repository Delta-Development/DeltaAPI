package club.deltapvp.deltacore.api.utilities.serialization;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public interface BukkitSerializer {

    /**
     * Write a PlayerInventory to Base64.
     *
     * @param inventory Player Inventory
     * @return String[0] is Inventory, String[1] is Armor.
     */
    String[] playerInventoryToBase64(PlayerInventory inventory) throws IllegalStateException;

    /**
     * Write an ItemStack Array to Base64.
     *
     * @param itemStacks Items
     * @return Encoded Base64
     */
    String itemStackArrayToBase64(ItemStack[] itemStacks) throws IllegalStateException;

    /**
     * Writes an Inventory to Base64
     *
     * @param inventory Inventory
     * @return Encoded Base64
     */
    String inventoryToBase64(Inventory inventory) throws IllegalStateException;

    /**
     * Gets an Inventory from Base64
     *
     * @param base64 Encoded Base64
     * @return Inventory Object
     */
    Inventory inventoryFromBase64(String base64) throws IllegalStateException;

    /**
     * Gets an ItemStack Array from Base64
     *
     * @param base64 Encoded Base64
     * @return ItemStack Array
     */
    ItemStack[] itemStackArrayFromBase64(String base64) throws IllegalStateException;


}
