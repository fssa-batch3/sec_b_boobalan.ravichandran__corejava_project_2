package in.fssa.fertagriboomi.service;

import java.util.*;

import in.fssa.fertagriboomi.dao.CategoryTypeDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.CategoryType;
import in.fssa.fertagriboomi.validator.CategoryTypeValidator;

public class CategoryTypeService {

	public List<CategoryType> getAll() throws ServiceException {
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

	public CategoryType findById(int newId) throws ServiceException, ValidationException {
		CategoryTypeDAO categoryTypeDao = null;
		CategoryType categoryType = null;
		try {
			categoryTypeDao = new CategoryTypeDAO();

			CategoryTypeValidator.ValidateId(newId);

			categoryType = categoryTypeDao.findById(newId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return categoryType;

	}

}
