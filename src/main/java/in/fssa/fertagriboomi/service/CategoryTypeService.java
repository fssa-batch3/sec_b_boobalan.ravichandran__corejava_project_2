package in.fssa.fertagriboomi.service;

import java.util.*;

import in.fssa.fertagriboomi.dao.CategoryTypeDAO;
import in.fssa.fertagriboomi.model.CategoryType;
import in.fssa.fertagriboomi.validator.CategoryTypeValidator;

public class CategoryTypeService {

	public List<CategoryType> getAll() {
		CategoryTypeDAO categoryTypeDao = new CategoryTypeDAO();
		List<CategoryType> categoryTypeList = categoryTypeDao.findAll();
		Iterator<CategoryType> iterator = categoryTypeList.iterator();

		while (iterator.hasNext()) {
			CategoryType categoryType = iterator.next();
			System.out.println(categoryType);

		}
		return categoryTypeList;
	}

	public CategoryType findById(int newId) throws Exception {
		CategoryTypeDAO categoryTypeDao = new CategoryTypeDAO();

		CategoryTypeValidator.ValidateId(newId);

		return categoryTypeDao.findById(newId);

	}

}
