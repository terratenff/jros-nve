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
	 * Attempts to find an active logged-in user by analyzing existing cookies.
	 * @param request Http-request from which the cookies are collected.
	 * @return A user entity, either a logged-in user or a private user.
	 */
	public static synchronized IUser collectUser(final HttpServletRequest request) {
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
