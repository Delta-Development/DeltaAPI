package club.deltapvp.deltacore.api.utilities.sign;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiPredicate;

public interface VirtualSignEditor {

    AbstractSignMenu createSign(List<String> lines);

    AbstractSignMenu createSign(List<String> lines, boolean reOpenIfFail);

    AbstractSignMenu createSign(List<String> lines, boolean reOpenIfFail, BiPredicate<Player, String[]> response);

    AbstractSignMenu createSign(List<String> lines, BiPredicate<Player, String[]> response);
}
