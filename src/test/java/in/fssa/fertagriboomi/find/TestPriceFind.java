package in.fssa.fertagriboomi.find;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.service.CategoryService;
import in.fssa.fertagriboomi.service.PriceService;

public class TestPriceFind {
	@Test
	public void TestGetPriceWithValidProductId() {
		PriceService priceService = new PriceService();

		// newUser.setActive(true);
		assertDoesNotThrow(() -> {
			System.out.println(priceService.getPrice(1));
		});
	
}
	
	@Test
	public void TestGetPriceWithValidInvalidProductId() {
		PriceService priceService = new PriceService();

			Exception exception = assertThrows(Exception.class, () -> {
				priceService.getPrice(300);
			});

			String exceptedMessage = "Product not available in the product list";
			String actualMessage = exception.getMessage();
			//System.out.println("e"+exceptedMessage);
		//	System.out.println("e"+actualMessage);

			assertTrue(actualMessage.contains(exceptedMessage));
	
}

}