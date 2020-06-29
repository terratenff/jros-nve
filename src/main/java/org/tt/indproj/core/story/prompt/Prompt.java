package org.tt.indproj.core.story.prompt;

import java.util.ArrayList;
import java.util.List;

import org.tt.indproj.core.IPrompt;

/**
 * A prompt is a segment within a story that is left for the user to fill.
 * @author terratenff
 *
 */
class Prompt implements IPrompt {
	
	/**
	 * Identifying value for the prompt.
	 */
	private int id = -1;
	
	/**
	 * List of positions in the story that the prompt is present in.
	 */
	private List<Integer> positions = new ArrayList<Integer>();
	
	/**
	 * Description of the prompt: assists the user both contextually and grammatically.
	 */
	private String description = "";
	
	/**
	 * Default prompt set for the story if the user does not set it.
	 */
	private String defaultPrompt = "";
	
	/**
	 * Prompt set by the user.
	 */
	private String prompt = "";
	
	/**
	 * Constructor function for new prompts. Note that an ID has to be assigned
	 * for the prompt afterwards.
	 * @param positions List of integers that describe list indexes of the locations
	 * of the story (list of strings).
	 * @param description Description set to help the user provide a prompt.
	 * @param defaultPrompt A prompt that is used by default if the user does not
	 * provide one.
	 */
	Prompt(List<Integer> positions, String description, String defaultPrompt) {
		// TODO
	}
	
	/**
	 * Constructor function for existing prompts.
	 * @param csv CSV Data from the database. <b>Expected format:</b><br><br>
	 * promptId;wordIndexes;promptDescription;promptDefault;promptFilled<br><br>
	 * Multiple word indexes are separated by commas.
	 */
	Prompt(String csv) {
		// TODO
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void assignId(int id) {
		if (this.id == -1) this.id = id;
	}

	@Override
	public List<Integer> getPositions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrompt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO: promptId;wordIndexes;promptDescription;promptDefault;promptFilled
		// Multiple word indexes are separated with commas (",").
		return "";
	}
}
