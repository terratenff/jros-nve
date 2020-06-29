package org.tt.indproj.core;

import java.util.List;

/**
 * Interface for a prompt within a story.
 * @author terratenff
 *
 */
public interface IPrompt {

	/**
	 * Getter for the ID of the prompt.
	 * @return ID.
	 */
	public int getId();
	
	/**
	 * Gives the prompt instance an ID number. This function is meant to be called only once,
	 * immediately after being added to the database.
	 * @param id Assigned ID.
	 */
	public void assignId(int id);
	
	/**
	 * Getter for the positions in a story it is part of.
	 * @return List of integers, representing places in a list of strings that make
	 * the story.
	 */
	public List<Integer> getPositions();
	
	/**
	 * Getter for the description of the prompt that helps users provide
	 * a suitable input.
	 * @return Prompt description.
	 */
	public String getDescription();
	
	/**
	 * Getter for the default input of the prompt.
	 * @return Default input.
	 */
	public String getDefault();
	
	/**
	 * Getter for the input placed by the user.
	 * @return Target prompt. If no prompt is given by the user, an empty string
	 * is returned instead.
	 */
	public String getPrompt();
}
