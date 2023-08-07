package in.fssa.fertagriboomi.util;

import java.util.regex.*;

import in.fssa.fertagriboomi.exception.ValidationException;

public class StringUtil {
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {

		if (input == null || "".equals(input.trim())) {

			throw new ValidationException(inputName.concat(" cannot be Null or Empty"));

		}
		
		
	}
}
