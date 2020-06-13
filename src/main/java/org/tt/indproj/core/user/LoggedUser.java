package org.tt.indproj.core.user;

import java.sql.ResultSet;

/**
 * Implementation of a user that can log in to the service.
 * @author terratenff
 *
 */
class LoggedUser extends User {
	
	private final String testsalt = "testsalt";
	private boolean unassigned = true;
	private String identifierKey;
	private String identifierValue;
	
	/**
	 * Constructor function for new instances of users.
	 * @param username
	 * @param password
	 */
	LoggedUser(String username, String password) {
		setName(username);
		setMagicWord(password);
		setUserId(-2);
		// TODO: identifierKey
		// TODO: identifierValue
	}
	
	/**
	 * Constructor function for existing users.
	 * @param rs ResultSet straight from the database. <b>Connection to the
	 * database should not be closed during this process!</b>
	 */
	LoggedUser(ResultSet rs) {
		// TODO
		
		// TODO: identifierKey
		// TODO: identifierValue
	}

	@Override
	public int getId() {
		return getUserId();
	}
	
	@Override
	public void assignId(int id) {
		if (unassigned) {
			setUserId(id);
			unassigned = false;
		}
	}

	@Override
	public String getUsername() {
		return getName();
	}

	@Override
	public String getPassword() {
		return getMagicWord();
	}

	@Override
	public String getIdentifierKey() {
		
		return "";
	}

	@Override
	public String getIdentifierValue() {
		// TODO
		return "";
	}

	@Override
	public boolean equals(Object obj) {
		boolean userInstance = super.equals(obj);
		if (userInstance) {
			if (obj instanceof LoggedUser) {
				final LoggedUser objU = (LoggedUser) obj;
				if (getUserId() == objU.getUserId() && getName().equals(objU.getName())) {
					return true;
				} else return false;
			} else return false;
		} else return false;
	}
}
