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
		CategoryTypeService categoryTypeService = new CategoryTypeService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(categoryTypeService.getAll());
		});

	}

	@Test
	public void testFindCategoryTypeWithValidId() {
		CategoryTypeService categoryTypeService = new CategoryTypeService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(categoryTypeService.findById(1));
		});

	}

	@Test
	public void testFindCategoryTypeWithInValidNegativeId() {
		CategoryTypeService categoryTypeService = new CategoryTypeService();

		Exception exception = assertThrows(Exception.class, () -> {
			categoryTypeService.findById(-22);
		});

		String exceptedMessage = "Invalid category type ID";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testFindCategoryTypeWithInValidNonExistentId() {
		CategoryTypeService categoryTypeService = new CategoryTypeService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryTypeService.findById(22);
		});

		String exceptedMessage = "Category type not available";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
