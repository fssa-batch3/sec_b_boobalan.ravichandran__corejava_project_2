package in.fssa.fertagriboomi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.util.StringUtil;

public class UserValidator {

	public static void validate(User user) throws ValidationException {

		if (user == null) {
			throw new ValidationException("Invalid User input");
		}
		StringUtil.rejectIfInvalidString(user.getEmail(), "email");
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getName(), "Name");

		if (user.getPhoneNumber() >= 600000001 && user.getPhoneNumber() <= 9999999999l) {

			throw new ValidationException("Invalid phone number");
		}

		Pattern namePattern = Pattern.compile("^[A-Za-z]+$");
		Matcher nameMatcher = namePattern.matcher(user.getName());
		if (!nameMatcher.matches()) {
			throw new ValidationException("Invalid User Name");
		}

		Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
		Matcher emailMatcher = emailPattern.matcher(user.getEmail());
		if (!emailMatcher.matches()) {
			throw new ValidationException("Invalid Email Address");
		}

		Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=_!])(?!.*\\s).{8,}$");
		Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());
		if (!passwordMatcher.matches()) {
			throw new ValidationException("Invalid Password");
		}

	}
}