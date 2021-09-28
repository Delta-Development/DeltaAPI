package club.deltapvp.deltacore.api.utilities.placeholder;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public abstract class PAPIManager {

    @Getter
    @Setter
    private static PAPIManager instance;

    public abstract void registerPlaceholder(DeltaPlugin plugin, PAPIPlaceholder... placeholders);

    public abstract String request(DeltaPlugin plugin, Player player, String[] params);

}
