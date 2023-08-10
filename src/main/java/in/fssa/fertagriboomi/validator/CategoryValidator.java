package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.dao.CategoryDAO;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Category;

public class CategoryValidator {
	public static void validate(Category category) throws ValidationException {

		if (category == null) {
			throw new ValidationException("Invalid category input");
		}

		if (category.getName() == null || "".equals(category.getName().trim())) {
			throw new ValidationException("Category name cannot be null or Empty");
		}

		if (category.getId() <= 0) {
			throw new ValidationException("Invalid category ID");

		}

		if (category.getCategory_type_id() <= 0) {
			throw new ValidationException("Invalid category type ID");
		}
	}

	public static void ValidateId(int newId) throws ValidationException {
		if (newId <= 0) {
			throw new ValidationException("Invalid category ID");
		}

		CategoryDAO categoryDao = null;
		try {
			categoryDao = new CategoryDAO();
			categoryDao.findById(newId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
	}

	public static void validateCategoryTypeId(int categoryTypeId) throws ValidationException {
		if (categoryTypeId <= 0) {
			throw new ValidationException("Invalid category type Id");
		}

		CategoryDAO categoryDao = null;
		try {
			categoryDao = new CategoryDAO();
			categoryDao.isCategoryTypeIdExists(categoryTypeId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}
}
