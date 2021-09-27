package club.deltapvp.deltacore.api.utilities.hologram;

import club.deltapvp.deltacore.api.utilities.event.DeltaEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
@Setter
@RequiredArgsConstructor
public class HologramInteractEvent extends DeltaEvent {

    private final Player player;
    private final Hologram hologram;
    private final boolean attacked;
    private boolean cancel;

}
