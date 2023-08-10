package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.service.PriceService;
import in.fssa.fertagriboomi.service.ProductService;

public class TestCreateProduct {

	@Test
	public void testCreateUserWithValideInput() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		assertDoesNotThrow(() -> {
			productService.create(product);

		});
	}

	@Test
	public void testCreateProductWithInvalidInput() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(Exception.class, () -> {
			productService.create(null);
		});
		String exceptedMessage = "Invalid Product";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

}
