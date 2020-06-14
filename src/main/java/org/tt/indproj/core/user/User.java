package org.tt.indproj.core.user;

import java.util.ArrayList;
import java.util.List;

import org.tt.indproj.core.IRating;
import org.tt.indproj.core.IStory;
import org.tt.indproj.core.IUser;

/**
 * Common class for both logged-in users and anonymous users.
 * @author terratenff
 *
 */
abstract class User implements IUser {
	
	/**
	 * User ID.
	 */
	private int id;
	
	/**
	 * Username.
	 */
	private String username;
	
	/**
	 * User Password.
	 */
	private String password;
	
	private List<IStory> stories = new ArrayList<IStory>();
	private List<IRating> ratings = new ArrayList<IRating>();
	
	/**
	 * Setter for user ID.
	 * @param var Positive integer or 0 for logged-in users, and -1
	 * for anonymous users.
	 */
	int setUserId(int var) {
		return id;
	}
	
	/**
	 * Setter for the username.
	 * @param var Name decided by the users as logged-in users.
	 * Defaults to "The Anonymous Collective" for anonymous users.
	 */
	String setName(String var) {
		return username;
	}
	
	/**
	 * Setter for the magic word.
	 * @param var Password.
	 */
	String setMagicWord(String var) {
		return password;
	}
	
	/**
	 * Getter for user ID.
	 * @return Positive integer or 0 for logged-in users, and -1
	 * for anonymous users.
	 */
	int getUserId() {
		return id;
	}
	
	/**
	 * Getter for the username.
	 * @return Name decided by the users as logged-in users.
	 * Defaults to "The Anonymous Collective" for anonymous users.
	 */
	String getName() {
		return username;
	}
	
	/**
	 * Getter for the magic word.
	 * @return Password.
	 */
	String getMagicWord() {
		return password;
	}
	
	@Override
	public List<IStory> getAssociatedStories() {
		return stories;
	}
	
	@Override
	public List<IRating> getAssociatedRatings() {
		return ratings;
	}
	
	@Override
	public void setAssociatedStories(List<IStory> stories) {
		this.stories.clear();
		this.stories = stories; // TODO: Select ones with matching IDs.
	}
	
	@Override
	public void setAssociatedRatings(List<IRating> ratings) {
		this.ratings.clear();
		this.ratings = ratings; // TODO: Select ones with matching IDs.
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof User) {
			final User objU = (User) obj;
			if (id == objU.id && username.equals(objU.username)) return true;
			else return false;
		} else return false;
	}
}
