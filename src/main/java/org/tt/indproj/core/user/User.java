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
	
	/**
	 * User's personal salt value.
	 */
	private String salt;
	
	private List<IStory> stories = new ArrayList<IStory>();
	private List<IRating> ratings = new ArrayList<IRating>();
	
	/**
	 * Setter for user ID.
	 * @param var Positive integer for logged-in users, and 0
	 * for anonymous users. (-1 if an ID value has not been assigned for a logged-in user)
	 */
	void setUserId(int var) {
		id = var;
	}
	
	/**
	 * Setter for the username.
	 * @param var Name decided by the users as logged-in users.
	 * Defaults to "The Anonymous Collective" for anonymous users.
	 */
	void setName(String var) {
		username = var;
	}
	
	/**
	 * Setter for the magic word.
	 * @param var Password.
	 */
	void setMagicWord(String var) {
		password = var;
	}
	
	void setUserSalt(String var) {
		salt = var;
	}
	
	/**
	 * Getter for user ID.
	 * @return Positive integer for logged-in users, and 0
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
	
	String getUserSalt() {
		return salt;
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
