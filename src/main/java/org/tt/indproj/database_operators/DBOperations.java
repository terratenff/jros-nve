package org.tt.indproj.database_operators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acts as a collection of functions related to database management.
 * @author terratenff
 */
public class DBOperations {
	
	/**
	 * Writes down information from generic database operations.
	 */
	private static Logger logger = LoggerFactory.getLogger(DBOperations.class);
	
    /**
     * All information is stored on the database file "textdata.db".
     */
    private static final String URL = "jdbc:sqlite:appdata.db";

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
			logger.info("Connection to 'appdata.db' established");
        } catch (ClassNotFoundException e) {
            logger.info("ClassNotFoundException occurred while establishing connection:");
			logger.info(e.getMessage());
		} catch (SQLException e) {
			logger.info("SQLException occurred while establishing connection:");
			logger.info(e.getMessage());
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
				logger.info("Connection to 'appdata.db' has been closed.");
			}
			return true;
		} catch (SQLException e) {
			logger.info("SQLException occurred while closing connection:");
			logger.info(e.getMessage());
			return false;
		}
	}
    
    /**
     * Convenient print function for database result sets.
     * @param rs ResultSet. <b>Connection to related database must not be closed!</b>
     * @throws SQLException 
     */
    static void printResultSet(ResultSet rs, boolean skipLargeInstances) throws SQLException {
    	ResultSetMetaData rsmd = rs.getMetaData();
    	List<String> columns = new ArrayList<String>();
    	for (int i = 0; i < rsmd.getColumnCount(); i++) {
    		columns.add(rsmd.getColumnName(i));
    	}
    	
    	String content = "";
    	int counter = 0;
    	while (rs.next()) {
    		logger.info("Entry [" + counter + "]");
    		for (String column : columns) {
    			content = rs.getString(column);
    			if (content.length() > 255 && skipLargeInstances) {
    				logger.info("- " + column + ": SKIPPED (Too large)");
    			} else {
    				logger.info("- " + column + ": " + content);
    			}
    		}
    	}
    }
}
