package club.deltapvp.deltacore.api.utilities.version;

import lombok.Getter;
import lombok.Setter;

public abstract class VersionChecker {

    @Getter @Setter
    private static VersionChecker instance;

    public abstract boolean isModern();
    public abstract boolean isLegacy();
}
