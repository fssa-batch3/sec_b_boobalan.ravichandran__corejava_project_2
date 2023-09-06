package in.fssa.fertagriboomi.login;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.service.UserService;

public class TestLoginUser {
	@Test
	public void testLoginUserWithValidInputs() {
		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			userService.findEmailAndPasswordExists("john@example.com", "Abc12345@");
		});
	}

	@Test
	public void testLoginWithInvalidEmailAddress() {
		UserService userService = new UserService();
		Exception exception = assertThrows(Exception.class, () -> {
			userService.findEmailAndPasswordExists("ranhuatuser16193@gmail.com", "Xyz12345@");
		});
		String exceptedMessage = "We cannot find an account with that email address";
		String actualMessage = exception.getMessage();
//		System.out.println(exceptedMessage);
//		System.out.println(actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testLoginWithInvalidPassword() {
		UserService userService = new UserService();
		Exception exception = assertThrows(Exception.class, () -> {
			userService.findEmailAndPasswordExists("rboomibaln459@gmail.com", "Xyz2345@");
		});
		String exceptedMessage = "Sorry we could not log you in. Your password is incorrect.";
		String actualMessage = exception.getMessage();
		System.out.println(exceptedMessage);
		System.out.println(actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));

	}

	@Test
	public void testLoginWithEmailAddressNull() {
		UserService userService = new UserService();
		Exception exception = assertThrows(Exception.class, () -> {
			userService.findEmailAndPasswordExists(null, "Xyz2345@");
		});
		String exceptedMessage = "email cannot be null or empty";
		String actualMessage = exception.getMessage();
//		System.out.println(exceptedMessage);
//		System.out.println(actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));

	}

	@Test
	public void testLoginWithEmailAddressEmptyString() {
		UserService userService = new UserService();
		Exception exception = assertThrows(Exception.class, () -> {
			userService.findEmailAndPasswordExists("", "Xyz2345@");
		});
		String exceptedMessage = "email cannot be null or empty";
		String actualMessage = exception.getMessage();
//		System.out.println(exceptedMessage);
//		System.out.println(actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));

	}

	@Test
	public void testLoginWithPasswordsNull() {
		UserService userService = new UserService();
		Exception exception = assertThrows(Exception.class, () -> {
			userService.findEmailAndPasswordExists("user161934@gmail.com", null);
		});
		String exceptedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(exceptedMessage);
		System.out.println(actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));

	}

	@Test
	public void testLoginWithPasswordsEmptyString() {
		UserService userService = new UserService();
		Exception exception = assertThrows(Exception.class, () -> {
			userService.findEmailAndPasswordExists("user161934@gmail.com", "");
		});
		String exceptedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(exceptedMessage);
		System.out.println(actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));

	}
}