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
     * Creates the primary database table, "puretext", that is set
     * to contain the story entries.
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
    	
    	String sql1 = "DELETE * FROM people WHERE id != 0";
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
     * Initializes application database. The structure of the database is set, and an entry
     * into the table "people" is made, set to represent people who do not use an account.
     */
    static void initializeDatabase() {
    	String sql1 = "CREATE TABLE IF NOT EXISTS people (\n"
        		+ "id INTEGER NOT NULL PRIMARY KEY,"
                + "user VARCHAR(50),"
                + "magicword VARCHAR(50)"
                + ");";
        String sql2 = "CREATE TABLE IF NOT EXISTS stories (\n"
        		+ "id INTEGER NOT NULL PRIMARY KEY,"
                + "makerid INTEGER NOT NULL DEFAULT 0,"
                + "title VARCHAR(50),"
                + "creationdate VARCHAR(16)," // 'YYYY-MM-DD HH:MM'
                + "content TEXT," // Story itself with default prompts.
                + "prompts TEXT," // CSV: wordIndex;promptId;promptDescription;promptFilled;promptDefault
                + "FOREIGN KEY(makerid) REFERENCES people(id)\n"
                + "ON UPDATE CASCADE\n"
                + "ON DELETE SET DEFAULT"
                + ");";
        String sql3 = "CREATE TABLE IF NOT EXISTS ratings (\n"
        		+ "id INTEGER NOT NULL PRIMARY KEY,"
                + "makerid INTEGER NOT NULL DEFAULT 0,"
                + "raterid INTEGER NOT NULL DEFAULT 0,"
                + "storyid INTEGER NOT NULL,"
                + "viewdate CHAR(16)," // 'YYYY-MM-DD HH:MM'
                + "grade INTEGER,"
                + "like INTEGER,"
                + "liketype INTEGER,"
                + "flag INTEGER,"
                + "comment VARCHAR(255),"
                + "FOREIGN KEY(makerid) REFERENCES people(id)\n"
                + "ON UPDATE CASCADE\n"
                + "ON DELETE SET DEFAULT,\n"
                + "FOREIGN KEY(raterid) REFERENCES people(id)\n"
                + "ON UPDATE CASCADE\n"
                + "ON DELETE SET DEFAULT,\n"
                + "FOREIGN KEY(storyid) REFERENCES stories(id)\n"
                + "ON UPDATE CASCADE\n"
                + "ON DELETE CASCADE"
                + ");";
        
        String sql4 = "INSERT INTO people (user, magicword) VALUES ('The Anonymous Collective', '');";
        // One is not supposed to log into the application as "The Anonymous Collective".
        // It is reserved for those users who use the services without an account.

        Connection conn = null;
        try {
            conn = DBOperations.establishConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
        } catch (SQLException e) {
            logger.error("Error while creating database:");
            logger.error(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
}
