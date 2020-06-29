package org.tt.indproj.utilities;

import org.tt.indproj.core.story.StoryManager;

public class DatabaseUpdater extends Thread {
	
	private static DatabaseUpdater instance = null;
	
	private boolean isRunning;
	
	public static DatabaseUpdater getUpdater() {
		if (instance == null) instance = new DatabaseUpdater();
		return instance;
	}
	
	private DatabaseUpdater() {
		setDaemon(true);
		isRunning = false;
	}
	
	public void run() {
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
	
	public void shutdown() {
		isRunning = false;
	}
}
