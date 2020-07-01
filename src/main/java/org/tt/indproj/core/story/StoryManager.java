package org.tt.indproj.core.story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tt.indproj.core.IPrompt;
import org.tt.indproj.core.IStory;
import org.tt.indproj.database_operators.DBBroker;

/**
 * Handles the tracking and creation of story entities.
 * @author terratenff
 *
 */
public class StoryManager {

	/**
	 * List of stories that have seen active use during application run time.
	 */
	private static List<IStory> activeStories = new ArrayList<IStory>();
	
	/**
	 * Getter for an active story entity.
	 * @param id ID of the desired story entity.
	 * @return Story instance with matching ID, or null, if a story with specified ID
	 * could not be found.
	 */
	public static IStory getStory(int id) {
		for (IStory story: activeStories) {
			int storyId = story.getId();
			if (storyId == id) return story;
		}
		return null;
	}
	
	/**
	 * Creates an existing logged user from the database.
	 * @param rs ResultSet from the database. <b>The connection should not be
	 * closed during this operation!</b>
	 * @return Existing logged user based on provided ResultSet from the
	 * database.
	 * @throws SQLException 
	 */
	public synchronized static IStory createStory(ResultSet rs) throws SQLException {
		IStory story;
		if (rs.getString("completemakerid") == null) {
			story = new IncompleteStory(rs);
		} else {
			story = new CompleteStory(rs);
		}
		if (activeStories.contains(story)) activeStories.remove(story);
		activeStories.add(story);
		return story;
	}
	
	/**
	 * Creates a new incomplete story. Note that its ID has to be assigned afterwards.
	 * <b>If a story with the same title already exists, target story is not created!</b>
	 * @param creator Username of the creator.
	 * @param creatorId ID of the creator.
	 * @param title Title of the story.
	 * @param contents Contents of the story. Contains default prompts.
	 * @param prompts Prompts of the story.
	 * @return Incomplete story for the application. Its ID has to be set (via the
	 * DBBroker). If a story with the same title exists, the story will not be created,
	 * and null is returned.
	 */
	public static IStory createIncompleteStory(
			String creator,
			int creatorId,
			String title,
			List<String> contents,
			List<IPrompt> prompts) {
		boolean storyExists = DBBroker.storyExists(title);
		if (storyExists) {
			return null;
		}
		IStory story = new IncompleteStory(
				creator,
				creatorId,
				title,
				contents,
				prompts);
		
		activeStories.add(story);
		return story;
	}
	
	/**
	 * Creates a new complete story. Note that its ID has to be assigned afterwards.
	 * <b>If a story with the same title already exists, target story is not created!</b>
	 * @param templateStory Story instance that was used as a template. Can be both
	 * complete and incomplete.
	 * @param fillerName Name of the user who provided the prompts.
	 * @param fillerId ID of the user who provided the prompts.
	 * @param newTitle Title for the new, complete story.
	 * @param userPrompts Prompts for the new complete story, provided by the specified
	 * user.
	 * @return Complete story for the application. Its ID has to be set (via the
	 * DBBroker). If a story with the same title exists, the story will not be created,
	 * and null is returned.
	 */
	public static IStory createCompleteStory(
			IStory templateStory,
			String fillerName,
			int fillerId,
			String newTitle,
			List<IPrompt> userPrompts) {
		boolean storyExists = DBBroker.storyExists(newTitle);
		if (storyExists) {
			return null;
		}
		IStory story = new CompleteStory(
				templateStory.getMakerName(),
				templateStory.getMakerId(),
				fillerName,
				fillerId,
				newTitle,
				templateStory.getContents(),
				userPrompts
				);
		
		activeStories.add(story);
		return story;
	}
	
	/**
	 * Collects active stories and takes their up-to-date information
	 * to the application database.
	 */
	public static synchronized void updateDatabase() {
		DBBroker.updateStoryViewCounts(activeStories);
	}
}
