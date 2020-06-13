package org.tt.indproj.database_operators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DBReader {
	
	/**
	 * Writes down some notes from general database read operations.
	 */
	private static Logger logger = LoggerFactory.getLogger(DBReader.class);
	
	/**
     * Checks to see if specified username exists in application database.
     * @param username Target username.
     * @return true, if it already exists. false otherwise.
     */
    static boolean usernameExists(String username) {
    	String sql = "SELECT COUNT(*) FROM people WHERE username='" + username + "'";
    	boolean outcome = false;
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	int rowCount = Integer.parseInt(rs.getString("count(*)"));
            	if (rowCount > 0) outcome = true;
            }
        } catch (SQLException e) {
        	logger.error("Error while checking for an existing username:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
    }
    
    /**
     * Prints (with a logger) the contents of specified database table.
     * @param table Target database table (if it exists).
     */
    static void viewTable(String table, boolean skipLargeInstances) {
    	String sql = "SELECT * FROM " + table.toLowerCase();
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            logger.info("Table '" + table.toLowerCase() + "' contents:");
            DBOperations.printResultSet(rs, skipLargeInstances);
        } catch (SQLException e) {
        	logger.error("Error while viewing a database table:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
    
    /**
     * Collects the ID of specified user.
     * @param username Username.
     * @return ID of specified user.
     */
    static int getUserId(String username) {
    	String sql = "SELECT id FROM people WHERE username='" + username + "'";
    	int id = -1;
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	id = rs.getInt("id");
            	break;
            }
        } catch (SQLException e) {
        	logger.error("Error while viewing a database table:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
        if (id == -1) logger.error("Entry with username '" + username + "' could not be found.");
        return id;
    }
}
