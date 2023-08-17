package in.fssa.fertagriboomi.find;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.service.CategoryService;

public class TestCategoryFind {
	@Test
	public void testFindAllCategory() {
		CategoryService categoryService = new CategoryService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(categoryService.getAll());
		});

	}

	@Test
	public void testFindCategoryTypeWithValidId() {
		CategoryService categoryService = new CategoryService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(categoryService.findById(6));
		});

	}

	@Test
	public void testFindCategoryWithInValidNegativeId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(Exception.class, () -> {
			categoryService.findById(-22);
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
			categoryService.findById(99);
		});

		String exceptedMessage = "Category not available";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

//	category type
	@Test
	public void testFindAllCategoryType() {
		CategoryService categoryService = new CategoryService();

		assertDoesNotThrow(() -> {
			System.out.println(categoryService.findCategoriesByCategoryId(2));
		});
	}

	@Test
	public void testFindCategoryTypeWithInValidNegativeId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.findCategoriesByCategoryId(-99);
		});

		String exceptedMessage = "Invalid category type Id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testFindCategoryTypeWithInValidNonExistentId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.findCategoriesByCategoryId(99);
		});

		String exceptedMessage = "Category type not available";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

}
