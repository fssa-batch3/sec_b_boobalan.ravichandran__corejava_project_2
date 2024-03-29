package in.fssa.fertagriboomi.update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.service.PriceService;
import in.fssa.fertagriboomi.service.ProductService;
import in.fssa.fertagriboomi.service.UserService;

public class TestUpdateProduct {
	public String generateImageURL() {
		// Generate a unique image URL using UUID
		String imageName = UUID.randomUUID().toString() + ".jpg";
		String imageUrl = "https://example.com/images/" + imageName;
		return imageUrl;
	}

	@Test
	public void testUpdateProductWithValideInput() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("5kg");
		product.setDescription(
				" Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"It leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());
		assertDoesNotThrow(() -> {
			productService.updateProduct(1, product);

		});
	}

	@Test
	public void testUpdateProductWithInvalidProductId() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("5kg");
		product.setDescription(
				" Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"It leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(Exception.class, () -> {
			productService.updateProduct(0, product);
		});
		String exceptedMessage = "Invalid Product id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductWithInvalidExistenProductId() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("1kg");
		product.setDescription(
				" Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"It leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(Exception.class, () -> {
			productService.updateProduct(28, product);
		});

		String exceptedMessage = "The product is not listed among the available products";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testUpdateProductWeightWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setProductWeight(null);
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);

		});

		String exceptedMessage = "Product quantity cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductWeightWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);

		});

		String exceptedMessage = "Product quantity cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductWeightWithInvalidPattern() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("67ll");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);

		});

		String exceptedMessage = "Please enter a valid product weight (e.g., 20 ml, 2.5kg) excluding 0g, 0kg, 0gms, 0ml, or 0l.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductDescriptionWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("500g");
		product.setDescription(null);
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String exceptedMessage = "Product Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductDescriptionWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("500g");
		product.setDescription("");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String exceptedMessage = "Product Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductApplicationWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(null);
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);

		});

		String exceptedMessage = "Product Application cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductApplicationWithEmtyString() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication("");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);

		});

		String exceptedMessage = "Product Application cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductBenefitsWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(null);
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String exceptedMessage = "Product Benefits cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductBenefitsWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits("");
		product.setImageURL(generateImageURL());

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);

		});

		String exceptedMessage = "Product Benefits cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductImageURLWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);

		});

		String exceptedMessage = "Product image cannot be empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductImageURLWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();

		product.setProductWeight("500g");
		product.setDescription(
				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
		product.setApplication(
				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
		product.setBenefits(
				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
		product.setImageURL("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);

		});

		String exceptedMessage = "Product image cannot be empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

}
