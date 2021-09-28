package club.deltapvp.deltacore.api.utilities.event.items.damage;

import club.deltapvp.deltacore.api.utilities.event.DeltaEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * PlayerDamageByEntityEvent
 *
 * @author Negative
 * @since September 28th, 2021
 *
 * This event is similar to {@link EntityDamageByEntityEvent}, but it only applies to players
 * so developers do not need to make specific checks to see if a player is being
 * damaged by an entity and not any other entity is being damaged by said entity
 */
@RequiredArgsConstructor
@Getter @Setter
public class PlayerDamageByEntityEvent extends DeltaEvent {

    private final Player player;
    private final Entity damager;
    private final EntityDamageEvent.DamageCause cause;
    private boolean cancelled;
    private double damage;

}
