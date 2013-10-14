package uk.org.funcube.fcdw.web.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public final class ReasonablePasswordGenerator implements PasswordGenerator {

	private static final int PASSWORD_LENGTH = 10;

	@Override
	public String generatePassword() {
		return RandomStringUtils.random(PASSWORD_LENGTH, true, true);
	}

	/**
	 * @throws NoSuchAlgorithmException
	 */
	@Override
	public String hashPassword(final String password) throws NoSuchAlgorithmException {
		final MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(password.getBytes());
		final BigInteger hashValue = new BigInteger(1, md5.digest());
		return hashValue.toString(16);
	}

	@Override
	public boolean passwordMatches(final String hash, final String candidate) {
		try {
			return hash.equals(hashPassword(candidate));
		} catch (final Exception e) {
			return false;
		}
	}
}
