package in.fssa.fertagriboomi.service;

import java.util.*;

import in.fssa.fertagriboomi.dao.CategoryTypeDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.CategoryType;
import in.fssa.fertagriboomi.validator.CategoryTypeValidator;

/**
 * Service class for managing CategoryType entities.
 */
public class CategoryTypeService {

	/**
	 * Retrieves a list of all category types.
	 *
	 * @return A list of CategoryType objects.
	 * @throws ServiceException If an error occurs while interacting with the
	 *                          database.
	 */
	public List<CategoryType> getAllCategoryTypes() throws ServiceException {
		CategoryTypeDAO categoryTypeDao = new CategoryTypeDAO();
		List<CategoryType> categoryTypeList = null;
		try {
			categoryTypeList = categoryTypeDao.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		Iterator<CategoryType> iterator = categoryTypeList.iterator();

		while (iterator.hasNext()) {
			CategoryType categoryType = iterator.next();
			System.out.println(categoryType);
		}
		return categoryTypeList;
	}

	/**
	 * Finds a category type by its ID.
	 *
	 * @param newId The ID of the category type to find.
	 * @return The CategoryType object with the given ID.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided ID is not valid.
	 */
	public CategoryType findCategoryTypeById(int newId) throws ServiceException, ValidationException {
		CategoryTypeDAO categoryTypeDao = null;
		CategoryType categoryType = null;
		try {
			categoryTypeDao = new CategoryTypeDAO();

			CategoryTypeValidator.validateId(newId);

			categoryType = categoryTypeDao.findById(newId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return categoryType;
	}
}
