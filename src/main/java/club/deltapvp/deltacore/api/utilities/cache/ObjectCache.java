/*
 *       DeltaAPI is a Minecraft Java API.
 *       Copyright (C) 2021 DeltaDevelopment
 *
 *       This program is free software; you can redistribute it and/or modify
 *       it under the terms of the GNU General Public License as published by
 *       the Free Software Foundation; either version 2 of the License, or
 *       (at your option) any later version.
 *
 *       This program is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *       GNU General Public License for more details.
 */

package club.deltapvp.deltacore.api.utilities.cache;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
public abstract class ObjectCache<T> {

    private final String path;
    private final Class<T[]> clazz;
    private final Gson gson = new Gson();

    /**
     * Save the Cache to the JSON file
     *
     * @param cacheArrayList Class Type ArrayList
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
