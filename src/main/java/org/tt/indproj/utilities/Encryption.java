package org.tt.indproj.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Collection of encryption utilities.
 * @author hexfl
 *
 */
public class Encryption {
	
	/**
	 * Encrypts a message using SHA-256 and converts it into a Base64-string.
	 * @param subject Message subject to encryption
	 * @return Encrypted message.
	 */
	public static String sha256(String subject) {
		String hashedSubject = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(subject.getBytes("UTF-8"));
			hashedSubject = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return hashedSubject;
	}
	
	/**
	 * Generates a random salt string. Limited to 16 character size.
	 * @return Salt string of size 16.
	 */
	public static String generateSalt() {
		return generateSalt(16);
	}
	
	/**
	 * Generates a random salt string of variable length.
	 * @param size Requested length of the salt string.
	 * @return Salt string of requested length.
	 */
	public static String generateSalt(int size) {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[size];
		random.nextBytes(bytes);
		String salt = Base64.getEncoder().encodeToString(bytes).substring(0, size);
		return salt;
	}
}
