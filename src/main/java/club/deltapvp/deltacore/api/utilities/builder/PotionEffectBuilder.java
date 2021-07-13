package club.deltapvp.deltacore.api.utilities.builder;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PotionEffectBuilder {

    private final PotionEffectType type;
    private int duration;
    private int amp;

    public PotionEffectBuilder(PotionEffectType type) {
        this.type = type;
    }

    public PotionEffectBuilder setDuration(int seconds) {
        this.duration = seconds;
        return this;
    }

    public PotionEffectBuilder setAmplifier(int amplifier) {
        this.amp = amplifier;
        return this;
    }

    public PotionEffect build() {
        return new PotionEffect(type, 20 * duration, amp - 1);
    }

    public void apply(Player player) {
        player.addPotionEffect(build());
    }

    public void apply(List<Player> players) {
        players.forEach(this::apply);
    }

}
