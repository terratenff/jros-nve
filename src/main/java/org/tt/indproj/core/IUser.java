package org.tt.indproj.core;

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
}
