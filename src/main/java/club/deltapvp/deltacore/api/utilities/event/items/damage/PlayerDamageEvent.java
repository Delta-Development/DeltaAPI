package club.deltapvp.deltacore.api.utilities.event.items.damage;

import club.deltapvp.deltacore.api.utilities.event.DeltaEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * PlayerDamageEvent
 *
 * @author Negative
 * @since September 28th, 2021
 *
 * This event is similar to {@link EntityDamageEvent}, but it only applies to players
 * so developers do not need to make specific checks to see if a player is being
 * damaged and not any other entity
 */
@RequiredArgsConstructor
@Getter @Setter
public class PlayerDamageEvent extends DeltaEvent {

    private final Player player;
    private final EntityDamageEvent.DamageCause cause;
    private boolean cancelled;
    private double damage;

}
