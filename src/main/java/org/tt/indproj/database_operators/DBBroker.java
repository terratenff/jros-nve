package org.tt.indproj.database_operators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tt.indproj.core.IPrompt;
import org.tt.indproj.core.IRating;
import org.tt.indproj.core.IStory;
import org.tt.indproj.core.IUser;
import org.tt.indproj.core.user.UserManager;
import org.tt.indproj.utilities.Encryption;
import org.tt.indproj.core.story.StoryManager;
import org.tt.indproj.core.rating.RatingManager;

/**
 * Makeshift API for database operations.
 * @author terratenff
 */
public class DBBroker {
	
	/**
	 * Writes down information relating to database mediations.
	 */
	private static Logger logger = LoggerFactory.getLogger(DBBroker.class);
	
	/**
	 * Temporary container for a singular user.
	 * Used in conjunction with a Consumer-callback function.
	 */
	private static IUser userTemp;
	
	/**
	 * Temporary container for a singular story.
	 * Used in conjunction with a Consumer-callback function.
	 */
	private static IStory storyTemp;
	
	/**
	 * Temporary container for a singular rating.
	 * Used in conjunction with a Consumer-callback function.
	 */
	private static IRating ratingTemp;
	
	/**
	 * Temporary container for a list of users.
	 * Used in conjunction with a Consumer-callback function.
	 */
	private static List<IUser> userListTemp = new ArrayList<IUser>();
	
	/**
	 * Temporary container for a list of stories.
	 * Used in conjunction with a Consumer-callback function.
	 */
	private static List<IStory> storyListTemp = new ArrayList<IStory>();
	
	/**
	 * Temporary container for a list of ratings.
	 * Used in conjunction with a Consumer-callback function.
	 */
	private static List<IRating> ratingListTemp = new ArrayList<IRating>();
    
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
     * Inserts a user into the database. Also assigns an appropriate ID for it.
     * @param user Target user entity.
     */
    public static void insertUser(IUser user) {
    	String username = user.getUsername();
    	String pwd = user.getPassword();
    	String salt = user.getSalt();
    	boolean outcome = DBModifier.insertUser(username, pwd, salt);
    	if (outcome) {
    		int id = DBReader.getUserId(username);
    		user.assignId(id);
    	}
    }
    
    /**
     * Inserts a story into the database. Also assigns an appropriate ID for it.
     * @param story Target story entity.
     */
    public static void insertStory(IStory story) {
    	// TODO
    	//DBModifier.insertStory(makerId, title, creationdate, content, prompts);
    }
    
    /**
     * Inserts a rating into the database. Also assigns an appropriate ID for it.
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
    	int id = user.getId();
    	String username = user.getUsername();
    	String pwd = user.getPassword();
    	String salt = user.getSalt();
    	DBModifier.updateUser(id, username, pwd, salt);
    }
    
    /**
     * Updates specified story entity in the database.
     * @param story Target story entity that the database is to match.
     */
    public static void updateStory(IStory story) {
    	int id = story.getId();
    	int makerId = story.getMakerId();
    	String author = story.getMakerName();
    	int fillerId = story.getMakerId();
    	String fillerAuthor = story.getMakerName();
    	String title = story.getTitle();
    	
    	LocalDate date = story.getCreationDate();
    	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	String creationDate = format.format(date);
    	
    	List<String> contentList = story.getContents();
    	String content = "";
    	for (String word : contentList) {
    		content += word + " ";
    	}
    	
    	List<IPrompt> prompts = story.getPrompts();
    	String promptMap = "";
    	for (IPrompt prompt : prompts) {
    		promptMap += prompt.toString() + "\n";
    	}
    	
    	DBModifier.updateStory(
    			id,
    			makerId,
    			author,
    			fillerId,
    			fillerAuthor,
    			title,
    			creationDate,
    			content,
    			promptMap);
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
    	int id = user.getId();
    	DBModifier.deleteUser(id);
    }
    
    /**
     * Deletes specified story from the database.
     * @param story Target story subject to removal.
     */
    public static void deleteStory(IStory story) {
    	int id = story.getId();
    	DBModifier.deleteStory(id);
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
    public synchronized static IUser getUser(int id) {
    	DBReader.processUser(id, rs -> {
    		try {
				userTemp = UserManager.createUser(rs);
			} catch (SQLException e) {
				logger.error("Error in user acquisition callback:");
				logger.error(e.getMessage());
			}
    	});
    	return userTemp;
    }
    
    /**
     * Selects a specific story from the database.
     * @param id ID of target story.
     * @return Story instance with matching ID, or null.
     */
    public synchronized static IStory getStory(int id) {
    	DBReader.processStory(id, rs -> {
    		try {
				storyTemp = StoryManager.createStory(rs);
			} catch (SQLException e) {
				logger.error("Error in story acquisition callback:");
				logger.error(e.getMessage());
			}
    	});
    	return storyTemp;
    }
    
    /**
     * Selects a specific rating from the database.
     * @param id ID of target rating.
     * @return Rating instance with matching ID, or null.
     */
    public synchronized static IRating getRating(int id) {
    	// TODO
    	return ratingTemp;
    }
    
    /**
     * Selects every user from the database.
     * @return List of every user from the database.
     */
    public synchronized static List<IUser> getUsers() {
    	userListTemp.clear();
    	DBReader.processUsers(rs -> {
    		try {
				userListTemp.add(UserManager.createUser(rs));
			} catch (SQLException e) {
				logger.error("Error in user acquisition callback:");
				logger.error(e.getMessage());
			}
    	});
    	return userListTemp;
    }
    
    /**
     * Selects every story from the database.
     * @return List of every story from the database.
     */
    public synchronized static List<IStory> getStories() {
    	storyListTemp.clear();
    	DBReader.processStories(rs -> {
    		try {
				storyListTemp.add(StoryManager.createStory(rs));
			} catch (SQLException e) {
				logger.error("Error in story acquisition callback:");
				logger.error(e.getMessage());
			}
    	});
    	return storyListTemp;
    }
    
    /**
     * Selects every rating from the database.
     * @return List of every rating from the database.
     */
    public synchronized static List<IRating> getRatings() {
    	// TODO
    	return ratingListTemp;
    }
    
    /**
     * Attempts to perform a login of specified user.
     * @param username Username put in by the user.
     * @param password Password put in by the user.
     * @return Instance of the user if login was successful. null object
     * is given if login failed.
     */
    public static IUser login(String username, String password) {
    	int outcome = DBReader.login(username, password);
    	if (outcome >= 0) {
    		return getUser(outcome);
    	} else return null;
    }
    
    /**
     * Executes SQL provided by the user.
     * @param sqlQuery SQL provided by the user, subject to execution.
     * @param skipLargeInstances Flag
     */
    public static void executeSql(String sqlQuery, boolean skipLargeInstances) {
    	// TODO: Edit DBModifier.executeSql(...) function.
    	DBModifier.executeSql(sqlQuery, skipLargeInstances);
    }
    
    /**
     * Prints the contents of specified database table.
     * @param table Target database table (if it exists).
     */
    public static void viewTable(String table, boolean skipLargeInstances) {
    	DBReader.viewTable(table, skipLargeInstances);
    }
    
    /**
     * Clears all data from specified database table.
     * @param table Target database table subject to clearing of data.
     */
    public static void clearTable(String table) {
    	DBModifier.clearTable(table);
    }
    
    /**
     * Collects IDs and view counts from a collection of active stories (that exist
     * in the database) and sets the database up-to-date.
     * @param stories List of stories that are to be updated. <b>These stories
     * must exist within the database!</b>
     */
    public static synchronized void updateStoryViewCounts(List<IStory> stories) {
    	Map<Integer, Integer> storyViews = new HashMap<Integer, Integer>();
		for (IStory story : stories) {
			storyViews.put(story.getId(), story.getViewCount());
		}
    	DBModifier.updateStoryViewCounts(storyViews);
    }
    
    /**
     * Checks whether specified user exists.
     * @param username Target user as username.
     * @return true, if exists. false otherwise.
     */
    public static boolean userExists(String username) {
    	return DBReader.usernameExists(username);
    }
    
    /**
     * Checks whether specified story exists.
     * @param title Target story as its title.
     * @return true, if exists. false otherwise.
     */
    public static boolean storyExists(String title) {
    	return DBReader.storyTitleExists(title);
    }
}
