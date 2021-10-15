package club.deltapvp.deltacore.api;

import club.deltapvp.deltacore.api.bungeecord.BungeeCord;
import club.deltapvp.deltacore.api.utilities.checker.UpdateChecker;
import club.deltapvp.deltacore.api.utilities.file.FileLoader;
import club.deltapvp.deltacore.api.utilities.hex.HexValidator;
import club.deltapvp.deltacore.api.utilities.hologram.HologramManager;
import club.deltapvp.deltacore.api.utilities.input.InputListener;
import club.deltapvp.deltacore.api.utilities.message.iface.Message;
import club.deltapvp.deltacore.api.utilities.serialization.BukkitSerializer;
import club.deltapvp.deltacore.api.utilities.sign.VirtualSignEditor;
import club.deltapvp.deltacore.api.utilities.skull.CustomSkull;
import club.deltapvp.deltacore.api.utilities.time.TimeConversion;
import club.deltapvp.deltacore.api.utilities.version.VersionChecker;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DeltaAPI
 *
 * @author Negative
 * @since August 25th, 2021
 * <p>
 * This class is one of the main points of access for any plugin to access
 * some features of the entire framework.
 */
public abstract class DeltaAPI {

    @Getter
    @Setter
    private static DeltaAPI instance;

    public abstract BungeeCord getBungeeCord();

    public abstract VersionChecker getVersionChecker();

    public abstract InputListener getInputListener();

    public abstract Message createMessage(String... message);

    public abstract Message createMessage(String message);

    public abstract Message createMessage(List<String> message);

    /**
     * @deprecated Use {@link club.deltapvp.deltacore.api.utilities.time.TimeUtil}
     */
    @Deprecated
    public abstract TimeConversion getTimeConverter();

    public abstract UpdateChecker getUpdateChecker();

    public abstract FileLoader getFileLoader();

    public abstract BukkitSerializer getBukkitSerializer();

    public abstract HexValidator getHexValidator();

    public abstract HologramManager getHologramManager();

    public abstract CustomSkull createCustomSkull(String url);

    public abstract VirtualSignEditor getVirtualSignEditor();
}
