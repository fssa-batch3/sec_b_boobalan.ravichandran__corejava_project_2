package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.CategoryDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Category;
import in.fssa.fertagriboomi.validator.CategoryValidator;

/**
 * Service class for managing Category entities.
 */
public class CategoryService {

	/**
	 * Retrieves a list of all categories.
	 *
	 * @return A list of Category objects.
	 */
	public List<Category> getAll() {
		CategoryDAO categoryDao = new CategoryDAO();
		List<Category> categoryList = categoryDao.findAll();

		Iterator<Category> iterator = categoryList.iterator();

		while (iterator.hasNext()) {
			Category categoryType = iterator.next();
			System.out.println(categoryType);
		}
		return categoryList;
	}

	/**
	 * Finds a category by its ID.
	 *
	 * @param newId The ID of the category to find.
	 * @return The Category object with the given ID.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided ID is not valid.
	 */
	public Category findById(int newId) throws ServiceException, ValidationException {
		CategoryDAO categoryDao = null;
		Category category = null;
		try {
			categoryDao = new CategoryDAO();
			CategoryValidator.validateId(newId);
			category = categoryDao.findById(newId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return category;
	}

	/**
	 * Finds categories by their category type ID.
	 *
	 * @param categoryTypeId The ID of the category type.
	 * @return A list of Category objects that belong to the specified category
	 *         type.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided category type ID is not valid.
	 */
	public List<Category> findCategoriesByCategoryId(int categoryTypeId) throws ServiceException, ValidationException {
		CategoryDAO categoryDao = null;
		List<Category> categoryList = null;
		try {
			categoryDao = new CategoryDAO();
			CategoryValidator.validateCategoryTypeId(categoryTypeId);
			categoryList = categoryDao.findCategoryByCategoryTypeId(categoryTypeId);
			Iterator<Category> iterator = categoryList.iterator();
			while (iterator.hasNext()) {
				Category categoryType = iterator.next();
				System.out.println(categoryType);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return categoryList;
	}
}
