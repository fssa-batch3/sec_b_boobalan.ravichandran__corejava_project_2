package in.fssa.fertagriboomi.service;

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
	public List<Category> getAllCategories() {
		CategoryDAO categoryDao = new CategoryDAO();
		List<Category> categoryList = categoryDao.findAll();

		for (Category category : categoryList) {

			System.out.println(category);
		}
		return categoryList;
	}

	/**
	 * Finds a category by its ID.
	 *
	 * @param newId The ID of the category to find.
	 * @return The Category object with the given ID.
	 * @throws DAOException
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided ID is not valid.
	 */
	public Category findCategoryById(int newId) throws ValidationException, ServiceException {
		CategoryDAO categoryDao = null;
		Category category = null;

		categoryDao = new CategoryDAO();
		try {
			CategoryValidator.validateId(newId); // Validate the ID
			category = categoryDao.findById(newId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return category;
	}

	/**
	 * Finds categories by their category type ID.
	 *
	 * @param categoryTypeId The ID of the category type.
	 * @return A list of Category objects that belong to the specified category
	 *         type.
	 * @throws DAOException
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided category type ID is not valid.
	 */
	public List<Category> findCategoriesByCategoryTypeId(int categoryTypeId)
			throws ServiceException, ValidationException {
		CategoryDAO categoryDao = null;
		List<Category> categoryList = null;

		CategoryValidator.validateCategoryTypeId(categoryTypeId); // Validate the input

		try {
			categoryDao = new CategoryDAO();
			categoryList = categoryDao.findCategoriesByCategoryTypeId(categoryTypeId);
			for (Category categoryType : categoryList) {
				System.out.println(categoryType);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return categoryList;
	}

}
