package jrosnve.database_operators;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Set of database operations that concern the creation side of things.
 * @author terratenff
 */
public class DBEstablisher {
    /**
     * Creates the primary database table, "puretext", that is set
     * to contain the story entries.
     */
    static void createDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS puretext (\n"
                + "id INTEGER NOT NULL PRIMARY KEY,"
                + "title VARCHAR(255),"
                + "content TEXT);";

        Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("- DBEstablisher: Error while remaking database -");
            System.out.println(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
}
