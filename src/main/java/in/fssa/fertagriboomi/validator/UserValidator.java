package in.fssa.fertagriboomi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.fertagriboomi.dao.UserDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.util.StringUtil;

/**
 * Validator class for validating User objects.
 */
public class UserValidator {

	/**
	 * Validates a User for creation.
	 *
	 * @param user The User object to validate.
	 * @throws ValidationException If the User object is invalid.
	 * @throws DAOException        If there's an issue with data access.
	 */
	public static void validate(User user) throws ValidationException, DAOException {

		if (user == null) {
			throw new ValidationException("Invalid User input");
		}
		StringUtil.rejectIfInvalidString(user.getEmail(), "email");
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getName(), "Name");

		if (!(user.getPhoneNumber() >= 6000000001l && user.getPhoneNumber() <= 9999999999l)) {

			throw new ValidationException(
					"Invalid phone number. The phone number must be in the range of 6000000001 to 9999999999.");
		}
		Pattern namePattern = Pattern.compile("^[A-Za-z ]+$");
		Matcher nameMatcher = namePattern.matcher(user.getName());
		if (!nameMatcher.matches()) {
			throw new ValidationException(
					"Invalid User Name. The name must only contain alphabetic characters and spaces");
		}

		Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
		Matcher emailMatcher = emailPattern.matcher(user.getEmail());
		if (!emailMatcher.matches()) {
			throw new ValidationException("Invalid Email Address");
		}

		Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=_!])(?!.*\\s).{8,}$");
		Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());
		if (!passwordMatcher.matches()) {
			throw new ValidationException(
					"Invalid Password. The password must be at least 8 characters long and contain at least one letter, one digit, and one special character. It should not contain spaces.");
		}

		UserDAO userDAO = null;
		try {
			userDAO = new UserDAO();
			boolean isEmailExists = userDAO.isEmailAlreadyExists(user.getEmail());
			if (isEmailExists) {
				throw new ValidationException("The email already exists");
			}
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * Validates updates to a User.
	 *
	 * @param newUpdate The updated User object.
	 * @param id        The ID of the User being updated.
	 * @throws ValidationException If the updated User object is invalid.
	 */
	public static void validateUpdate(User newUpdate, int id) throws ValidationException {
		if (newUpdate == null) {
			throw new ValidationException("Invalid User input");
		}
		if (id <= 0) {
			throw new ValidationException("Invalid User id");
		}
		StringUtil.rejectIfInvalidString(newUpdate.getPassword(), "password");
		StringUtil.rejectIfInvalidString(newUpdate.getName(), "Name");

		if (!(newUpdate.getPhoneNumber() >= 6000000001l && newUpdate.getPhoneNumber() <= 9999999999l)) {

			throw new ValidationException(
					"Invalid phone number. The phone number must be in the range of 6000000001 to 9999999999.");
		}

		Pattern namePattern = Pattern.compile("^[A-Za-z ]+$");
		Matcher nameMatcher = namePattern.matcher(newUpdate.getName());
		if (!nameMatcher.matches()) {
			throw new ValidationException("Invalid User Name");
		}

		Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=_!])(?!.*\\s).{8,}$");
		Matcher passwordMatcher = passwordPattern.matcher(newUpdate.getPassword());
		if (!passwordMatcher.matches()) {
			throw new ValidationException(
					"Invalid Password. The password must be at least 8 characters long and contain at least one letter, one digit, and one special character. It should not contain spaces.");
		}

		UserDAO userDAO = null;
		try {
			userDAO = new UserDAO();
			userDAO.isValidUserId(id);
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

	}

	public static boolean ValidateEmailAndPassword(String userEmail, String password) throws ValidationException {

		StringUtil.rejectIfInvalidString(userEmail, "email");
		StringUtil.rejectIfInvalidString(password, "password");

		UserDAO userDAO = new UserDAO();
		boolean emailExists = false;
		try {
			emailExists = userDAO.findByEmail(userEmail);
			if (emailExists) {
				boolean isRegister = userDAO.findUserRegisterOrNot(userEmail, password);
				if (!isRegister) {
					throw new ValidationException("Sorry we could not log you in. Your password is incorrect.");
				}
			} else {
				throw new ValidationException("We cannot find an account with that email address");
			}
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}
		return emailExists;
	}

	public static void ValidateEmailAddress(String email) throws ValidationException {

		if (email == null || "".equals(email.trim())) {
			throw new ValidationException("Email cannot be null or empty");
		}
		UserDAO userDAO = new UserDAO();
		boolean emailExists = false;
		try {
			emailExists = userDAO.findByEmail(email);
			if (!emailExists) {
				throw new ValidationException("We cannot find an account with that email address");
			}
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

	}

}