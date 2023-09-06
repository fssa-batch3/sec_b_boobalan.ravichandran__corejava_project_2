package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.service.UserService;
import in.fssa.fertagriboomi.util.EmailGenerator;

public class TestCreateUser {

	@Test
	public void testCreateUserWithValidInput() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("Ragunath");
		newUser.setEmail(EmailGenerator.generateRandomEmail()); // Generate a random email
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(9676547782L);

		assertDoesNotThrow(() -> {
			userService.createUser(newUser);
		});
	}

	@Test
	public void testCreateUSerWithInvalidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(Exception.class, () -> {
			userService.createUser(null);
		});
		String exceptedMessage = "Invalid User input";
		String actualMessage = exception.getMessage();
		System.out.println("ex" + exceptedMessage);
		System.out.println("ac" + actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUserEmailWithNull() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR");
		newUser.setEmail(null);
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(967654665277l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUserEmailWithEmptyString() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR");
		newUser.setEmail("");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(967654665277l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUserEmailWithInvalidpattern() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR");
		newUser.setEmail("rboomibaln459gmailcom");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(9676546652l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "Invalid Email Address";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testCreateUserWithExistingEmail() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Boobalan");
			newUser.setEmail("rboomibaln459@gmail.com");
			newUser.setPassword("Xyz12345@");
			newUser.setPhoneNumber(9676546652l);

			userService.createUser(newUser);
		});
		String exceptedMessage = "The email already exists";
		String actualMessage = exception.getMessage();
		// System.out.println("ex" + exceptedMessage);
		// System.out.println("ac" + actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUserPasswordWithEmptyString() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("Boobalan");
		newUser.setEmail("rboomibaln4559@gmail.com");
		newUser.setPassword("");
		newUser.setPhoneNumber(967654665277l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUserPasswordWithNull() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("Boobalan");
		newUser.setEmail("rboomibaln4599@gmail.com");
		newUser.setPassword(null);
		newUser.setPhoneNumber(967654665277l);
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUserPasswordlWithInvalidPattern() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR");
		newUser.setEmail("rboomibaln459@gmailcom");
		newUser.setPassword("xyz1234577");
		newUser.setPhoneNumber(9676546652l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "Invalid Password. The password must be at least 8 characters long and contain at least one letter, one digit, and one special character. It should not contain spaces.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

//	Name
	@Test
	public void testUserNameWithEmptyString() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("");
		newUser.setEmail("rboomibaln459@gmail.com");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(967654665277l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUsertNameWithNull() {
		UserService userService = new UserService();
		User newUser = new User();

		newUser.setName(null);
		newUser.setEmail("rboomibaln459@gmail.com");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(967654665277l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUserNamelWithInvalidpattern() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR837");
		newUser.setEmail("rboomibaln459@gmailcom");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(9676546652l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "Invalid User Name. The name must only contain alphabetic characters";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUsePhoneNumberWithInvalidInput() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR");
		newUser.setEmail("rboomibaln461@gmailcom");
		newUser.setPhoneNumber(96);
		newUser.setPassword("Abcd1234@");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String exceptedMessage = "Invalid phone number. The phone number must be in the range of 6000000001 to 9999999999.";
		String actualMessage = exception.getMessage();

		System.out.println(exceptedMessage);
		System.out.println(actualMessage);

		assertTrue(actualMessage.contains(exceptedMessage));
	}
}