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
			userService.update(1, newUser);
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
			userService.update(0, newUser);
		});
		String exceptedMessage = "Invalid User id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUSerWithInvalidUserInput() {
		UserService userService = new UserService();

		Exception exception = assertThrows(Exception.class, () -> {
			userService.update(1, null);
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
			userService.update(1, newUser);
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
			userService.update(1, newUser);
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
			userService.update(1, newUser);
		});

		String exceptedMessage = "Invalid User Name";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
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
			userService.update(1, newUser);
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
			userService.update(1, newUser);
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
			userService.update(1, newUser);
		});

		String exceptedMessage = "Invalid Password";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUsePhoneNumberWithInvalidInput() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("BoobalanR");
		newUser.setPassword("Xyz12345@");
		newUser.setPhoneNumber(0);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(1, newUser);
		});

		String exceptedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
