package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.CategoryType;
import in.fssa.fertagriboomi.dao.CategoryTypeDAO;

/**
 * Validator class for validating CategoryType objects.
 */
public class CategoryTypeValidator {

	/**
	 * Validates a CategoryType object.
	 *
	 * @param categoryType The CategoryType object to validate.
	 * @throws ValidationException If the CategoryType object is invalid.
	 */
	public static void validate(CategoryType categoryType) throws ValidationException {
		if (categoryType == null) {
			throw new ValidationException("Invalid category type");
		}

		if (categoryType.getName() == null || "".equals(categoryType.getName().trim())) {
			throw new ValidationException("Category type name cannot be null or empty");
		}

		if (categoryType.getId() <= 0) {
			throw new ValidationException("Invalid category type ID");
		}
	}

	/**
	 * Validates a category type ID.
	 *
	 * @param newId The category type ID to validate.
	 * @throws ValidationException If the category type ID is invalid or not found
	 *                             in the database.
	 */
	public static void validateId(int newId) throws ValidationException {
		if (newId <= 0) {
			throw new ValidationException("Invalid category type ID");
		}

		CategoryTypeDAO categoryTypeDAO = null;
		try {
			categoryTypeDAO = new CategoryTypeDAO();
			categoryTypeDAO.isCategoryIdExists(newId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
	}
}
