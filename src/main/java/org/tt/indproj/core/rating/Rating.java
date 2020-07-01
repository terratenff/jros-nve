package org.tt.indproj.core.rating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.tt.indproj.core.IRating;

/**
 * Instance of a user's thoughts on a specific story.
 * @author terratenff
 *
 */
class Rating implements IRating {
	
	/**
	 * Identifying value for the rating instance.
	 */
	private int id = -1;
	
	/**
	 * Identifying value for the user who created the story that the rating
	 * instance focuses on.
	 */
	private int makerId;
	
	/**
	 * Identifying value for the user who created the rating instance.
	 */
	private int raterId;
	
	/**
	 * Identifying value for the story that the rating instance focuses on.
	 */
	private int storyId;
	
	/**
	 * The date at which the rating was created (or the story was viewed).
	 */
	LocalDate viewDate;
	
	/**
	 * Integer representation of the grade given by the user. <b>Enforced range is
	 * 1-100.</b>.
	 */
	private int grade;
	
	/**
	 * Flag that determines whether the user personally liked the story.
	 */
	private boolean liked;
	
	/**
	 * Flag for the user to decided as to what kind of story they thought that was.
	 */
	private LikeType likeType;
	
	/**
	 * Flag that determines whether the user has flagged the story for review (in other
	 * words, the user suspects that the story violates rules/guidelines).
	 */
	private boolean flagged;
	
	/**
	 * User's thoughts on the story as words.
	 */
	private String comment;
	
	/**
	 * Rating constructor function. Used for creating new rating instances. Note that
	 * once the rating has been created, its ID has to be assigned afterwards.
	 * @param makerId ID of the creator of the subject story.
	 * @param raterId ID of the rater of the subject story.
	 * @param storyId ID of the subject story.
	 * @param viewDate Date at which the subject story was viewed (or rating was created).
	 * @param grade Numerical rating of the story given by the user for the story.
	 * @param liked Flag that determines whether the user personally liked the story.
	 * @param likeType Flag for what kind of story the user thought it was.
	 * @param flagged Flag that determines whether the user suspects that the
	 * subject story violates rules/guidelines.
	 * @param comment User's thoughts on the subject story as words.
	 */
	Rating(
			int makerId,
			int raterId,
			int storyId,
			LocalDate viewDate,
			int grade,
			boolean liked,
			LikeType likeType,
			boolean flagged,
			String comment) {
		this.makerId = makerId;
		this.raterId = raterId;
		this.storyId = storyId;
		this.viewDate = viewDate;
		this.grade = grade;
		this.liked = liked;
		this.likeType = likeType;
		this.flagged = flagged;
		this.comment = comment;
	}
	
	/**
	 * Rating constructor function. Used for creating existing rating instances
	 * straight from the application database.
	 * @param rs ResultSet from the database. <b>The connection to the database
	 * should not be closed during this procedure!</b>
	 * @throws SQLException
	 */
	Rating(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		makerId = rs.getInt("makerId");
		raterId = rs.getInt("raterid");
		storyId = rs.getInt("storyid");
		
		String dateString = rs.getString("viewdate");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM");
		LocalDate date = LocalDate.parse(dateString, format);
		
		viewDate = date;
		
		grade = rs.getInt("grade");
		liked = rs.getBoolean("liked");
		likeType = LikeType.getLikeType(rs.getInt("liketype"));
		flagged = rs.getBoolean("flagged");
		comment = rs.getString("comment");
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
	public int getMakerId() {
		return makerId;
	}

	@Override
	public int getRaterId() {
		return raterId;
	}

	@Override
	public int getStoryId() {
		return storyId;
	}

	@Override
	public LocalDate getViewDate() {
		return viewDate;
	}

	@Override
	public int getGrade() {
		return grade;
	}

	@Override
	public LikeType getLikeType() {
		return likeType;
	}

	@Override
	public boolean isFlagged() {
		return flagged;
	}

	@Override
	public String getComment() {
		return comment;
	}
}
