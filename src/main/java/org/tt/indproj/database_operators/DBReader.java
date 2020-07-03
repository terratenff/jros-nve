package org.tt.indproj.database_operators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tt.indproj.utilities.Encryption;

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
     * Checks to see if specified story title exists in application database.
     * @param title Target story title.
     * @return true, if it already exists. false otherwise.
     */
    static boolean storyTitleExists(String title) {
    	String sql = "SELECT COUNT(*) FROM stories WHERE title='" + title + "'";
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
        	logger.error("Error while checking for an existing story:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
    }
    
    /**
     * Checks to see if specified rating exists in application database.
     * @param makerId ID of the creator of the story.
     * @param raterId ID of the creator of the rating.
     * @param storyId ID of the story.
     * @return true, if it already exists. false otherwise.
     */
    static boolean ratingInstanceExists(int makerId, int raterId, int storyId) {
    	String sql = "SELECT COUNT(*) FROM ratings WHERE "
    			+ "makerid=" + makerId + " AND "
    			+ "raterid=" + raterId + " AND "
    			+ "storyid=" + storyId;
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
        	logger.error("Error while checking for an existing story:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
    }
    
    /**
     * Checks to see if specified rating exists in application database. Rating
     * uniqueness is based on combination of makerId, raterId and storyId.
     * @param makerId ID of the story author.
     * @param raterId ID of the rater.
     * @param storyId ID of the story subject to rating.
     * @return true, if it already exists. false otherwise.
     */
    static boolean ratingExists(int makerId, int raterId, int storyId) {
    	String sql = "SELECT COUNT(*) FROM ratings WHERE "
    			+ "makerid=" + makerId + " AND "
    			+ "raterid=" + raterId + " AND "
    			+ "storyid=" + storyId;
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
        	logger.error("Error while checking for an existing rating:");
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
     * Attempts user authorization.
     * @param username Username input by user.
     * @param password Password input by user.
     * @return ID of the user if authorization was successful. 0 if authroization
     * failed (nonexistent username, invalid password, exception).
     */
    static int login(String username, String password) {
    	String sql = "SELECT id, magicword, salt FROM people WHERE username='" + username + "'";
    	int outcome = 0;
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            boolean discovery = false;
            while (rs.next()) {
            	discovery = true;
            	String passwordHash = Encryption.sha256(password + rs.getString("salt"));
            	if (passwordHash.equals(rs.getString("magicword"))) {
            		logger.info("Authorization succeeded!");
            		outcome = rs.getInt("id");
            		break;
            	} else {
            		logger.error("Authorization failed: invalid password.");
            		outcome = 0;
            		break;
            	}
            }
            if (!discovery) {
            	logger.error("Authorization failed: nonexistent username");
            }
        } catch (SQLException e) {
        	logger.error("Error while authorizing a specific user:");
			logger.error(e.getMessage());
			outcome = 0;
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
    
    /**
     * Collects an individual story from the database.
     * @param id ID of the story.
     * @param callback Action that is taken with the ResultSet containing
     * the story.
     */
    synchronized static void processStory(int id, Consumer<ResultSet> callback) {
    	String sql = "SELECT * FROM stories WHERE id=" + id;
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            callback.accept(rs);
        } catch (SQLException e) {
        	logger.error("Error while processing a specific story:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
    
    /**
     * Collects every story instance from the database.
     * @param callback Action that is taken with the ResultSet containing
     * the stories.
     */
    synchronized static void processStories(Consumer<ResultSet> callback) {
    	String sql = "SELECT * FROM stories";
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	callback.accept(rs);
            }
        } catch (SQLException e) {
        	logger.error("Error while processing every story:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
    
    /**
     * Collects an individual rating from the database.
     * @param id ID of the rating.
     * @param callback Action that is taken with the ResultSet containing
     * the rating.
     */
    synchronized static void processRating(int id, Consumer<ResultSet> callback) {
    	String sql = "SELECT * FROM ratings WHERE id=" + id;
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            callback.accept(rs);
        } catch (SQLException e) {
        	logger.error("Error while processing a specific rating:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
    
    /**
     * Collects every rating instance from the database.
     * @param callback Action that is taken with the ResultSet containing
     * the ratings.
     */
    synchronized static void processRatings(Consumer<ResultSet> callback) {
    	String sql = "SELECT * FROM ratings";
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	callback.accept(rs);
            }
        } catch (SQLException e) {
        	logger.error("Error while processing every rating:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
}
