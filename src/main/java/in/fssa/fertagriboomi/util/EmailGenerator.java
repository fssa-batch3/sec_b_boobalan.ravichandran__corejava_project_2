package in.fssa.fertagriboomi.util;

import java.util.Random;

/**
 * Utility class for generating random email addresses.
 */
public class EmailGenerator {

	private static final String[] DOMAIN_SUFFIXES = { "gmail.com", "yahoo.com", "outlook.com", "example.com" };
	private static final int MAX_RANDOM_NUMBER = 999999;

	/**
	 * Generates a random email address.
	 *
	 * @return A randomly generated email address.
	 */
	public static String generateRandomEmail() {
		Random random = new Random();
		String username = "user" + random.nextInt(MAX_RANDOM_NUMBER);
		String domain = DOMAIN_SUFFIXES[random.nextInt(DOMAIN_SUFFIXES.length)];
		return username + "@" + domain;
	}
}
