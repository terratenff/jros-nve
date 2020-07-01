package org.tt.indproj.core.rating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tt.indproj.core.IRating;

/**
 * Handles the creation and tracking of rating entities.
 * @author terratenff
 *
 */
public class RatingManager {

	/**
	 * List of ratings that have seen active use during application run time.
	 */
	private static List<IRating> activeRatings = new ArrayList<IRating>();
	
	/**
	 * Getter for an active rating entity.
	 * @param id ID of the desired rating entity.
	 * @return Rating instance with matching ID, or null, if a rating with specified ID
	 * could not be found.
	 */
	public static IRating getRating(int id) {
		for (IRating rating: activeRatings) {
			int ratingId = rating.getId();
			if (ratingId == id) return rating;
		}
		return null;
	}
	
	/**
	 * Creates an existing rating from the database.
	 * @param rs ResultSet from the database. <b>The connection should not be
	 * closed during this operation!</b>
	 * @return Existing rating based on provided ResultSet from the database.
	 * @throws SQLException 
	 */
	public synchronized static IRating createRating(ResultSet rs) throws SQLException {
		IRating rating = new Rating(rs);
		if (activeRatings.contains(rating)) activeRatings.remove(rating);
		activeRatings.add(rating);
		return rating;
	}
}
