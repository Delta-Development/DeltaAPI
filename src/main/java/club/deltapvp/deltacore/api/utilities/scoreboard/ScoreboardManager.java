package club.deltapvp.deltacore.api.utilities.scoreboard;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public abstract class ScoreboardManager {

    @Getter
    @Setter
    private static ScoreboardManager instance;

    public abstract void registerScoreboard(DeltaPlugin plugin, Scoreboard scoreboard);

    public abstract void unRegisterScoreboard(DeltaPlugin plugin, Scoreboard scoreboard);

    public abstract void applyScoreboard(Player player, Scoreboard scoreboard);

    public abstract void applyScoreboard(Player player, String handle);

    public abstract void removeScoreboard(Player player, Scoreboard scoreboard);

    public abstract void removeScoreboard(Player player, String handle);

    public abstract void removeAllScoreboards();

    public abstract void updateScoreboard(Player player);

    public abstract Scoreboard getScoreboard(Player player);

    public abstract Optional<Scoreboard> getScoreboardByHandle(String input);

    public abstract List<Scoreboard> getScoreboards(DeltaPlugin plugin);
}
