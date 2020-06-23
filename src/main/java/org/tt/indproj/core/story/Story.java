package org.tt.indproj.core.story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	 * Setter for story ID.
	 * @param id ID.
	 */
	void setStoryId(int id) {
		this.id = id;
	}
	
	/**
	 * Setter for the story author by ID.
	 * @param id Author ID.
	 */
	void setStoryAuthorId(int id) {
		this.creatorId = id;
	}
	
	/**
	 * Setter for the story author by name.
	 * @param author Author name.
	 */
	void setStoryAuthorName(String author) {
		this.creatorName = author;
	}
	
	/**
	 * Setter for the story filler by ID.
	 * @param id Story Filler ID.
	 */
	void setStoryFillerId(int id) {
		this.fillerId = id;
	}
	
	/**
	 * Setter for the story author by name.
	 * @param author Story Filler name.
	 */
	void setStoryFillerName(String author) {
		this.fillerName = author;
	}
	
	/**
	 * Setter for story title.
	 * @param title Title.
	 */
	void setStoryTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Setter for the story creation date.
	 * @param date Story creation date as a LocalDate-instance.
	 */
	void setStoryCreationDate(LocalDate date) {
		this.creationDate = date;
	}
	
	/**
	 * Setter for the story's current view count.
	 * @param count View Count.
	 */
	void setStoryViewCount(int count) {
		this.viewCount = count;
	}
	
	/**
	 * Setter for story contents.
	 * @param contents Story contents, default inputs.
	 */
	void setStoryContents(List<String> contents) {
		this.contents = contents;
	}
	
	/**
	 * Setter for story prompts.
	 * @param prompts Story prompts.
	 */
	void setStoryPrompts(List<IPrompt> prompts) {
		this.prompts = prompts;
	}
	
	/**
	 * Story constructor function. Used for creating new story instances. Note that
	 * once the story has been created, its ID has to be assigned afterwards.
	 * @param creator Story author by username.
	 * @param creatorId Story author by ID.
	 * @param title Story title.
	 * @param contents List of strings that make the story. Prompts within are
	 * default inputs.
	 * @param prompts List of prompts used in the story.
	 */
	Story(String creator, int creatorId, String title, List<String> contents, List<IPrompt> prompts) {
		setStoryAuthorName(creator);
		setStoryAuthorId(creatorId);
		setStoryTitle(title);
		setStoryViewCount(0);
		setStoryContents(contents);
		setStoryPrompts(prompts);
	}
	
	/**
	 * Story constructor function. Used for creating existing story instances
	 * straight from the application database.
	 * @param rs ResultSet from the database. <b>The connection to the database
	 * should not be closed during this procedure!</b>
	 */
	Story(ResultSet rs) throws SQLException {
		setStoryId(rs.getInt("id"));
		setStoryAuthorId(rs.getInt("templatemakerid"));
		setStoryAuthorName(rs.getString("templateauthor"));
		setStoryFillerId(rs.getInt("completemakerid"));
		setStoryFillerName(rs.getString("completeauthor"));
		setStoryTitle(rs.getString("title"));
		
		String dateString = rs.getString("creationdate");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM");
		LocalDate date = LocalDate.parse(dateString, format);
		
		setStoryCreationDate(date);
		setStoryViewCount(rs.getInt("viewcount"));
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
