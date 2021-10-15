package club.deltapvp.deltacore.api.utilities.scoreboard;

import club.deltapvp.deltacore.api.utilities.DeltaUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class Scoreboard {

    @Getter
    @Setter
    private Function<Player, List<String>> scoreboardLines;
    @Getter
    @Setter
    private Function<Player, String> scoreboardTitle;

    public abstract String handle();

    public abstract int priority();

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
        if (apply == null) {
            player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
            return;
        }

        Collections.reverse(apply);

        if (player.getScoreboard().equals(Bukkit.getServer().getScoreboardManager().getMainScoreboard())) {
            player.setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
        }

        org.bukkit.scoreboard.Scoreboard score = player.getScoreboard();
        final Objective objective = (score.getObjective(player.getName()) == null) ?
                score.registerNewObjective(player.getName(), "delta") : score.getObjective(player.getName());

        objective.setDisplayName(DeltaUtils.colorWithPlaceholders(player, scoreboardTitle.apply(player)));

        for (String line : apply) {
            int index = apply.indexOf(line);
            replaceScore(objective, (index + 1), DeltaUtils.colorWithPlaceholders(player, line));
        }

        if (objective.getDisplaySlot() != DisplaySlot.SIDEBAR) {
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
        player.setScoreboard(score);
    }
}
