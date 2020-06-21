package org.tt.indproj.core.story;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.tt.indproj.core.IPrompt;
import org.tt.indproj.core.IRating;
import org.tt.indproj.core.IStory;

/**
 * Common class for both complete and incomplete stories.
 * @author terratenff
 *
 */
abstract class Story implements IStory {

	/**
	 * Identifying value of the story instance.
	 */
	private int id;
	
	/**
	 * Flag for whether an ID value has been assigned for the story.
	 */
	private boolean unassigned = true;
	
	/**
	 * Identifying value of the story author.
	 */
	private int creatorId;
	
	/**
	 * Story author.
	 */
	private String creatorName;
	
	/**
	 * Identifying value of the story prompt filler.
	 */
	private int fillerId;
	
	/**
	 * Story prompt filler.
	 */
	private String fillerName;
	
	/**
	 * Title of the story.
	 */
	private String title;
	
	/**
	 * Time at which the story was created.
	 */
	private LocalDate creationDate;
	
	/**
	 * Number of times that the story has been viewed, by both logged users
	 * and private users.
	 */
	private int viewCount;
	
	/**
	 * List of words that make the story. The spots where the prompts are contain
	 * default inputs.
	 */
	private List<String> contents = new ArrayList<String>();
	
	/**
	 * List of prompts created for the story. Includes/Excludes user input, depending
	 * on story type (Incomplete stories exclude user input, whereas complete stories
	 * include user input).
	 */
	private List<IPrompt> prompts = new ArrayList<IPrompt>();
	
	/**
	 * List of ratings given for the story.
	 */
	private List<IRating> ratings = new ArrayList<IRating>();
	
	/**
	 * Story constructor function. Used for creating new story instances. Note that
	 * once the story has been created, its ID has to be assigned afterwards.
	 * @param creator Story author by username.
	 * @param title Story title.
	 * @param contents List of strings that make the story. Prompts within are
	 * default inputs.
	 * @param prompts List of prompts used in the story.
	 */
	Story(String creator, String title, List<String> contents, List<IPrompt> prompts) {
		// TODO
	}
	
	/**
	 * Story constructor function. Used for creating existing story instances
	 * straight from the application database.
	 * @param rs ResultSet from the database. <b>The connection to the database
	 * should not be closed during this procedure!</b>
	 */
	Story(ResultSet rs) {
		// TODO
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void assignId(int id) {
		if (unassigned) {
			this.id = id;
			unassigned = false;
		}
	}
	
	@Override
	public int getMakerId() {
		return creatorId;
	}
	
	@Override
	public String getMakerName() {
		return creatorName;
	}
	
	@Override
	public int getFillerId() {
		return fillerId;
	}
	
	@Override
	public String getFillerName() {
		return fillerName;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	@Override
	public synchronized int getViewCount() {
		return viewCount;
	}
	
	@Override
	public synchronized int view() {
		++viewCount;
		return viewCount;
	}
	
	@Override
	public List<String> getContents() {
		return contents;
	}
	
	@Override
	public List<IPrompt> getPrompts() {
		return prompts;
	}
	
	@Override
	public List<IRating> getAssociatedRatings() {
		return ratings;
	}
	
	@Override
	public void setAssociatedRatings(List<IRating> ratings) {
		this.ratings.clear();
		this.ratings = ratings; // TODO: Select ones with matching IDs.
	}
}
