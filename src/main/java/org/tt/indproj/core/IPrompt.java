package org.tt.indproj.core;

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
	 * Getter for the position in a story it is part of.
	 * @return Integer, representing a place in a list.
	 */
	public int getPosition();
	
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
