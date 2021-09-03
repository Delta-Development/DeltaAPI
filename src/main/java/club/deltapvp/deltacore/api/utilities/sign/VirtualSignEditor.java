package club.deltapvp.deltacore.api.utilities.sign;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiPredicate;

public interface VirtualSignEditor {

    VirtualSign createSignEditor(List<String> lines);

    VirtualSign createSignEditor(List<String> lines, boolean reOpenIfFail);

    VirtualSign createSignEditor(List<String> lines, boolean reOpenIfFail, BiPredicate<Player, String[]> response);

    VirtualSign createSignEditor(List<String> lines, BiPredicate<Player, String[]> response);
}
