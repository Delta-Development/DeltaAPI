package club.deltapvp.deltacore.api.utilities.sql;

import club.deltapvp.deltacore.api.annotation.Prototype;
import lombok.Getter;
import lombok.Setter;

/**
 * DeltaAPI's SQL Management Class
 *
 * @author Negative
 *
 * This class is where you can create connects to a MySQL or SQLite database!
 *
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
