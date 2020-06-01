package org.tt.indproj.database_operators;

/**
 * Makeshift API for database operations.
 * @author terratenff
 */
public class DBBroker {
    /**
     * Creates database table "puretext" if it is missing.
     */
    public static void createDatabase() {
        DBEstablisher.createDatabase();
    }
}
