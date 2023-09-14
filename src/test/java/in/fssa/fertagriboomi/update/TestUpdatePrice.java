package in.fssa.fertagriboomi.update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.service.PriceService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUpdatePrice {

	@Test
	public void testUpdatePriceWithValidInput() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(generateRandomPriceInRange(50, 10000));
		int priceValue = price.getPrice();
		int discountValue = (int) (0.5 * priceValue);

		assertDoesNotThrow(() -> {
			priceService.updatePrice(2, priceValue, discountValue);
		});
	}

	private int generateRandomPriceInRange(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	@Test
	public void testUpdatePriceMinimumRequiredAmount() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(20);
		// newUser.setActive(true);
		int priceValue = price.getPrice();
		int discountValue = (int) (0.5 * priceValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(2, priceValue, discountValue);
		});

		String expectedMessage = "Price should be between a minimum of 50 and a maximum of 50000.";
		String actualMessage = exception.getMessage();
		System.out.println("Expected: " + expectedMessage);
		System.out.println("Actual: " + actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdatePriceMaximumRequiredAmount() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(50002);
		int priceValue = price.getPrice();
		// newUser.setActive(true);
		int discountValue = (int) (0.5 * priceValue);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(2, priceValue, discountValue);
		});

		String expectedMessage = "Price should be between a minimum of 50 and a maximum of 50000.";
		String actualMessage = exception.getMessage();
//		System.out.println("Expected: " + expectedMessage);
//		System.out.println("Actual: " + actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdatePriceWithInvalidNegativeOrZeroProductId() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(10002);
		int priceValue = price.getPrice();
		// newUser.setActive(true);
		int discountValue = (int) (0.5 * priceValue);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(-22, priceValue, discountValue);
		});

		String expectedMessage = "Invalid Product ID";
		String actualMessage = exception.getMessage();
		System.out.println("Expected: " + expectedMessage);
		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdatePriceWithInvalidNonExistenProductId() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(2900);
		int priceValue = price.getPrice();
		int discountValue = (int) (0.5 * priceValue);
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(220, priceValue, discountValue);
		});

		String expectedMessage = "Product not available in the product list";
		String actualMessage = exception.getMessage();
		System.out.println("Expected: " + expectedMessage);
		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdatePriceWithExistsPrice() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1000);

		int priceValue = price.getPrice();
		//int discountValue = (int) (0.5 * priceValue);
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(1, priceValue, 110);
		});

		String expectedMessage = "Product price and discount should be same";
		String actualMessage = exception.getMessage();
		System.out.println("Expected: " + expectedMessage);
		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));

	}
}
