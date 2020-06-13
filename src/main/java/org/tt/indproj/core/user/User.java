package org.tt.indproj.core.user;

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
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof User) {
			final User objU = (User) obj;
			if (id == objU.id && username.equals(objU.username)) return true;
			else return false;
		} else return false;
	}
}
