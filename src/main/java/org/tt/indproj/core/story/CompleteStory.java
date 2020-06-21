package org.tt.indproj.core.story;

import java.sql.ResultSet;
import java.util.List;

import org.tt.indproj.core.IPrompt;

class CompleteStory extends Story {

	CompleteStory(String creator, String title, List<String> contents, List<IPrompt> prompts) {
		super(creator, title, contents, prompts);
		// TODO
	}
	
	CompleteStory(ResultSet rs) {
		super(rs);
		// TODO
	}

}
