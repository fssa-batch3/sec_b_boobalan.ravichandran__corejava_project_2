package in.fssa.fertagriboomi.util;

import in.fssa.fertagriboomi.exception.ValidationException;

/**
 * Utility class for string-related operations and validations.
 */
public class StringUtil {

	/**
	 * Rejects a string if it is null or empty, throwing a ValidationException.
	 *
	 * @param input     The input string to validate.
	 * @param inputName The name of the input, used in the error message.
	 * @throws ValidationException If the input string is null or empty.
	 */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}
}
