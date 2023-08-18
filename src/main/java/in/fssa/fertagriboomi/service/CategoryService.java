package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.CategoryDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Category;
import in.fssa.fertagriboomi.validator.CategoryValidator;

public class CategoryService {

	/**
	 * 
	 * @return
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
	 * 
	 * @param newId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */

	public Category findById(int newId) throws ServiceException, ValidationException {
		CategoryDAO categoryDao = null;
		Category category = null;
		try {
			categoryDao = new CategoryDAO();
			CategoryValidator.ValidateId(newId);
			category = categoryDao.findById(newId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return category;

	}

	/**
	 * 
	 * @param categoryTypeId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
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
