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

package club.deltapvp.deltacore.api.utilities.sql;

import club.deltapvp.deltacore.api.annotation.Prototype;
import lombok.Getter;
import lombok.Setter;

/**
 * DeltaAPI's SQL Management Class
 *
 * @author Negative
 * <p>
 * This class is where you can create connects to a MySQL or SQLite database!
 * @see {@link DeltaSQLAPI#createConnection(String, String, String, String, String)} to create a connection for MySQL
 * @see {@link DeltaSQLAPI#createConnection(String, String, String)} to create a SQLite connection
 */
@Prototype // Still in development, sort of.
public abstract class DeltaSQLAPI {

    @Getter
    @Setter
    private static DeltaSQLAPI instance;

    public abstract SQLConnector createConnection(String host, String port, String database, String username, String password);

    public abstract SQLConnector createConnection(String path);

    public abstract SQLConnector createConnection(String path, String databaseName, String endSuffix);

    public abstract SQLConnector createConnection(String path, String databaseName);

}
