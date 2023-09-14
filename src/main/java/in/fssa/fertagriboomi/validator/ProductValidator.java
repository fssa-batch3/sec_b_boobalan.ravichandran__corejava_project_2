package in.fssa.fertagriboomi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.util.StringUtil;

/**
 * Validator class for validating Product objects.
 */
public class ProductValidator {

	/**
	 * Validates a Product object for creation.
	 *
	 * @param product The Product object to validate.
	 * @throws ValidationException If the Product object is invalid or conflicts
	 *                             with existing data.
	 */
	public static void validateCreate(Product product) throws ValidationException {
		if (product == null) {
			throw new ValidationException("Invalid Product input");
		}

		if (product.getCategoryId() <= 0) {
			throw new ValidationException("Invalid Category Id");
		}

		StringUtil.rejectIfInvalidString(product.getName(), "Product name");
		StringUtil.rejectIfInvalidString(product.getProductWeight(), "Product quantity");
		StringUtil.rejectIfInvalidString(product.getApplication(), "Product Application");
		StringUtil.rejectIfInvalidString(product.getBenefits(), "Product Benefits");
		StringUtil.rejectIfInvalidString(product.getDescription(), "Product Description");
		StringUtil.rejectIfInvalidString(product.getManufacture(), "Product manufacture company");

		if (product.getImageURL() == null || "".equals(product.getImageURL().trim())) {
			throw new ValidationException("Product image cannot be empty");
		}

		String pattern = "^(?!0(g|kg|gms|ml|l)$)(\\d+(\\.\\d+)?\\s*(g|kg|gm|gms|ml|l))$";
		Pattern weightPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher weightMatcher = weightPattern.matcher(product.getProductWeight());
		if (!weightMatcher.matches()) {
			throw new ValidationException(
					"Please enter a valid product weight (e.g., 20 ml, 2.5kg) excluding 0g, 0kg, 0gms, 0ml, or 0l.");
		}

		int discountValue = product.getDiscount();
		int priceValue = (int) (0.92 * product.getPrice());
		if (discountValue < 0 || discountValue > priceValue) {

			throw new ValidationException("Discount should be between 0 and a maximum of 92% of the price.");
		}

		ProductDAO productDAO = new ProductDAO();
		try {
			boolean categoryExists = productDAO.isCategoryExists(product.getCategoryId());
			if (!categoryExists) {
				throw new ValidationException("Category does not exist");
			}
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

		try {
			productDAO = new ProductDAO();
			boolean productExists = productDAO.isProductAlreadyExists(product.getName());
			if (productExists) {
				throw new ValidationException("Product name is already exists");
			}
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * Validates a Product object for update.
	 *
	 * @param id        The ID of the Product to update.
	 * @param newUpdate The updated Product object.
	 * @throws ValidationException If the updated Product object is invalid or
	 *                             conflicts with existing data.
	 */
	public static void validateUpdate(int id, Product newUpdate) throws ValidationException {
		if (newUpdate == null) {
			throw new ValidationException("Invalid Product input");
		}

		if (id <= 0) {
			throw new ValidationException("Invalid Product id");
		}

		StringUtil.rejectIfInvalidString(newUpdate.getProductWeight(), "Product quantity");
		StringUtil.rejectIfInvalidString(newUpdate.getApplication(), "Product Application");
		StringUtil.rejectIfInvalidString(newUpdate.getBenefits(), "Product Benefits");
		StringUtil.rejectIfInvalidString(newUpdate.getDescription(), "Product Description");

		if (newUpdate.getImageURL() == null || "".equals(newUpdate.getImageURL().trim())) {
			throw new ValidationException("Product image cannot be empty");
		}

		String pattern = "^(?!0(g|kg|gms|ml|l)$)(\\d+(\\.\\d+)?\\s*(g|kg|gms|ml|l))$";
		Pattern weightPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher weightMatcher = weightPattern.matcher(newUpdate.getProductWeight());
		if (!weightMatcher.matches()) {
			throw new ValidationException(
					"Please enter a valid product weight (e.g., 20 ml, 2.5kg) excluding 0g, 0kg, 0gms, 0ml, or 0l.");
		}

		ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO();
			productDAO.findById(id);

		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * Validates a Product ID.
	 *
	 * @param newId The ID to validate.
	 * @throws ValidationException If the Product ID is invalid or not found.
	 */
	public static void validateId(int newId) throws ValidationException {

		if (newId <= 0) {
			throw new ValidationException("Invalid Product id");
		}
		ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO();
			productDAO.findById(newId);

		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * Validates a Product ID for deletion.
	 *
	 * @param newId The ID to validate.
	 * @throws ValidationException If the Product ID is invalid, not found, or has
	 *                             already been removed.
	 */
	public static void validateDeleteId(int newId) throws ValidationException {

		if (newId <= 0) {
			throw new ValidationException("Invalid Product id");
		}
		ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO();
			productDAO.findById(newId);

		} catch (DAOException e) {
			throw new ValidationException(e);
		}

		try {
			productDAO = new ProductDAO();
			boolean val = productDAO.isDeletedProduct(newId);
			if (val) {
				throw new ValidationException("This product has already been removed");
			}

		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * Validates a Category ID for listing products.
	 *
	 * @param id The Category ID to validate.
	 * @throws ValidationException If the Category ID is invalid or not found.
	 */
	public static void validateCategoryId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Invalid Category id");
		}

		ProductDAO productDAO = new ProductDAO();

		try {
			boolean categoryExists = productDAO.isCategoryExists(id);
			if (categoryExists) {
				productDAO.isCategoryProductsExits(id);
				productDAO.listAllTheProductsByCategoryId(id);
			} else {
				throw new ValidationException(
						"The products for the specified category are not found in the product list.");
			}
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}
	}
}
