package uk.org.funcube.fcdw;

import java.security.NoSuchAlgorithmException;

import uk.org.funcube.fcdw.web.security.ReasonablePasswordGenerator;

public class PasswordGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.out.println("Usage: PasswordGenerator password");
			return;
		}
		
		String passwordHash;
		try {
			ReasonablePasswordGenerator passwordGenerator = new ReasonablePasswordGenerator();
			passwordHash = passwordGenerator.hashPassword(args[0]);
		} catch (NoSuchAlgorithmException e)  {
			passwordHash = "xxxxxxxxxx";
		}
		
		System.out.println(passwordHash);
	

	}

}