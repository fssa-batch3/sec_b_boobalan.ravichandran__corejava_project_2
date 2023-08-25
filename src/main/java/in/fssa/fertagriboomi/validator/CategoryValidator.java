package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.dao.CategoryDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Category;

/**
 * Validator class for validating Category objects.
 */
public class CategoryValidator {

	/**
	 * Validates a Category object.
	 *
	 * @param category The Category object to validate.
	 * @throws ValidationException If the Category object is invalid.
	 */
	public static void validate(Category category) throws ValidationException {
		if (category == null) {
			throw new ValidationException("Invalid category input");
		}

		if (category.getName() == null || "".equals(category.getName().trim())) {
			throw new ValidationException("Category name cannot be null or empty");
		}

		if (category.getId() <= 0) {
			throw new ValidationException("Invalid category ID");
		}

		if (category.getCategoryTypeId() <= 0) {
			throw new ValidationException("Invalid category type ID");
		}
	}

	/**
	 * Validates a category ID.
	 *
	 * @param newId The category ID to validate.
	 * @throws ValidationException If the category ID is invalid or not found in the
	 *                             database.
	 */
	public static void validateId(int newId) throws ValidationException {
		if (newId <= 0) {
			throw new ValidationException("Invalid category ID");
		}

		CategoryDAO categoryDAO = null;
		try {
			categoryDAO = new CategoryDAO();
			categoryDAO.findById(newId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
	}

	/**
	 * Validates a category type ID.
	 *
	 * @param categoryTypeId The category type ID to validate.
	 * @throws ValidationException If the category type ID is invalid or not found
	 *                             in the database.
	 */
	public static void validateCategoryTypeId(int categoryTypeId) throws ValidationException {
		if (categoryTypeId <= 0) {
			throw new ValidationException("Invalid category type ID");
		}

		CategoryDAO categoryDAO = null;
		try {
			categoryDAO = new CategoryDAO();
			categoryDAO.isCategoryTypeIdExists(categoryTypeId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
	}
}
