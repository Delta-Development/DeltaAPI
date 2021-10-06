package club.deltapvp.deltacore.api.utilities.hologram.v2;

import club.deltapvp.deltacore.api.utilities.event.DeltaEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Getter
public class HologramInteractEvent extends DeltaEvent {

    private final Player player;
    private final Hologram hologram;
}
