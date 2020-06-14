package org.tt.indproj.core.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.tt.indproj.core.IUser;

/**
 * Handles the creation and tracking of user entities.
 * @author terratenff
 *
 */
public class UserManager {
	
	/**
	 * List of users that have seen active use during application run time.
	 */
	private static List<IUser> activeUsers = new ArrayList<IUser>();
	
	/**
	 * Getter for an active user entity. These are retrieved with unique
	 * identifiers that application users are given upon logging in.
	 * @param identifierKey The key part of the unique cookie.
	 * @param identifierValue The value part of the unique cookie.
	 * @return A logged user entity if proof of authentication is successfully
	 * detected. Otherwise a private user entity is given, representing a state
	 * of being logged out.
	 */
	public static IUser getUser(String identifierKey, String identifierValue) {
		// TODO: If identifier key and values match, return a logged user.
		// The logged user should be collected from the list.
		// TODO: If identifier matches fail, return a private user.
		return new PrivateUser();
	}
	
	/**
	 * Creates an existing logged user from the database.
	 * @param rs ResultSet from the database. <b>The connection should not be
	 * closed during this operation!</b>
	 * @return Existing logged user based on provided ResultSet from the
	 * database.
	 */
	public static IUser createUser(ResultSet rs) {
		IUser user = new LoggedUser(rs);
		if (!activeUsers.contains(user)) {
			activeUsers.add(user);
		}
		return user;
	}
	
	/**
	 * Creates a new logged user from given username and password.
	 * Note that its ID has to be assigned afterwards.
	 * @param username Name of the new logged user.
	 * @param password Password of the new logged user.
	 * @return Brand new logged user of the application. Its ID has to be
	 * set (via the DBBroker).
	 */
	public static IUser createUser(String username, String password) {
		IUser user = new LoggedUser(username, password);
		if (!activeUsers.contains(user)) {
			activeUsers.add(user);
		}
		return user;
	}
}
