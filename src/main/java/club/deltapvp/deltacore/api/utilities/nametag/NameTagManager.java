package club.deltapvp.deltacore.api.utilities.nametag;

import club.deltapvp.deltacore.api.DeltaPlugin;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public abstract class NameTagManager {

    @Getter @Setter
    private static NameTagManager instance;

    public abstract void registerNameTag(DeltaPlugin plugin, NameTag nameTag);

    public abstract void unRegisterNameTag(DeltaPlugin plugin, NameTag nameTag);

    public abstract void applyNameTag(Player player, NameTag nameTag);

    public abstract void applyNameTag(Player player, String handle);

    public abstract void removeNameTag(Player player, NameTag nameTag);

    public abstract void removeNameTag(Player player, String handle);

    public abstract void removeAllNameTags(Player player);

    public abstract void updateNameTag(Player player);

    public abstract Optional<NameTag> getNameTag(Player player);

    public abstract Optional<NameTag> getNameTagByHandle(String handle);

    public abstract List<NameTag> getNameTags(DeltaPlugin plugin);
}
