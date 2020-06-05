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
	
	private static Logger logger = LoggerFactory.getLogger(DBEstablisher.class);
	
    /**
     * Creates the primary database table, "puretext", that is set
     * to contain the story entries.
     */
    static void createDatabase() {
    	File database = new File("appdata.db");
    	if (database.exists()) {
    		logger.info("Database file currently exists.");
    		logger.info("A crash may occur if it is not formatted properly.");
    	}
    	else {
    		logger.info("Proceeding to create application database...");
    		initializeDatabase();
    	}
    }
    
    static void resetDatabase() {
    	try {
    		File file = new File("appdata.db");
			boolean outcome = Files.deleteIfExists(file.toPath());
			if (outcome) logger.info("Database reset was successful!");
			else logger.info("Database did not exist.");
			createDatabase();
		} catch (IOException e) {
			logger.info("Database reset was not successful (IOException):");
			logger.info(e.getMessage());
		}
    }
    
    static void clearDatabase() {
    	File database = new File("appdata.db");
    	if (!database.exists()) {
    		logger.warn("Database file does not exist.");
    		createDatabase();
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
            logger.info("Error while clearing database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
    
    static void initializeDatabase() {
    	String sql1 = "CREATE TABLE IF NOT EXISTS people (\n"
        		+ "id INTEGER NOT NULL PRIMARY KEY,"
                + "user VARCHAR(50),"
                + "magicword VARCHAR(50)"
                + ");";
        String sql2 = "CREATE TABLE IF NOT EXISTS stories (\n"
        		+ "id INTEGER NOT NULL PRIMARY KEY,"
                + "makerid INTEGER NOT NULL DEFAULT 0,"
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
            logger.info("Error while creating database:");
            logger.info(e.getMessage());
        } finally {
            DBOperations.terminateConnection(conn);
        }
    }
}
