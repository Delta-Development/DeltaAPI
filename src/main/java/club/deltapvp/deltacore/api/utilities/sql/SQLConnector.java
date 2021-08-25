package club.deltapvp.deltacore.api.utilities.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLConnector {

    boolean isConnected();

    void connect() throws ClassNotFoundException, SQLException;

    void disconnect();

    Connection getConnection();

}
