package uk.org.funcube.fcdw.web.security;

import java.security.NoSuchAlgorithmException;

public interface PasswordGenerator {
	String generatePassword();

	String hashPassword(String password) throws NoSuchAlgorithmException;

	boolean passwordMatches(String hash, String candidate);
}
