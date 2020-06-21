package org.tt.indproj.core;

import java.time.LocalDate;

/**
 * Interface for rating instances.
 * @author terratenff
 *
 */
public interface IRating {
	
	/**
	 * Enums for various types of likes
	 * @author terratenff
	 *
	 */
	public static enum LikeType {
		FUNNY(0), INFORMATIVE(1), DETAILED(2), IRONIC(3), INSPIRATIONAL(4),
		EASY(5), CHALLENGING(6), ABSURD(7), POETIC(8), SIMPLE(9),
		NONE(-1);
		
		/**
		 * Container for various types of likes.
		 */
		private static LikeType[] values = null;
		
		/**
		 * Identifying value for a like type.
		 */
		private int id;
		
		/**
		 * LikeType constructor.
		 * @param id Assigned ID.
		 */
		LikeType(int id) {
			this.id = id;
		}
		
		/**
		 * Getter for the like type's ID.
		 * @return ID value that corresponds the like type.
		 */
		public int getId() {
			return this.id;
		}
		
		/**
		 * Getter for a like type based on provided ID.
		 * @param id ID of desired like type.
		 * @return Like type corresponding to provided ID, or like type
		 * NONE if a match could not be found.
		 */
		public static LikeType getLikeType(int id) {
			if (values == null) values = LikeType.values();
			
			for (LikeType like : values) {
				if (id == like.getId()) return like;
			}
			
			return LikeType.NONE;
		}
	};

	/**
	 * Getter for rating ID.
	 * @return ID.
	 */
	public int getId();
	
	/**
	 * Gives the rating instance an ID number. This function is meant to be called only once,
	 * immediately after being added to the database.
	 * @param id Assigned ID.
	 */
	public void assignId(int id);
	
	/**
	 * Getter for the ID of the user who created the story that the rating
	 * is aimed at.
	 * @return ID of the creator of the story.
	 */
	public int getMakerId();
	
	/**
	 * Getter for the ID of the user who created the rating.
	 * @return ID of the rater.
	 */
	public int getRaterId();
	
	/**
	 * Getter for the ID of the story that the rating is focused on.
	 * @return ID of the story.
	 */
	public int getStoryId();
	
	/**
	 * Getter for the time at which the rating was created.
	 * @return Time of creation, as a LocalDate instance.
	 */
	public LocalDate getViewDate();
	
	/**
	 * Getter for the numerical rating given by the rater.
	 * @return Numerical representation of the rating.
	 */
	public int getGrade();
	
	/**
	 * Getter for the like type assigned by the rater.
	 * @return LikeType-enum given by the rater. NONE is returned if
	 * the rater did not specifically like it.
	 */
	public LikeType getLikeType();
	
	/**
	 * Getter for the flag possibly raised by the rater. The flag indicates
	 * that the rater thinks that the story violates story rules/guidelines.
	 * @return true, if the rater has flagged it. false otherwise.
	 */
	public boolean isFlagged();
	
	/**
	 * Getter for a comment left behind by the rater.
	 * @return Comment string. An empty string is returned if no comment was made.
	 */
	public String getComment();
}
