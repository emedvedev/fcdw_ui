package uk.org.funcube.fcdw.web.security;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public final class BcryptPasswordGenerator implements PasswordGenerator {

	private static final int PASSWORD_LENGTH = 10;

	@Override
	public String generatePassword() {
		return RandomStringUtils.random(PASSWORD_LENGTH, true, true);
	}

	/**
	 * @See http://codahale.com/how-to-safely-store-a-password/
	 */
	@Override
	public String hashPassword(final String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());

	}

	@Override
	public boolean passwordMatches(final String hash, final String candidate) {
		return BCrypt.checkpw(candidate, hash);
	}
}
