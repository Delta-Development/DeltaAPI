package club.deltapvp.deltacore.api.utilities.event.items.other;

import club.deltapvp.deltacore.api.annotation.Prototype;
import club.deltapvp.deltacore.api.utilities.event.DeltaEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@Deprecated // Unused
@RequiredArgsConstructor
@Getter
@Prototype
public class PlayerJumpEvent extends DeltaEvent {

    private final Player player;

}
