package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

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

	private String generateRandomProductName() {
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder dishName = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int index = (int) (Math.random() * alphabet.length());
			char randomChar = alphabet.charAt(index);
			dishName.append(Character.toUpperCase(randomChar));
		}
		return dishName.toString();
	}

	public String generateImageURL() {
		// Generate a unique image URL using UUID
		String imageName = UUID.randomUUID().toString() + ".jpg";
		String imageUrl = "https://example.com/images/" + imageName;
		return imageUrl;
	}

	@Test
	public void testCreateProductWithValideInput() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName(generateRandomProductName());
		product.setCategoryId(1);
		product.setManufacture("Dow Agro Science Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());
		//PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(50);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		//System.out.println(discountValue);
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		assertDoesNotThrow(() -> {
			productService.createProduct(product);

		});
	}

	@Test
	public void testCreateProductWithInvalidInput() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(Exception.class, () -> {
			productService.createProduct(null);
		});
		String expectedMessage = "Invalid Product input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductNameWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName(null);
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String expectedMessage = "Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductNameWithExistingProductName() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Superb");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(-22);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Invalid Category Id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWithNonExistenCategoryId() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategoryId(22);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture(null);
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture("");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight(null);
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Product quantity cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWeightWithInvalidPattern() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("200");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Please enter a valid product weight (e.g., 20 ml, 2.5kg) excluding 0g, 0kg, 0gms, 0ml, or 0l.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductDescriptionWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(null);
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription("");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String expectedMessage = "Product Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductApplicationWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(null);
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication("");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(null);
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
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
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits("");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Product Benefits cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductPriceBelow50Rupees() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dow agro ");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(10);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Price should be between a minimum of 50 and a maximum of 50000.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testCreateProductPriceUnder50000Rupees() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dow agro088979");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(53600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Price should be between a minimum of 50 and a maximum of 50000.";
		String actualMessage = exception.getMessage();

		System.out.println("exceptedMessage " + exceptedMessage);
		System.out.println("actualMessage " + actualMessage);

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductDiscountBelowZero() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dow agro");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(-23);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Discount should be between 0 and a maximum of 92% of the price.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductDiscountMoreThan90PercentageOfProductPrice() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dow agrodcd");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(generateImageURL());

		// PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Discount should be between 0 and a maximum of 92% of the price.";
		String actualMessage = exception.getMessage();
		System.out.println("exceptedMessage " + exceptedMessage+ discountValue);
		System.out.println("actualMessage " + actualMessage);

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductImgeUrlWithEmpty() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dow agro");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL("");

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Product image cannot be empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductImgeUrlWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dow agro");
		product.setCategoryId(1);
		product.setManufacture("Dhanuka Agritech Limited");
		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setActive(true);
		product.setImageURL(null);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1600);
		price.setDiscount(100);
		int priceValue = price.getPrice();
		int discountValue = price.getDiscount();
		product.setPrice(priceValue);
		product.setDiscount(discountValue);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});

		String exceptedMessage = "Product image cannot be empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

}
