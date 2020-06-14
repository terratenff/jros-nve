package org.tt.indproj.database_operators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Set of database operations that concern mostly just reading from
 * the database.
 * @author terratenff
 *
 */
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
    
    /**
     * Attempts user authorization.
     * @param username Username input by user.
     * @param password Password input by user.
     * @return ID of the user if authorization was successful. -1 if authroization
     * failed (nonexistent username, invalid password, exception).
     */
    static int login(String username, String password) {
    	String sql = "SELECT id, magicword FROM people WHERE username='" + username + "'";
    	int outcome = -1;
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            boolean discovery = false;
            while (rs.next()) {
            	discovery = true;
            	if (password.equals(rs.getString("magicword"))) {
            		logger.info("Authorization succeeded!");
            		outcome = rs.getInt("id");
            		break;
            	} else {
            		logger.error("Authorization failed: invalid password.");
            		outcome = -1;
            		break;
            	}
            }
            if (!discovery) {
            	logger.error("Authorization failed: nonexistent username");
            }
        } catch (SQLException e) {
        	logger.error("Error while authorizing a specific user:");
			logger.error(e.getMessage());
			outcome = -1;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
    }
    
    /**
     * Collects an individual user from the database.
     * @param id ID of the user.
     * @param callback Action that is taken with the ResultSet containing
     * the user.
     */
    synchronized static void processUser(int id, Consumer<ResultSet> callback) {
    	String sql = "SELECT * FROM people WHERE id=" + id;
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            callback.accept(rs);
        } catch (SQLException e) {
        	logger.error("Error while processing a specific user:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
    
    /**
     * Collects every logged user from the database.
     * @param callback Action that is taken with the ResultSet containing
     * the users.
     */
    synchronized static void processUsers(Consumer<ResultSet> callback) {
    	String sql = "SELECT * FROM people";
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	callback.accept(rs);
            }
        } catch (SQLException e) {
        	logger.error("Error while processing every user:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
}
