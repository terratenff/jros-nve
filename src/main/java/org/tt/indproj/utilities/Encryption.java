package org.tt.indproj.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Encryption {
	public static String sha256(String subject) {
		String hashedSubject = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(subject.getBytes("UTF-8"));
			hashedSubject = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashedSubject;
	}
	
	public static String generateSalt() {
		return generateSalt(16);
	}
	
	public static String generateSalt(int size) {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[size];
		random.nextBytes(bytes);
		String salt = Base64.getEncoder().encodeToString(bytes).substring(0, size);
		return salt;
	}
}
