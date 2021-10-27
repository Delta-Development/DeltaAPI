package club.deltapvp.deltacore.api.utilities.cache;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ObjectCache<T> {

    private final String path;
    private final Class<T[]> clazz;
    private final Gson gson;

    /**
     * Constructor for the ObjectCache
     *
     * @param path  Path to where the cache will be saved to JSON
     * @param clazz Class Type Array
     */
    public ObjectCache(String path, Class<T[]> clazz) {
        this.path = path;
        this.clazz = clazz;
        gson = new Gson();
    }

    /**
     * Save the Cache to the JSON file
     *
     * @param cacheArrayList Class Type ArrayList
     * @throws IOException
     */
    public void save(ArrayList<T> cacheArrayList) throws IOException {
        File file = getFile(path);
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file, false);
        gson.toJson(cacheArrayList, writer);
        writer.flush();
        writer.close();
    }

    /**
     * Load the Cache from the JSON file
     *
     * @return A new instance of an ArrayList with the new Cache
     * @throws IOException
     */
    public ArrayList<T> load() throws IOException {
        File file = getFile(path);
        if (file.exists()) {
            Reader reader = new FileReader(file);
            T[] p = gson.fromJson(reader, clazz);
            return new ArrayList<>(Arrays.asList(p));
        }
        return new ArrayList<>();
    }

    private File getFile(String path) {
        return new File(path);
    }
}
