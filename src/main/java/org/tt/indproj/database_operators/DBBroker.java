package org.tt.indproj.database_operators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tt.indproj.core.IRating;
import org.tt.indproj.core.IStory;
import org.tt.indproj.core.IUser;

/**
 * Makeshift API for database operations.
 * @author terratenff
 */
public class DBBroker {
	
	/**
	 * Writes down some notes from general database operations.
	 */
	private static Logger logger = LoggerFactory.getLogger(DBBroker.class);
    
	/**
	 * Establishes a database for the application to use.
	 * @param reset Flag for resetting the database (delete file and create a new one).
	 * @param clear Flag for clearing the contents of the database. If database does
	 * not exist, it is created instead. This flag is ignored if 'reset' is set to true.
	 */
    public static void setDatabase(boolean reset, boolean clear, boolean discreet) {
    	if (reset) DBEstablisher.resetDatabase();
    	else if (clear) DBEstablisher.clearDatabase();
    	else DBEstablisher.createDatabase(discreet);
    }
    
    /**
     * Inserts a user into the database.
     * @param user Target user entity.
     */
    public static void insertUser(IUser user) {
    	// TODO
    	//DBModifier.insertUser(username, pwd);
    }
    
    /**
     * Inserts a story into the database.
     * @param story Target story entity.
     */
    public static void insertStory(IStory story) {
    	// TODO
    	//DBModifier.insertStory(makerId, title, creationdate, content, prompts);
    }
    
    /**
     * Inserts a rating into the database.
     * @param rating Target rating entity.
     */
    public static void insertRating(IRating rating) {
    	// TODO
    	
    	//DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	//String dateString = format.format(viewDate);
    	//int likeInt = like ? 1 : 0;
    	//int flagInt = flag ? 1 : 0;
    }
    
    /**
     * Updates specified user entity in the database.
     * @param user Target user entity that the database is to match.
     */
    public static void updateUser(IUser user) {
    	// TODO
    }
    
    /**
     * Updates specified story entity in the database.
     * @param story Target story entity that the database is to match.
     */
    public static void updateStory(IStory story) {
    	// TODO
    }
    
    /**
     * Updates specified rating entity in the database.
     * @param rating Target rating entity that the database is to match.
     */
    public static void updateRating(IRating rating) {
    	// TODO
    }
    
    /**
     * Deletes specified user from the database.
     * @param user Target user subject to removal.
     */
    public static void deleteUser(IUser user) {
    	// TODO
    }
    
    /**
     * Deletes specified story from the database.
     * @param story Target story subject to removal.
     */
    public static void deleteStory(IStory story) {
    	// TODO
    }
    
    /**
     * Deletes specified rating from the database.
     * @param rating Target rating subject to removal.
     */
    public static void deleteRating(IRating rating) {
    	// TODO
    }
    
    /**
     * Selects a specific user from the database.
     * @param id ID of target user.
     * @return User instance with matching ID, or null.
     */
    public static IUser getUser(int id) {
    	// TODO
    	return null;
    }
    
    /**
     * Selects a specific story from the database.
     * @param id ID of target story.
     * @return Story instance with matching ID, or null.
     */
    public static IStory getStory(int id) {
    	// TODO
    	return null;
    }
    
    /**
     * Selects a specific rating from the database.
     * @param id ID of target rating.
     * @return Rating instance with matching ID, or null.
     */
    public static IRating getRating(int id) {
    	// TODO
    	return null;
    }
    
    /**
     * Selects every user from the database.
     * @return List of every user from the database.
     */
    public static List<IUser> getUsers() {
    	// TODO
    	return null;
    }
    
    /**
     * Selects every story from the database.
     * @return List of every story from the database.
     */
    public static List<IStory> getStories() {
    	// TODO
    	return null;
    }
    
    /**
     * Selects every rating from the database.
     * @return List of every rating from the database.
     */
    public static List<IRating> getRatings() {
    	// TODO
    	return null;
    }
    
    /**
     * Attempts to perform a login of specified user.
     * @param username Username put in by the user.
     * @param password Password put in by the user.
     * @return An instance of a user if the login was successful. A null object is
     * given upon a failure.
     */
    public static IUser login(String username, String password) {
    	// TODO
    	return null;
    }
    
    /**
     * Prints (with a logger) the contents of specified database table.
     * @param table Target database table (if it exists).
     */
    public static void viewTable(String table, boolean skipLargeInstances) {
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
     * Clears all data from specified database table.
     * @param table Target database table subject to clearing of data.
     */
    public static void clearTable(String table) {
    	DBModifier.clearTable(table);
    }
    
    /**
     * Executes SQL provided by the user.
     * @param sqlQuery SQL provided by the user, subject to execution.
     */
    public static void executeSql(String sqlQuery, boolean skipLargeInstances) {
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            boolean outcome = stmt.execute(sqlQuery);
            if (outcome) {
            	ResultSet rs = stmt.getResultSet();
            	logger.info("Results from the query:");
            	DBOperations.printResultSet(rs, skipLargeInstances);
            } else {
            	logger.info("SQL query has been completed.");
            }
        } catch (SQLException e) {
        	logger.error("Error while executing a custom sql query:");
			logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
}
