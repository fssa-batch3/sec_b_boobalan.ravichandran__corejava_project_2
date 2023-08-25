package in.fssa.fertagriboomi.validator;

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
	public static void validate(Product product) throws ValidationException {
		if (product == null) {
			throw new ValidationException("Invalid Product");
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

		// if (product.getName() == null || "".equals(product.getName().trim())) {
//			throw new ValidationException("Product name cannot be null or Empty");
//		}

//		if (product.getProductWeight() == null || "".equals(product.getProductWeight().trim())) {
//			throw new ValidationException("Product quantity cannot be nul or empty");
//		}

//		if (product.getApplication() == null || "".equals(product.getApplication().trim())) {
//			throw new ValidationException("Product Application cannot be nul or empty");
//		}
//
//		if (product.getBenefits() == null || "".equals(product.getBenefits().trim())) {
//			throw new ValidationException("Product Benifits cannot be nul or empty");
//		}
//
//		if (product.getDescription() == null || "".equals(product.getDescription().trim())) {
//			throw new ValidationException("Product Description cannot be nul or empty");
//		}
//
//		if (product.getManufacture() == null || "".equals(product.getManufacture().trim())) {
//			throw new ValidationException("Product manufacture company cannot be null or empty.");
//		}

		ProductDAO productDAO = new ProductDAO();
		try {
			boolean categoryExists = productDAO.isCategoryExists(product.getCategoryId());
			if (!categoryExists) {
				throw new ValidationException("Category does not exist");
			}
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

		try {
			productDAO = new ProductDAO();
			boolean productExists = productDAO.isProductAlreadyExists(product.getName());
			if (productExists) {
				throw new ValidationException("Product name is already exists");
			}
		} catch (DAOException e) {
			throw new ValidationException(e);
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

		ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO();
			productDAO.findById(id);

		} catch (DAOException e) {
			throw new ValidationException(e);
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
			throw new ValidationException(e);
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
			throw new ValidationException(e);
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
		ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO();
			boolean categoryExists = productDAO.isCategoryExists(id);
			if (categoryExists) {
				productDAO.listAllTheProductsByCategoryId(id);
			} else {
				throw new ValidationException("Invalid Category id");
			}
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}

}
