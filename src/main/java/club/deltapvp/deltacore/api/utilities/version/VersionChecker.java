package club.deltapvp.deltacore.api.utilities.version;

public interface VersionChecker {

    boolean isModern();

    boolean isLegacy();

    ServerVersion getVersion();

}
