package club.deltapvp.deltacore.api.utilities.scoreboard;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;

public abstract class Scoreboard {

    private Function<Player, List<String>> scoreboardLines;

    public abstract String handle();

    public abstract int priority();

    public void setScoreboardLines(Function<Player, List<String>> function) {
        this.scoreboardLines = function;
    }

    public Function<Player, List<String>> getScoreboardLines() {
        return scoreboardLines;
    }
}
