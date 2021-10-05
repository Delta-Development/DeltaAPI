package club.deltapvp.deltacore.api.utilities.scoreboard;

import club.deltapvp.deltacore.api.utilities.DeltaUtils;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class Scoreboard {

    private Function<Player, List<String>> scoreboardLines;

    public abstract String handle();

    public abstract int priority();

    public Function<Player, List<String>> getScoreboardLines() {
        return scoreboardLines;
    }

    public void setScoreboardLines(Function<Player, List<String>> function) {
        this.scoreboardLines = function;
    }

    private String getEntryFromScore(final Objective o, final int score) {
        if (o == null) {
            return null;
        }
        if (!hasScoreTaken(o, score)) {
            return null;
        }
        for (final String s : o.getScoreboard().getEntries()) {
            if (o.getScore(s).getScore() == score) {
                return o.getScore(s).getEntry();
            }
        }
        return null;
    }

    private boolean hasScoreTaken(Objective o, final int score) {
        for (final String s : o.getScoreboard().getEntries()) {
            if (o.getScore(s).getScore() == score) {
                return true;
            }
        }
        return false;
    }

    private void replaceScore(Objective o, final int score, final String name) {
        if (hasScoreTaken(o, score)) {
            if (getEntryFromScore(o, score).equalsIgnoreCase(name)) {
                return;
            }
            if (!getEntryFromScore(o, score).equalsIgnoreCase(name)) {
                o.getScoreboard().resetScores(getEntryFromScore(o, score));
            }
        }
        o.getScore(name).setScore(score);
    }

    public void display(Player player) {
        List<String> apply = scoreboardLines.apply(player);
        Collections.reverse(apply);

        org.bukkit.scoreboard.Scoreboard score = player.getScoreboard();
        final Objective objective = (score.getObjective(player.getName()) == null) ?
                score.registerNewObjective(player.getName(), "dummy") : score.getObjective(player.getName());

        for (int i = 0; i < apply.size(); i++) {
            String line = apply.get(0);
            int pos = (i + 1);
            replaceScore(objective, pos, DeltaUtils.colorWithPlaceholders(player, line));
        }
        if (objective.getDisplaySlot() != DisplaySlot.SIDEBAR) {
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
        player.setScoreboard(score);
    }
}
