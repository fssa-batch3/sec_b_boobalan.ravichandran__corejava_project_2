package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.service.UserService;

public class TestCreateUser {

	@Test
	public void testCreateUserWithValideInput() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("Boobalan");
		newUser.setEmail("rboomibaln97@gmail.com");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(9676546652l);
		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			userService.create(newUser);
		});

	}

	@Test
	public void testCreateUSerWithInvalidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(null);
		});
		String exceptedMessage = "Invalid User input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.create(newUser);
		});

		String exceptedMessage = "email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.create(newUser);
		});

		String exceptedMessage = "email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.create(newUser);
		});

		String exceptedMessage = "Invalid Email Address";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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

			userService.create(newUser);
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
			userService.create(newUser);
		});

		String exceptedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.create(newUser);
		});

		String exceptedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.create(newUser);
		});

		String exceptedMessage = "Invalid Password";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.create(newUser);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.create(newUser);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.create(newUser);
		});

		String exceptedMessage = "Invalid User Name";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUsePhoneNumberWithInvalidInput() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR");
		newUser.setEmail("rboomibaln461@gmailcom");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(0);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});

		String exceptedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
}