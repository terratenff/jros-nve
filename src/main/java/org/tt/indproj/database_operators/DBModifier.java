package org.tt.indproj.database_operators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tt.indproj.utilities.Encryption;

/**
 * Set of database operations that concern the modification of the database
 * in some form.
 * @author terratenff
 *
 */
class DBModifier {
	
	/**
	 * Writes down information relating to changes in the database.
	 */
	private static Logger logger = LoggerFactory.getLogger(DBModifier.class);
	
	/**
	 * Inserts a user into database table "people".
	 * @param username Target username.
	 * @param magicword Password.
	 * @param Salt for concealing the password.
	 * @return Outcome of the operation.
	 */
	static synchronized boolean insertUser(
			String username,
			String magicword,
			String salt) {
		String sql = "INSERT INTO people (username, magicword, salt) VALUES ('"
				+ username + "', '" + magicword + "', '" + salt + "');";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("A new user that goes by '" + username + "' has been added to the database.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while inserting a user into the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Inserts a story into database table "stories".
	 * @param makerId ID of the creator of the story.
	 * @param author Username of the creator of the story.
	 * @param fillerId ID of the prompt filler of the story.
	 * @param fillerAuthor Username of the filler of the story.
	 * @param title Title of the story.
	 * @param creationDate The date at which the story was created.
	 * @param content Main content of the story, with default prompts.
	 * @param promptMap Prompt-related information. CSV Format is as follows:<br>
	 * wordIndex;promptId;promptDescription;promptFilled;promptDefault
	 * @return Outcome of the operation.
	 */
	
	/**
	 * 
	 * @param makerId
	 * @param author
	 * @param fillerId
	 * @param fillerAuthor
	 * @param title
	 * @param creationDate
	 * @param content
	 * @param promptMap
	 * @return
	 */
	static synchronized boolean insertStory( // TODO (insert + update)
			int makerId,
			String author,
			int fillerId,
			String fillerAuthor,
			String title,
			String creationDate,
			String content,
			String promptMap) {
		String sql = "INSERT INTO stories (templatemakerid, templateauthor, completemakerid, completeauthor, title, creationdate, content, prompts) VALUES ("
				+ makerId + ", '"
				+ author + "', "
				+ fillerId + ", '"
				+ fillerAuthor + "', '"
				+ title + "', '"
				+ creationDate + "', '"
				+ content + ", '"
				+ promptMap
				+ "');";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("A new story titled' " + "' has been added to the database.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while inserting a story into the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Inserts a rating into database table "ratings".
	 * @param makerId Creator of the story that the rating focuses on.
	 * @param raterId Creator of the rating.
	 * @param storyId ID of the story that the rating focuses on.
	 * @param viewdate The date at which the rating is made. Also set to imply the date at which the
	 * story was viewed.
	 * @param grade Numerical grade given to the story.
	 * @param like Flag for whether the rater personally liked the story.
	 * @param liketype Flag for the specifics of why the rater liked the story.
	 * @param flag Flag for whether the rater thinks that the story violates service rules.
	 * @param comment Text for the rater to provide more context for the rating.
	 * @return Outcome of the operation.
	 */
	static synchronized boolean insertRating(
			int makerId,
			int raterId,
			int storyId,
			String viewdate,
			int grade,
			int like,
			int liketype,
			int flag,
			String comment) {
		String sql = "INSERT INTO ratings (makerid, raterid, storyid,"
				+ "viewdate, grade, like, liketype, flag, comment) VALUES ("
				+  makerId + ", " + raterId + ", " + storyId + ", '"
				+ viewdate + "', " + grade + ", " + like + ", " + liketype + ", "
				+ flag + ", '" + comment + "');";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("A new rating has been added to the database.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while inserting a rating into the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Updates a user of specified ID with new information.
	 * @param id ID of target user.
	 * @param username New username.
	 * @param magicword New password.
	 * @param salt New salt value.
	 * @return Outcome of the operation.
	 */
	static synchronized boolean updateUser(
			int id,
			String username,
			String magicword,
			String salt) {
		String sql = "UPDATE people SET\n"
				+ "username='" + username + "', "
				+ "magicword='" + magicword + "', "
				+ "salt='" + salt + "' WHERE id=" + id + ";";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("User of id " + id + " has been updated.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while updating a user in the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Updates a story of specified ID with new information.
	 * @param id ID of target story.
	 * @param makerId ID of the creator of the story.
	 * @param author Username of the creator of the story.
	 * @param fillerId ID of the prompt filler of the story.
	 * @param fillerAuthor Username of the filler of the story.
	 * @param title Title of the story.
	 * @param creationDate The date at which the story was created.
	 * @param content Main content of the story, with default prompts.
	 * @param promptMap Prompt-related information. CSV Format is as follows:<br>
	 * wordIndex;promptId;promptDescription;promptFilled;promptDefault
	 * @return Outcome of the operation.
	 */
	static synchronized boolean updateStory(
			int id,
			int makerId,
			String author,
			int fillerId,
			String fillerAuthor,
			String title,
			String creationDate,
			String content,
			String promptMap) {
		String sql = "UPDATE stories SET\n"
				+ "templatemakerid=" + makerId + ", "
				+ "templateauthor='" + author + "', "
				+ "completemakerid=" + fillerId + ", "
				+ "completeauthor='" + fillerAuthor + "', "
				+ "title='" + title + "', "
				+ "creationdate='" + creationDate + "', "
				+ "content='" + content + "', "
				+ "prompts='" + promptMap + "' WHERE id=" + id + ";";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Story of id " + id + " has been updated.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while updating a story in the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Updates a rating of specified ID with new information.
	 * @param id ID of target rating.
	 * @param makerId Creator of the story that the rating focuses on.
	 * @param raterId Creator of the rating.
	 * @param storyId ID of the story that the rating focuses on.
	 * @param viewdate The date at which the rating is made. Also set to imply the date at which the
	 * story was viewed.
	 * @param grade Numerical grade given to the story.
	 * @param like Flag for whether the rater personally liked the story.
	 * @param liketype Flag for the specifics of why the rater liked the story.
	 * @param flag Flag for whether the rater thinks that the story violates service rules.
	 * @param comment Text for the rater to provide more context for the rating.
	 * @return Outcome of the operation.
	 */
	static synchronized boolean updateRating(
			int id,
			int makerId,
			int raterId,
			int storyId,
			String viewdate,
			int grade,
			int like,
			int liketype,
			int flag,
			String comment) {
		String sql = "UPDATE ratings SET\n"
				+ "makerid=" + makerId + ", "
				+ "raterid=" + raterId + ", "
				+ "storyid=" + storyId + ", "
				+ "viewdate='" + viewdate + "', "
				+ "grade=" + grade + ", "
				+ "like=" + like + ", "
				+ "liketype=" + liketype + ", "
				+ "flag=" + flag + ", "
				+ "comment='" + comment + "' WHERE id=" + id + ";";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Rating of id " + id + " has been updated.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while updating a rating in the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Deletes the user of specified ID from the database.
	 * @param userId ID of user to be deleted.
	 * @return Outcome of the operation.
	 */
	static synchronized boolean deleteUser(int userId) {
		String sql = "DELETE * FROM people WHERE id=" + userId + ";";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("User of id " + userId + " has been deleted.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while deleting a user from the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Deletes the story of specified ID from the database.
	 * @param storyId ID of story to be deleted.
	 * @return Outcome of the operation.
	 */
	static synchronized boolean deleteStory(int storyId) {
		String sql = "DELETE * FROM stories WHERE id=" + storyId + ";";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Story of id " + storyId + " has been deleted.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while deleting a story from the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Deletes the rating of specified ID from the database.
	 * @param ratingId ID of rating to be deleted.
	 * @return Outcome of the operation.
	 */
	static synchronized boolean deleteRating(int ratingId) {
		String sql = "DELETE * FROM ratings WHERE id=" + ratingId + ";";
		boolean outcome;
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Rating of id " + ratingId + " has been deleted.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while deleting a rating from the database:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
	}
	
	/**
	 * Clears all data from specified database table.
	 * @param table Table from which data should be deleted.
	 * @return Outcome of the operation.
	 */
	static synchronized boolean clearTable(String table) {
    	String sql = "DELETE * FROM " + table.toLowerCase();
    	boolean outcome;
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Data from table '" + table.toLowerCase() + "' has been deleted.");
            outcome = true;
        } catch (SQLException e) {
            logger.error("Error while clearing a database table:");
            logger.error(e.getMessage());
            outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
    }
	
	/**
     * Executes SQL provided by the user.
     * @param sqlQuery SQL provided by the user, subject to execution.
     * @return Outcome of the operation.
     */
    static synchronized boolean executeSql(String sqlQuery, boolean skipLargeInstances) {
    	boolean outcome;
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            boolean result = stmt.execute(sqlQuery);
            if (result) {
            	ResultSet rs = stmt.getResultSet();
            	logger.info("Results from the query:");
            	DBOperations.printResultSet(rs, skipLargeInstances);
            } else {
            	logger.info("SQL query has been completed.");
            }
            outcome = true;
        } catch (SQLException e) {
        	logger.error("Error while executing a custom sql query:");
			logger.error(e.getMessage());
			outcome = false;
        } finally {
            DBOperations.terminateConnection(conn);
        }
        return outcome;
    }
    
    /**
     * Updates view counts on specified story instances.
     * @param storyViews Map of story views. Key represents story ID. Value represents
     * current view count of the story.
     */
    static synchronized void updateStoryViewCounts(Map<Integer, Integer> storyViews) {
    	Connection conn = null;
    	String sql;
    	try {
    		conn = DBOperations.establishConnection();
    		for (Entry<Integer, Integer> keyvalue : storyViews.entrySet()) {
    			int id = keyvalue.getKey();
    			int viewCount = keyvalue.getValue();
    			sql = "UPDATE stories SET viewcount=" + viewCount + " WHERE id=" + id;
    			Statement stmt = conn.createStatement();
    			stmt.execute(sql);
    		}
    	} catch (SQLException e) {
    		logger.error("Error while updating story view counts:");
			logger.error(e.getMessage());
    	} finally {
    		DBOperations.terminateConnection(conn);
    	}
    }
}
