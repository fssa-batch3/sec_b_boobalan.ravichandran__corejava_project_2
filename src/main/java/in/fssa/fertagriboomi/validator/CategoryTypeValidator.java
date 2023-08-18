package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;

import in.fssa.fertagriboomi.model.CategoryType;
import in.fssa.fertagriboomi.dao.CategoryTypeDAO;

public class CategoryTypeValidator {

	/**
	 * 
	 * @param categorytype
	 * @throws ValidationException
	 */
	public static void validate(CategoryType categorytype) throws ValidationException {

		if (categorytype == null) {
			throw new ValidationException("Invalid category type");
		}

		if (categorytype.getName() == null || "".equals(categorytype.getName().trim())) {
			throw new ValidationException("Category type name cannot be null or Empty");
		}

		if (categorytype.getId() <= 0) {
			throw new ValidationException("Invalid category type ID");

		}
	}

	/**
	 * 
	 * @param newId
	 * @throws ValidationException
	 */
	public static void ValidateId(int newId) throws ValidationException {
		if (newId <= 0) {
			throw new ValidationException("Invalid category type ID");
		}

		CategoryTypeDAO categoryTypeDao = null;
		try {
			categoryTypeDao = new CategoryTypeDAO();
			categoryTypeDao.isCategoryIdExists(newId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}
}
