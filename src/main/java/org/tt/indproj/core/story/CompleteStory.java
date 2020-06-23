package org.tt.indproj.core.story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.tt.indproj.core.IPrompt;

class CompleteStory extends Story {

	CompleteStory(String creator, int creatorId, String title, List<String> contents, List<IPrompt> prompts) {
		super(creator, creatorId, title, contents, prompts);
		// TODO
	}
	
	CompleteStory(ResultSet rs) throws SQLException {
		super(rs);
		// TODO
	}

}
