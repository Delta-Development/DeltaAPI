package club.deltapvp.deltacore.api.utilities.sql;

import lombok.Getter;
import lombok.Setter;

public abstract class DeltaSQLAPI {

    @Getter
    @Setter
    private static DeltaSQLAPI instance;

    public abstract SQLConnector createConnection(String host, String port, String database, String username, String password);

    public abstract SQLConnector createConnection(String path);

    public abstract SQLConnector createConnection(String path, String databaseName, String endSuffix);

    public abstract SQLConnector createConnection(String path, String databaseName);

}
