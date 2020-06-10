package org.tt.indproj.database_operators;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 */
	static void insertUser(String username, String magicword) {
		String sql = "INSERT INTO people (user, magicword) VALUES ('"
				+ username + "', '" + magicword + "');";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("A new user that goes by '" + username + "' has been added to the database.");
        } catch (SQLException e) {
            logger.error("Error while inserting a user into the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	/**
	 * Inserts a story into database table "stories".
	 * @param makerId Creator of the story.
	 * @param title Title of the story.
	 * @param creationDate The date at which the story was created.
	 * @param content Main content of the story, with default prompts.
	 * @param promptMap Prompt-related information. CSV Format is as follows:<br>
	 * wordIndex;promptId;promptDescription;promptFilled;promptDefault
	 */
	static void insertStory(int makerId, String title, String creationDate, String content, String promptMap) {
		String sql = "INSERT INTO stories (makerid, title, creationdate, content, prompts) VALUES ("
				+ makerId + ", '" + title + "', '" + creationDate + "', '" + content + ", '" + promptMap + "');";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("A new story titled' " + "' has been added to the database.");
        } catch (SQLException e) {
            logger.error("Error while inserting a story into the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
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
	 */
	static void insertRating(int makerId, int raterId, int storyId,
			String viewdate, int grade, int like, int liketype,
			int flag, String comment) {
		String sql = "INSERT INTO ratings (makerid, raterid, storyid,"
				+ "viewdate, grade, like, liketype, flag, comment) VALUES ("
				+  makerId + ", " + raterId + ", " + storyId + ", '"
				+ viewdate + "', " + grade + ", " + like + ", " + liketype + ", "
				+ flag + ", '" + comment + "');";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("A new rating has been added to the database.");
        } catch (SQLException e) {
            logger.error("Error while inserting a rating into the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	/**
	 * Updates a user of specified ID with new information.
	 * @param id ID of target user.
	 * @param username New username.
	 * @param magicword New password.
	 */
	static void updateUser(int id, String username, String magicword) {
		String sql = "UPDATE people SET\n"
				+ "user='" + username + "', "
				+ "magicword='" + magicword + "' WHERE id=" + id + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("User of id " + id + " has been updated.");
        } catch (SQLException e) {
            logger.error("Error while updating a user in the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	/**
	 * Updates a story of specified ID with new information.
	 * @param id ID of target story.
	 * @param makerId Creator of the story.
	 * @param title Title of the story.
	 * @param creationDate The date at which the story was created.
	 * @param content Main content of the story, with default prompts.
	 * @param promptMap Prompt-related information. CSV Format is as follows:<br>
	 * wordIndex;promptId;promptDescription;promptFilled;promptDefault
	 */
	static void updateStory(int id, int makerId, String title, String creationDate, String content, String promptMap) {
		String sql = "UPDATE stories SET\n"
				+ "makerid=" + makerId + ", "
				+ "title='" + title + "', "
				+ "creationdate='" + creationDate + "', "
				+ "content='" + content + "', "
				+ "prompts='" + promptMap + "' WHERE id=" + id + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Story of id " + id + " has been updated.");
        } catch (SQLException e) {
            logger.error("Error while updating a story in the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
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
	 */
	static void updateRating(int id, int makerId, int raterId, int storyId,
			String viewdate, int grade, int like, int liketype,
			int flag, String comment) {
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
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Rating of id " + id + " has been updated.");
        } catch (SQLException e) {
            logger.error("Error while updating a rating in the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	/**
	 * Deletes the user of specified ID from the database.
	 * @param userId ID of user to be deleted.
	 */
	static void deleteUser(int userId) {
		String sql = "DELETE * FROM people WHERE id=" + userId + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("User of id " + userId + " has been deleted.");
        } catch (SQLException e) {
            logger.error("Error while deleting a user from the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	/**
	 * Deletes the story of specified ID from the database.
	 * @param storyId ID of story to be deleted.
	 */
	static void deleteStory(int storyId) {
		String sql = "DELETE * FROM stories WHERE id=" + storyId + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Story of id " + storyId + " has been deleted.");
        } catch (SQLException e) {
            logger.error("Error while deleting a story from the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	/**
	 * Deletes the rating of specified ID from the database.
	 * @param ratingId ID of rating to be deleted.
	 */
	static void deleteRating(int ratingId) {
		String sql = "DELETE * FROM ratings WHERE id=" + ratingId + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Rating of id " + ratingId + " has been deleted.");
        } catch (SQLException e) {
            logger.error("Error while deleting a rating from the database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	/**
	 * Clears all data from specified database table.
	 * @param table Table from which data should be deleted.
	 */
	static void clearTable(String table) {
    	String sql = "DELETE * FROM " + table.toLowerCase();
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            logger.info("Data from table '" + table.toLowerCase() + "' has been deleted.");
        } catch (SQLException e) {
            logger.error("Error while clearing a database table:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
}
