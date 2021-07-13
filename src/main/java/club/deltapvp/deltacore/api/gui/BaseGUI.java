package club.deltapvp.deltacore.api.gui;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BaseGUI implements InventoryHolder {

    @Getter
    private final GUI gui;

    @Getter
    @Setter
    private Inventory inventory;

    public BaseGUI(GUI gui) {
        this.gui = gui;
    }

    public void onClose(Player player) {
        gui.getActiveInventories().remove(player);
    }

}
