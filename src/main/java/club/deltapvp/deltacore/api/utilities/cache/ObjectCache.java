package club.deltapvp.deltacore.api.utilities.cache;

import club.deltapvp.deltacore.api.annotation.Prototype;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ObjectCache<T> {

    private final String path;
    private final Gson gson;

    public ObjectCache(String path) {
        this.path = path;
        gson = new Gson();
    }

    public void save(ArrayList<T> cacheArrayList) throws IOException {
        File file = getFile(path);
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file, false);
        gson.toJson(cacheArrayList, writer);
        writer.flush();
        writer.close();
    }

    @Prototype
    public ArrayList<T> load(Class<? extends T> clazz) throws IOException {
        File file = getFile(path);
        if (file.exists()) {
            Reader reader = new FileReader(file);
            T[] p = gson.fromJson(reader, (Type) clazz);
            return new ArrayList<>(Arrays.asList(p));
        }
        return new ArrayList<>();
    }

    private File getFile(String path) {
        return new File(path);
    }
}
