package org.tt.indproj.database_operators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Set of database operations that concern the creation side of things.
 * @author terratenff
 */
class DBEstablisher {
	
	/**
	 * Writes down information relating to database initializations.
	 */
	private static Logger logger = LoggerFactory.getLogger(DBEstablisher.class);
	
    /**
     * Creates the application database.
     * @param discreet Deterimnes whether the user should be bothered with
     * information about the database file existing if an attempt to create
     * one is made.
     */
    static void createDatabase(boolean discreet) {
    	File database = new File("appdata.db");
    	if (database.exists()) {
    		if (!discreet) {
    			logger.info("Database file currently exists.");
    		}
    	}
    	else {
    		logger.info("Proceeding to create application database...");
    		initializeDatabase();
    	}
    }
    
    /**
     * Deletes application database and creates it again from scratch.
     */
    static void resetDatabase() {
    	try {
    		File file = new File("appdata.db");
			boolean outcome = Files.deleteIfExists(file.toPath());
			if (outcome) logger.info("Database reset was successful!");
			else logger.info("Database did not exist.");
			createDatabase(false);
		} catch (IOException e) {
			logger.error("Database reset was not successful (IOException):");
			logger.error(e.getMessage());
		}
    }
    
    /**
     * Deletes all data from application database.
     */
    static void clearDatabase() {
    	File database = new File("appdata.db");
    	if (!database.exists()) {
    		logger.warn("Database file does not exist.");
    		createDatabase(false);
    		return;
    	}
    	
    	String sql1 = "DELETE * FROM people";
    	String sql2 = "DELETE * FROM stories";
    	String sql3 = "DELETE * FROM ratings";
    	
    	Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
        } catch (SQLException e) {
            logger.error("Error while clearing database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
    
    /**
     * Initializes application database.
     */
    static void initializeDatabase() {
    	String sql1 = "CREATE TABLE IF NOT EXISTS people (\n"
        		+ "id INTEGER NOT NULL PRIMARY KEY,"
                + "username VARCHAR(50) UNIQUE,"
                + "magicword VARCHAR(50)"
                + ");";
        String sql2 = "CREATE TABLE IF NOT EXISTS stories (\n"
        		+ "id INTEGER NOT NULL PRIMARY KEY,"
                + "makerid INTEGER,"
                + "title VARCHAR(50),"
                + "creationdate VARCHAR(16)," // 'YYYY-MM-DD HH:MM'
                + "content TEXT," // Story itself with default prompts.
                + "prompts TEXT," // CSV: wordIndex;promptId;promptDescription;promptFilled;promptDefault
                + "FOREIGN KEY(makerid) REFERENCES people(id)\n"
                + "ON UPDATE CASCADE\n"
                + "ON DELETE SET NULL"
                + ");";
        String sql3 = "CREATE TABLE IF NOT EXISTS ratings (\n"
        		+ "id INTEGER NOT NULL PRIMARY KEY,"
                + "makerid INTEGER,"
                + "raterid INTEGER,"
                + "storyid INTEGER NOT NULL,"
                + "viewdate CHAR(16)," // 'YYYY-MM-DD HH:MM'
                + "grade INTEGER,"
                + "like INTEGER,"
                + "liketype INTEGER,"
                + "flag INTEGER,"
                + "comment VARCHAR(255),"
                + "FOREIGN KEY(makerid) REFERENCES people(id)\n"
                + "ON UPDATE CASCADE\n"
                + "ON DELETE SET NULL,\n"
                + "FOREIGN KEY(raterid) REFERENCES people(id)\n"
                + "ON UPDATE CASCADE\n"
                + "ON DELETE SET NULL,\n"
                + "FOREIGN KEY(storyid) REFERENCES stories(id)\n"
                + "ON UPDATE CASCADE\n"
                + "ON DELETE CASCADE"
                + ");";

        Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
        } catch (SQLException e) {
            logger.error("Error while creating database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
}
