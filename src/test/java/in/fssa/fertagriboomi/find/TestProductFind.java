package in.fssa.fertagriboomi.find;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.service.CategoryService;
import in.fssa.fertagriboomi.service.CategoryTypeService;
import in.fssa.fertagriboomi.service.ProductService;

public class TestProductFind {
	@Test
	public void testFindAllProductType() {
		ProductService productService = new ProductService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(productService.getAllProducts());
		});

	}

	@Test
	public void testFindProductWithValidId() {
		ProductService productService = new ProductService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(productService.findProductById(1));
		});

	}

	@Test
	public void testFindProductWithInValidNegativeId() {
		ProductService productService = new ProductService();

		Exception exception = assertThrows(Exception.class, () -> {
			productService.findProductById(-22);
		});

		String exceptedMessage = "Invalid Product id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testFindProductWithInValidNonExistentId() {
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.findProductById(150);
		});

		String expectedMessage = "The product is not listed among the available products";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

//	category 
	@Test
	public void testFindAllProductByCategoryId() {
		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			System.out.println(productService.listAllProductsByCategoryId(2));
		});
	}

	@Test
	public void testFindProductWithNegativeCategoryId() {
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.listAllProductsByCategoryId(-20);
		});

		String exceptedMessage = "Invalid Category id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testFindProductWithNonExistentCategoryId() {
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.listAllProductsByCategoryId(50);
		});

		String expectedMessage = "Invalid Category id";
		String actualMessage = exception.getMessage();

		System.out.println("Expected: " + expectedMessage);
		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	

}
