package club.deltapvp.deltacore.api.utilities.gson;

import java.io.IOException;

public interface JSONDataManager {

    /**
     * Attempts to save Data
     */
    void save() throws IOException;

    /**
     * Attempts to load Data
     */
    void load() throws IOException;
}
