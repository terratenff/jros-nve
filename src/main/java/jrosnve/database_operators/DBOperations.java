package jrosnve.database_operators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Acts as a collection of functions related to database management.
 * @author terratenff
 */
public class DBOperations {
    /**
     * All information is stored on the database file "textdata.db".
     */
    private static final String URL = "jdbc:sqlite:textdata.db";

    /**
     * Attempts to create a connection to the database.
     * @return Connection to the database, if connection could be established.
     * Otherwise a null object is returned.
     */
    static Connection establishConnection() {
		Connection conn = null;
		try {
            Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL);
			System.out.println("- DBOperations: Connection established -");
        } catch (ClassNotFoundException e) {
            System.out.println("- DBOperations: Error (ClassNotFoundException) occurred while establishing connection -");
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("- DBOperations: Error (SQLException) occurred while establishing connection -");
			System.out.println(e.getMessage());
		}
		return conn;
	}

    /**
     * Terminates a connection to the database.
     * @param conn Connection to the database.
     * @return true, if connection termination was successful. false otherwise.
     */
    static boolean terminateConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("- DBOperations: Connection closed -");
			}
			return true;
		} catch (SQLException e) {
			System.out.println("- DBOperations: Error occurred while closing connection -");
			System.out.println(e.getMessage());
			return false;
		}
	}

    /**
     * Searches the database for any IDs that have remained unused.
     * @return ID-integer that is both the lowest and unused.
     */
    static int findUnusedID() {
		String query = "SELECT id FROM user";
		int chosenId = 0;
		Connection conn = null;
		try {
			conn = establishConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ArrayList<Integer> ids = new ArrayList<Integer>();

            // Collect every ID-integer that is in use.
			while (rs.next()) {
				int id = rs.getInt("id");
				if (!ids.contains(id)) {
					ids.add(id);
				}
			}

            // Sort the collection of IDs to see if there are any gaps.
			Collections.sort(ids);
			int i = 0;
			for (int candidate : ids) {
				if (candidate != i) {
                    // There is a gap in the collection: an unused ID
                    // has been found.
					break;
				} else {
                    // Keep incrementing ID-integer until all of the IDs
                    // from the collection have been viewed. If no gap could
                    // be found, the next ID-integer will be used.
					++i;
				}
			}
			chosenId = i;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			chosenId = -1;
		} finally {
			terminateConnection(conn);
		}
		return chosenId;
	}
}
