package org.tt.indproj.database_operators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.tt.indproj.core.IRating;
import org.tt.indproj.core.IStory;
import org.tt.indproj.core.IUser;

/**
 * Makeshift API for database operations.
 * @author terratenff
 */
public class DBBroker {
    
	/**
	 * Establishes a database for the application to use.
	 * @param reset Flag for resetting the database (delete file and create a new one).
	 * @param clear Flag for clearing the contents of the database. If database does
	 * not exist, it is created instead. This flag is ignored if 'reset' is set to true.
	 */
    public static void setDatabase(boolean reset, boolean clear) {
    	if (reset) DBEstablisher.resetDatabase();
    	else if (clear) DBEstablisher.clearDatabase();
    	else DBEstablisher.createDatabase();
    }
    
    public static void insertUser(IUser user) {
    	// TODO
    	//DBModifier.insertUser(username, pwd);
    }
    
    public static void insertStory(IStory story) {
    	// TODO
    	//DBModifier.insertStory(makerId, content, prompts);
    }
    
    public static void insertRating(IRating rating) {
    	// TODO
    	
    	//DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	//String dateString = format.format(viewDate);
    	//int likeInt = like ? 1 : 0;
    	//int flagInt = flag ? 1 : 0;
    }
}
