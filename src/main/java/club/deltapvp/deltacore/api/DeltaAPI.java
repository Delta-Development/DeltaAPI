package club.deltapvp.deltacore.api;

import club.deltapvp.deltacore.api.bungeecord.BungeeCord;
import club.deltapvp.deltacore.api.utilities.TimeConversion;
import club.deltapvp.deltacore.api.utilities.UpdateChecker;
import club.deltapvp.deltacore.api.utilities.input.InputListener;
import club.deltapvp.deltacore.api.utilities.message.Message;
import club.deltapvp.deltacore.api.utilities.version.VersionChecker;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    public abstract TimeConversion getTimeConverter();

    public abstract UpdateChecker getUpdateChecker();

}
