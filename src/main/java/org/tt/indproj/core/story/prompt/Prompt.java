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
	private List<Integer> positions;
	
	/**
	 * Description of the prompt: assists the user both contextually and grammatically.
	 */
	private String description;
	
	/**
	 * Default prompt set for the story if the user does not set it.
	 */
	private String defaultPrompt;
	
	/**
	 * Prompt set by the user.
	 */
	private String prompt;
	
	/**
	 * Constructor function for new prompts. Note that an ID has to be assigned
	 * for the prompt afterwards.
	 * @param positions List of integers that describe list indexes of the locations
	 * of the story (list of strings).
	 * @param description Description set to help the user provide a prompt.
	 * @param defaultPrompt A prompt that is used by default if the user does not
	 * provide one.
	 * @param prompt A prompt provided by the user. An empty string is treated
	 * as if the user did not provide one.
	 */
	Prompt(List<Integer> positions, String description, String defaultPrompt, String prompt) {
		this.positions = positions;
		this.description = description;
		this.defaultPrompt = defaultPrompt;
		this.prompt = prompt;
	}
	
	/**
	 * Constructor function for existing prompts.
	 * @param csv CSV Data from the database. <b>Expected format:</b><br><br>
	 * promptId;wordIndexes;promptDescription;promptDefault;promptFilled<br><br>
	 * Multiple word indexes are separated by commas.
	 */
	Prompt(String csv) {
		String[] components = csv.split(";");
		String csvId = components[0];
		String csvIndexes = components[1];
		String csvDescription = components[2];
		String csvDefault = components[3];
		if (components.length < 5) prompt = "";
		else prompt = components[4];
		
		id = Integer.parseInt(csvId);
		String[] wordIndexes = csvIndexes.split(",");
		positions = new ArrayList<Integer>();
		for (String index : wordIndexes) {
			positions.add(Integer.parseInt(index));
		}
		description = csvDescription;
		defaultPrompt = csvDefault;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void assignId(int id) {
		if (this.id == -1) this.id = id;
	}

	@Override
	public List<Integer> getPositions() {
		return positions;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getDefault() {
		return defaultPrompt;
	}

	@Override
	public String getPrompt() {
		return prompt;
	}

	@Override
	public String toString() {
		String str = "";
		str += id + ";";
		for (int i = 0; i < positions.size(); i++) {
			int index = positions.get(i);
			if (i == positions.size() - 1) str += index + ";";
			else str += index + ",";
		}
		str += description + ";";
		str += defaultPrompt + ";";
		str += prompt;
		// Result: promptId;wordIndexes;promptDescription;promptDefault;promptFilled
		// Multiple word indexes are separated with commas (",").
		return str;
	}
}
