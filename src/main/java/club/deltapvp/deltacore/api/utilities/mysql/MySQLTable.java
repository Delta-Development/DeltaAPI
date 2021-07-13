package club.deltapvp.deltacore.api.utilities.mysql;

/**
 * MySQL Table Interface
 * This interface grants you a simple
 * template for allowing you to create a MySQL Database table
 *
 * @author Negative
 */
public interface MySQLTable {

    String getTable();

    void createTable();

}
