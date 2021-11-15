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

package club.deltapvp.deltacore.api.utilities.downloader;

import lombok.var;

import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {
    private static final int BUFFER_SIZE = 8192;

    public static void download(String url, String directory, DownloadCallback callback) {
        try {
            var var3 = new URL(url);
            HttpURLConnection var4 = (HttpURLConnection) var3.openConnection();
            var var5 = var4.getResponseCode();
            if (var5 != 200) {
                callback.onError(var5);
            } else {
                var var7 = var4.getHeaderField("Content-Disposition");
                String var6;
                if (var7 != null) {
                    var6 = var7.replaceFirst(".*?filename=\"?\"?", "").replaceAll("\"?", "");
                } else {
                    var6 = url.substring(url.lastIndexOf("/") + 1);
                }

                var var8 = var4.getInputStream();
                var var9 = directory + File.separator + var6;
                (new File(directory)).mkdirs();
                var var10 = new File(var9);
                if (!var10.createNewFile()) {
                    callback.onExist();
                    return;
                }

                var var11 = new FileOutputStream(var9);
                var var13 = new byte[8192];

                while (true) {
                    int var12;
                    if ((var12 = var8.read(var13)) == -1) {
                        var11.close();
                        var8.close();
                        callback.onSuccess();
                        break;
                    }

                    var11.write(var13, 0, var12);
                }
            }

            var4.disconnect();
        } catch (Exception var14) {
            var14.printStackTrace();
            callback.onError(0);
        }

    }

}
