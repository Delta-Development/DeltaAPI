package club.deltapvp.deltacore.api.utilities.gson;

import java.io.IOException;

public interface JSONDataManager {

    /**
     * Save Function
     * <p>
     * Attempts to save Data
     */
    void save() throws IOException;

    /**
     * Load Function
     * <p>
     * Attempts to load Data
     */
    void load() throws IOException;
}
