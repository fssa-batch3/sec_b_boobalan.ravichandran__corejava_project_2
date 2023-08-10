package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.CategoryDAO;

import in.fssa.fertagriboomi.model.Category;
import in.fssa.fertagriboomi.validator.CategoryTypeValidator;
import in.fssa.fertagriboomi.validator.CategoryValidator;

public class CategoryService {

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

	public Category findById(int newId) throws Exception {
		CategoryDAO categoryDao = new CategoryDAO();
		CategoryValidator.ValidateId(newId);

		return categoryDao.findById(newId);

	}

	public List<Category> findCategoriesByCategoryId(int categoryTypeId) throws Exception {
		CategoryDAO categoryDao = new CategoryDAO();
		CategoryValidator.validateCategoryTypeId(categoryTypeId);
		List<Category> categoryList = categoryDao.findCategoryByCategoryTypeId(categoryTypeId);
		Iterator<Category> iterator = categoryList.iterator();
		while (iterator.hasNext()) {
			Category categoryType = iterator.next();
			System.out.println(categoryType);

		}
		return categoryList;

	}
}
