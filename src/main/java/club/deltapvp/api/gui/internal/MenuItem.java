package club.deltapvp.api.gui.internal;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Function;

@Data
public class MenuItem {

    private final int slot;
    private final Function<Player, ItemStack> item;
    private final BiConsumer<Player, InventoryClickEvent> clickEvent;

}
