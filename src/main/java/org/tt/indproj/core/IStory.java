package org.tt.indproj.core;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for story instances.
 * @author terratenff
 *
 */
public interface IStory {

	/**
	 * Getter for story ID.
	 * @return ID.
	 */
	public int getId();
	
	/**
	 * Gives the story instance an ID number. This function is meant to be called only once,
	 * immediately after being added to the database.
	 * @param id Assigned ID.
	 */
	public void assignId(int id);
	
	/**
	 * Getter for the ID of the story template creator.
	 * @return ID of the story template maker, or -1 if they are anonymous.
	 */
	public int getMakerId();
	
	/**
	 * Getter for the name of the story template creator.
	 * @return Name of the story template maker, or "The Anonymous Collective" if they are
	 * anonymous.
	 */
	public String getMakerName();
	
	/**
	 * Getter for the ID of the story prompt filler.
	 * @return ID of the story prompt filler, or -1 if they are anonymous. -2 is
	 * returned if the story prompts have not been filled.
	 */
	public int getFillerId();
	
	/**
	 * Getter for the name of the story prompt filler.
	 * @return Name of the story prompt filler, or "The Anonymous Collective" if they are
	 * anonymous. An empty string is returned if the story prompts have not been filled.
	 */
	public String getFillerName();
	
	/**
	 * Getter for the title of the story.
	 * @return Story title.
	 */
	public String getTitle();
	
	/**
	 * Getter for the time at which the story was created.
	 * @return Time of creation, as a LocalDate instance.
	 */
	public LocalDate getCreationDate();
	
	/**
	 * Getter for the number of views the story has accumulated so far.
	 * @return View count.
	 */
	public int getViewCount();
	
	/**
	 * Marks the story as viewed. (view count is incremented)
	 * @return View count after viewing it.
	 */
	public int view();
	
	/**
	 * Getter for the contents of the story, containing default prompts.
	 * @return The story.
	 */
	public List<String> getContents();
	
	/**
	 * Getter for the prompts of the story.
	 * @return Story prompts.
	 */
	public List<IPrompt> getPrompts();
	
	/**
	 * Getter for the ratings that are associated with the story.
	 * Association is based on being the focus of the rating.
	 * @return List of ratings that have a matching storyID with the story ID.
	 */
	public List<IRating> getAssociatedRatings();
	
	/**
	 * Setter for the ratings that are associated with the story.
	 * Association is based on being the focus of the rating.
	 * @param ratings List of either ratings that have a matching storyID with
	 * the story ID, or every existing rating. In the latter case, those ratings
	 * with matching storyIDs are selected.
	 */
	public void setAssociatedRatings(List<IRating> ratings);
}
