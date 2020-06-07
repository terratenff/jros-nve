package org.tt.indproj.database_operators;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DBModifier {
	
	private static Logger logger = LoggerFactory.getLogger(DBModifier.class);
	
	static void insertUser(String username, String magicword) {
		String sql = "INSERT INTO people (user, magicword) VALUES ('"
				+ username + "', '" + magicword + "');";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.info("Error while inserting a user into the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	static void insertStory(int makerId, String content, String promptMap) {
		String sql = "INSERT INTO stories (makerid, content, prompts) VALUES ("
				+ makerId + ", '" + content + ", '" + promptMap + "');";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.info("Error while inserting a story into the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
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
        } catch (SQLException e) {
            logger.info("Error while inserting a rating into the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	static void updateUser(int id, String username, String magicword) {
		String sql = "UPDATE people SET\n"
				+ "user='" + username + "', "
				+ "magicword='" + magicword + "' WHERE id=" + id + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.info("Error while updating a user in the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	static void updateStory(int id, int makerId, String content, String promptMap) {
		String sql = "UPDATE stories SET\n"
				+ "makerid=" + makerId + ", "
				+ "content='" + content + "', "
				+ "prompts='" + promptMap + "' WHERE id=" + id + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.info("Error while updating a story in the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
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
        } catch (SQLException e) {
            logger.info("Error while updating a rating in the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	static void deleteUser(int userId) {
		String sql = "DELETE * FROM people WHERE id=" + userId + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.info("Error while deleting a user from the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	static void deleteStory(int storyId) {
		String sql = "DELETE * FROM stories WHERE id=" + storyId + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.info("Error while deleting a story from the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
	
	static void deleteRating(int ratingId) {
		String sql = "DELETE * FROM ratings WHERE id=" + ratingId + ";";
		
		Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.info("Error while deleting a rating from the database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
	}
}
