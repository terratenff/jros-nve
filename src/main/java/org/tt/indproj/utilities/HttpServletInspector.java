package org.tt.indproj.utilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tt.indproj.core.IUser;
import org.tt.indproj.core.user.UserManager;

/**
 * Convenience class for dealing with HttpServlet-objects.
 * @author terratenff
 *
 */
public class HttpServletInspector {
	
	/**
	 * Attempts to find an active logged-in user by analysing existing cookies.
	 * @param request Http-request from which the cookies are collected.
	 * @return A user entity, either a logged-in user or a private user.
	 */
	public static synchronized IUser collectUser(final HttpServletRequest request) {
		// TODO: Notify user A if another user has logged in to user A's account.
    	// 1. User B attempts to log in to user A's account.
    	// 2. If user instance is active, collect its identifying values and
    	//    store them in a list of expired identifying values (UserManager).
    	// 3. Recreate the user instance and let user B perform authentication.
    	// 4. User A attempts to use now-expired identifying values.
    	// 5. Expired values are now removed from the list, and user A is given
    	//    the notice that says that they've been logged out and implies that
    	//    user A's account may be compromised.
		
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return UserManager.getUser("", "");
		}
		for (Cookie cookie : cookies) {
			String key = cookie.getName();
			String value = cookie.getValue();
			IUser user = UserManager.getUser(key, value);
			if (user.getId() != 0) return user;
		}
		return UserManager.getUser("", "");
	}
	
	/**
	 * Commands specified cookie for immediate expiration.
	 * @param response The Http-response to which the cookie must be set.
	 * @param cookieName Name of the cookie subject to immediate expiration.
	 */
	public static synchronized void expireCookie(
			final HttpServletResponse response,
			String cookieName) {
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
