package org.tt.indproj.core.user;

/**
 * Implementation of a user that does not use an account to use the service.
 * @author terratenff
 *
 */
class PrivateUser extends User {
	
	private static final String USERNAME = "The Anonymous Collective";

	@Override
	public int getId() {
		return -1;
	}
	
	@Override
	public void assignId(int id) {
		
	}

	@Override
	public String getUsername() {
		return USERNAME;
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public String getIdentifierKey() {
		return "";
	}

	@Override
	public String getIdentifierValue() {
		return "";
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean userInstance = super.equals(obj);
		if (userInstance) {
			if (obj instanceof PrivateUser) {
				PrivateUser objU = (PrivateUser) obj;
				if (objU.getUsername().equals(USERNAME)) {
					return true;
				} else return false;
			} else return false;
		} else return false;
	}
}
