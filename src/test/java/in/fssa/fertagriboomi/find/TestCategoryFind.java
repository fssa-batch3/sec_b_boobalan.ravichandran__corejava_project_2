package in.fssa.fertagriboomi.find;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.service.CategoryService;

public class TestCategoryFind {
	@Test
	public void getAllCategory() {
		CategoryService categoryService = new CategoryService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(categoryService.getAllCategories());
		});

	}

	@Test
	public void testFindCategoryTypeWithValidId() {
		CategoryService categoryService = new CategoryService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(categoryService.findCategoryById(2));
		});

	}

	@Test
	public void testFindCategoryWithInValidNegativeId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(Exception.class, () -> {
			categoryService.findCategoryById(-22);
		});

		String exceptedMessage = "Invalid category ID";
		String actualMessage = exception.getMessage();
//		System.out.println("e"+exceptedMessage);
//		System.out.println("e"+actualMessage);

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testFindCategoryWithInValidNonExistentId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.findCategoryById(99);
		});

		String exceptedMessage = "The Category is not listed among the available Categories";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

//	category type
	@Test
	public void testFindAllCategoryType() {
		CategoryService categoryService = new CategoryService();

		assertDoesNotThrow(() -> {
			System.out.println(categoryService.findCategoriesByCategoryTypeId(2));
		});
	}

	@Test
	public void testFindCategoryTypeWithInValidNegativeId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.findCategoriesByCategoryTypeId(-99);
		});

		String exceptedMessage = "Invalid category type ID";
		String actualMessage = exception.getMessage();
		System.out.println("e" + exceptedMessage);
		System.out.println("e" + actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testFindCategoryTypeWithInValidNonExistentId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.findCategoriesByCategoryTypeId(99);
		});

		String exceptedMessage = "This Category type is not listed among the available Category types";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

}
