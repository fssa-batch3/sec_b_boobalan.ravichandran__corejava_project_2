package in.fssa.fertagriboomi.find;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.CategoryType;
import in.fssa.fertagriboomi.service.CategoryTypeService;

public class TestCategoryTypeFind {

	@Test
	public void testFindAllCategoryType() {
		CategoryTypeService userService = new CategoryTypeService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(userService.getAll());
		});

	}

	@Test
	public void testFindCategoryTypeWithValidId() {
		CategoryTypeService userService = new CategoryTypeService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(userService.findById(1));
		});

	}

	@Test
	public void testFindCategoryTypeWithInValidNegativeId() {
		CategoryTypeService userService = new CategoryTypeService();

		Exception exception = assertThrows(Exception.class, () -> {
			userService.findById(-22);
		});

		String exceptedMessage = "Invalid category type ID";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testFindCategoryTypeWithInValidNonExistentId() {
		CategoryTypeService userService = new CategoryTypeService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.findById(22);
		});

		String exceptedMessage = "Category type not available";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
