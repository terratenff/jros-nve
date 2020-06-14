package org.tt.indproj.core;

import java.util.List;

/**
 * Interface for user instances.
 * @author terratenff
 *
 */
public interface IUser {
	
	/**
	 * Getter for user ID.
	 * @return ID.
	 */
	public int getId();
	
	/**
	 * Gives the user instance an ID number. This function is meant to be called
	 * only once, immediately after being added to the database.
	 * @param id Assigned ID.
	 */
	public void assignId(int id);
	
	/**
	 * Getter for username.
	 * @return Username.
	 */
	public String getUsername();
	
	/**
	 * Getter for password.
	 * @return Password.
	 */
	public String getPassword();
	
	/**
	 * Getter for the key part of the user's identifier.
	 * @return Identifier Key.
	 */
	public String getIdentifierKey();
	
	/**
	 * Getter for the value part of the user's identifier.
	 * @return Identifier Value.
	 */
	public String getIdentifierValue();
	
	/**
	 * Getter for story instances that are associated with the user.
	 * Association is based on who created the story.
	 * @return List of stories that have a matching makerID with the user ID.
	 */
	public List<IStory> getAssociatedStories();
	
	/**
	 * Getter for rating instances that are associated with the user.
	 * Association is based on who created the rating.
	 * @return List of ratings that have a matching makerID with the user ID.
	 */
	public List<IRating> getAssociatedRatings();
	
	/**
	 * Setter for story instances that are associated with the user.
	 * Association is based on who created the story.
	 * @param stories List of either stories that have a matching makerID with
	 * the user ID, or every existing story. In the latter case, those stories
	 * with matching makerIDs are selected.
	 */
	public void setAssociatedStories(List<IStory> stories);
	
	/**
	 * Setter for rating instances that are associated with the user.
	 * Association is based on who created the rating.
	 * @param ratings List of either ratings that have a matching makerID with
	 * the user ID, or every existing rating. In the latter case, those ratings
	 * with matching makerIDs are selected.
	 */
	public void setAssociatedRatings(List<IRating> ratings);
}
