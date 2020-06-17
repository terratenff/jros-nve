package org.tt.indproj.core.user;

import java.sql.ResultSet;

import org.tt.indproj.utilities.Encryption;

/**
 * Implementation of a user that can log in to the service.
 * @author terratenff
 *
 */
class LoggedUser extends User {
	
	/**
	 * Flag that keeps track of whether the logged user has been
	 * properly assigned with an ID value.
	 */
	private boolean unassigned = true;
	
	/**
	 * Key part of the identifier unique to the logged user.<br>
	 * Creation:<br><br>
	 * 1. Generate a random, session-wide salt key upon logging in.<br>
	 * 2. Append username to the left of it.<br>
	 * 3. SHA-256 on the combination.
	 */
	private String identifierKey;
	
	/**
	 * Value part of the identifier unique to the logged user.<br>
	 * Creation:<br><br>
	 * 1. Generate a random, session-wide salt upon logging in.<br>
	 * 2. Append username to the left of it.<br>
	 * 3. SHA-256 on the combination.
	 */
	private String identifierValue;
	
	/**
	 * Constructor function for new instances of users.
	 * @param username (uniqueness is not checked here).
	 * @param password (unhashed).
	 */
	LoggedUser(String username, String password) {
		setUserSalt(Encryption.generateSalt());
		setName(username);
		setMagicWord(Encryption.sha256(password + getUserSalt()));
		setUserId(-2);
		
		identifierKey = Encryption.generateSalt();
		identifierValue = Encryption.generateSalt();
	}
	
	/**
	 * Constructor function for existing users.
	 * @param rs ResultSet straight from the database. <b>Connection to the
	 * database should not be closed during this process!</b>
	 */
	LoggedUser(ResultSet rs) {
		// TODO
		
		identifierKey = Encryption.generateSalt();
		identifierValue = Encryption.generateSalt();
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
	public String getSalt() {
		return getUserSalt();
	}

	@Override
	public String getIdentifierKey() {
		String key = getName() + identifierKey;
		return Encryption.sha256(key);
	}

	@Override
	public String getIdentifierValue() {
		String key = getName() + identifierValue;
		return Encryption.sha256(key);
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
