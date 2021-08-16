package club.deltapvp.deltacore.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DeltaAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    private void bop(Player player) {

        List<String> blacklisted = getConfig().getStringList("blacklisted-materials");
        ItemStack[] contents = player.getInventory().getContents();

        List<ItemStack> toRemove = new ArrayList<>();
        Arrays.stream(contents).filter(itemStack -> {
            if (itemStack == null || itemStack.getType() == Material.AIR)
                return false;

            String type = blacklisted.stream()
                    .filter(s -> itemStack.getType().toString().equalsIgnoreCase(s))
                    .findFirst().orElse(null);

            return type != null;

        }).forEach(toRemove::add);

        toRemove.forEach(itemStack -> player.getInventory().remove(itemStack));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
