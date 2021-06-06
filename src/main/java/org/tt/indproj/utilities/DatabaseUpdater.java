package org.tt.indproj.utilities;

import org.tt.indproj.core.story.StoryManager;

/**
 * Utility class that updates the database periodically.
 * @author hexfl
 *
 */
public class DatabaseUpdater extends Thread {
	
	/**
	 * Singleton instance of the database updater object.
	 */
	private static DatabaseUpdater instance = null;
	
	/**
	 * Determines whether the database updater should be active.
	 */
	private boolean isRunning;
	
	/**
	 * Getter for the database updater.
	 * @return Singleton instance of the database updater object.
	 */
	public static DatabaseUpdater getUpdater() {
		if (instance == null) instance = new DatabaseUpdater();
		return instance;
	}
	
	/**
	 * Constructor function. Has to be activated separately.
	 */
	private DatabaseUpdater() {
		setDaemon(true);
		isRunning = false;
	}
	
	/**
	 * Activates the database updater. If it is already running, this function does nothing.
	 */
	public void run() {
		if (isRunning) return;
		isRunning = true;
		
		while (isRunning) {
			StoryManager.updateDatabase();
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				shutdown();
			}
		}
	}
	
	/**
	 * Explicitly terminates the database updater.
	 */
	public void shutdown() {
		isRunning = false;
	}
}
