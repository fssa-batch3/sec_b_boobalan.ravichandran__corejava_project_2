package in.fssa.fertagriboomi.update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.service.UserService;

public class TestUpdateUser {

	@Test
	public void testUpdateUserWithValideInput() {
		UserService userService = new UserService();
		User newUser = new User();

		newUser.setName("Boobalan Ravichandran ");
		newUser.setPhoneNumber(8876546652l);
		newUser.setPassword("Abc12345@");

		assertDoesNotThrow(() -> {
			userService.updateUser(1, newUser);
		});
	}

	@Test
	public void testUpdateUSerWithInvalidUserId() {
		UserService userService = new UserService();
		User newUser = new User();

		newUser.setName("Boobalan Ravichandran");
		newUser.setPhoneNumber(8876546652l);
		newUser.setPassword("Abc12345@");
		Exception exception = assertThrows(Exception.class, () -> {
			userService.updateUser(0, newUser);
		});
		String exceptedMessage = "Invalid User id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUSerWithInvalidUserInput() {
		UserService userService = new UserService();

		Exception exception = assertThrows(Exception.class, () -> {
			userService.updateUser(1, null);
		});
		String exceptedMessage = "Invalid User input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

//	Name
	@Test
	public void testUpdateUserNameWithEmptyString() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(967654665277l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(1, newUser);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUsertNameWithNull() {
		UserService userService = new UserService();
		User newUser = new User();

		newUser.setName(null);
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(967654665277l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(1, newUser);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserNamelWithInvalidpattern() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR837");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(9676546652l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(1, newUser);
		});

		String expectedMessage = "Invalid User Name";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

//	password

	@Test
	public void testUserPasswordWithEmptyString() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("Boobalan");
		newUser.setPassword("");
		newUser.setPhoneNumber(967654665277l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(1, newUser);
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
		newUser.setPassword(null);
		newUser.setPhoneNumber(967654665277l);
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(1, newUser);
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
		newUser.setPassword("xyz1234577");
		newUser.setPhoneNumber(9676546652l);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(1, newUser);
		});

		String exceptedMessage = "Invalid Password. The password must be at least 8 characters long and contain at least one letter, one digit, and one special character. It should not contain spaces.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUsePhoneNumberWithInvalidInput() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(0);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(1, newUser);
		});

		String exceptedMessage = "Invalid phone number. The phone number must be in the range of 6000000001 to 9999999999.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
