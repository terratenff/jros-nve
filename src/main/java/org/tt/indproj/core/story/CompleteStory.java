package org.tt.indproj.core.story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.tt.indproj.core.IPrompt;

class CompleteStory extends Story {

	CompleteStory(
			String templateCreator,
			int templateCreatorId,
			String completeCreator,
			int completeCreatorId,
			String title,
			List<String> contents,
			List<IPrompt> prompts) {
		super(templateCreator, templateCreatorId, title, contents, prompts);
		setStoryFillerName(completeCreator);
		setStoryFillerId(completeCreatorId);
	}
	
	CompleteStory(ResultSet rs) throws SQLException {
		super(rs);
	}

}
