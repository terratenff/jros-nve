package org.tt.indproj.core.story.prompt;

import java.util.ArrayList;
import java.util.List;

import org.tt.indproj.core.IPrompt;

/**
 * Handles the creation of prompt instances.
 * @author terratenff
 *
 */
public class PromptManager {
	
	/**
	 * Creates an existing collection of prompts based on provided CSV data.
	 * @param csvList List of CSV rows. Each row should represent a singular prompt
	 * instance.
	 * @return List of prompt instances.
	 */
	public static List<IPrompt> getPrompts(String... csvList) {
		List<IPrompt> prompts = new ArrayList<IPrompt>();
		for (String csv : csvList) {
			prompts.add(new Prompt(csv));
		}
		return prompts;
	}
	
	/**
	 * Creates a new prompt instance from data provided by the user.
	 * @param designatedId Identifying value that has been designated for the
	 * prompt instance. <b>Prompt ID values are not heavily enforced by the application,
	 * so the user is encouraged to self-enforce it.</b>
	 * @param positions List of positions in the story where the prompt is expected
	 * to occur.
	 * @param description Description of the prompt that provides advice for the user
	 * on how to fill the prompt, both contextually and grammatically.
	 * @param defaultPrompt Default prompt that will be used if the user does not
	 * provide a prompt.
	 * @param prompt Prompt provided by the user.
	 * @return New, proper prompt instance, with an ID value assigned to it
	 * (not heavily enforced).
	 */
	public static IPrompt createPrompt(
			int designatedId,
			List<Integer> positions,
			String description,
			String defaultPrompt,
			String prompt) {
		IPrompt promptInstance = new Prompt(positions, description, defaultPrompt, prompt);
		promptInstance.assignId(designatedId);
		return promptInstance;
	}
}
