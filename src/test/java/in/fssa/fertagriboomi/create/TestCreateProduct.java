package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.service.PriceService;
import in.fssa.fertagriboomi.service.ProductService;
import in.fssa.fertagriboomi.service.UserService;

public class TestCreateProduct {

	@Test
	public void testCreateProductWithValideInput() {
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

	@Test
	public void testCreateProductNameWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName(null);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductNameWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("");
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductNameWithExistingProductName() {
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product name is already exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWithInvalidCategoryId() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(-22);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Invalid Category Id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWithNonExistsCategoryId() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(22);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Category does not exist";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductManufactureCompanyWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture(null);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product manufacture company cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductManufactureCompanyWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("");
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product manufacture company cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWeightWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight(null);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product quantity cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWeightWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight("");
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product quantity cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductDescriptionWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight("500g");
		product.setDescription(null);
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductDescriptionWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight("500g");
		product.setDescription("");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductApplicationWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(null);
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product Application cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductApplicationWithEmtyString() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication("");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product Application cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductBenefitsWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(null);
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product Benefits cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductBenefitsWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProduct_weight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits("");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product Benefits cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductPriceBelow50Rupees() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dow agro science");
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
		price.setPrice(10);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Price should be between a minimum of 50 and a maximum of 10000.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void testCreateProductPriceUnder10000Rupees() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dow agro");
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
		price.setPrice(10020);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Price should be between a minimum of 50 and a maximum of 10000.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

}
