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
			System.out.println(categoryTypeService.getAllCategoryTypes());
		});

	}

	@Test
	public void testFindCategoryTypeWithValidId() {
		CategoryTypeService categoryTypeService = new CategoryTypeService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(categoryTypeService.findCategoryTypeById(1));
		});

	}

	@Test
	public void testFindCategoryTypeWithInValidNegativeId() {
		CategoryTypeService categoryTypeService = new CategoryTypeService();

		Exception exception = assertThrows(Exception.class, () -> {
			categoryTypeService.findCategoryTypeById(-22);
		});

		String exceptedMessage = "Invalid category type ID";
		String actualMessage = exception.getMessage();

		System.out.println("e" + exceptedMessage);
		System.out.println("e" + actualMessage);

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testFindCategoryTypeWithInValidNonExistentId() {
		CategoryTypeService categoryTypeService = new CategoryTypeService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryTypeService.findCategoryTypeById(22);
		});

		String exceptedMessage = "This Category type is not listed among the available Category types";
		String actualMessage = exception.getMessage();
//		System.out.println("ex"+ exceptedMessage);
//		System.out.println("ac"+ actualMessage);

		assertTrue(actualMessage.contains(exceptedMessage));
	}
}
