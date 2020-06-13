package org.tt.indproj.core.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.tt.indproj.core.IUser;

public class UserManager {
	private static List<IUser> activeUsers = new ArrayList<IUser>();
	public static IUser getUser(String identifierKey, String identifierValue) {
		// TODO: If identifier key and values match, return a logged user.
		// The logged user should be collected from the list.
		// TODO: If identifier matches fail, return a private user.
		return new PrivateUser();
	}
	
	public static IUser createUser(ResultSet rs) {
		IUser user = new LoggedUser(rs);
		if (!activeUsers.contains(user)) {
			activeUsers.add(user);
		}
		return user;
	}
	
	public static IUser createUser(String username, String password) {
		IUser user = new LoggedUser(username, password);
		if (!activeUsers.contains(user)) {
			activeUsers.add(user);
		}
		return user;
	}
}
