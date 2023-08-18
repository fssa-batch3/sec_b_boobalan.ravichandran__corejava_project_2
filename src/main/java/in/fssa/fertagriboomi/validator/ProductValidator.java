package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.util.StringUtil;

public class ProductValidator {
	
	/**
	 * 
	 * @param product
	 * @throws ValidationException
	 */
	public static void validate(Product product) throws ValidationException {
		if (product == null) {
			throw new ValidationException("Invalid Product");
		}

		if (product.getCategory_id() <= 0) {
			throw new ValidationException("Invalid Category Id");
		}

		StringUtil.rejectIfInvalidString(product.getName(), "Product name");
		StringUtil.rejectIfInvalidString(product.getProduct_weight(), "Product quantity");
		StringUtil.rejectIfInvalidString(product.getApplication(), "Product Application");
		StringUtil.rejectIfInvalidString(product.getBenefits(), "Product Benefits");
		StringUtil.rejectIfInvalidString(product.getDescription(), "Product Description");
		StringUtil.rejectIfInvalidString(product.getManufacture(), "Product manufacture company");

		// if (product.getName() == null || "".equals(product.getName().trim())) {
//			throw new ValidationException("Product name cannot be null or Empty");
//		}

//		if (product.getProduct_weight() == null || "".equals(product.getProduct_weight().trim())) {
//			throw new ValidationException("Product quantity cannot be nul or empty");
//		}

//		if (product.getApplication() == null || "".equals(product.getApplication().trim())) {
//			throw new ValidationException("Product Application cannot be nul or empty");
//		}
//
//		if (product.getBenefits() == null || "".equals(product.getBenefits().trim())) {
//			throw new ValidationException("Product Benifits cannot be nul or empty");
//		}
//
//		if (product.getDescription() == null || "".equals(product.getDescription().trim())) {
//			throw new ValidationException("Product Description cannot be nul or empty");
//		}
//
//		if (product.getManufacture() == null || "".equals(product.getManufacture().trim())) {
//			throw new ValidationException("Product manufacture company cannot be null or empty.");
//		}

		ProductDAO productDao = new ProductDAO();
		try {
			boolean categoryExists = productDao.isCategoryExists(product.getCategory_id());
			if (!categoryExists) {
				throw new ValidationException("Category does not exist");
			}
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

		try {
			productDao = new ProductDAO();
			boolean productExists = productDao.isProductAlreadyExists(product.getName());
			if (productExists) {
				throw new ValidationException("Product name is already exists");
			}
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}
	
	
/**
 * 
 * @param id
 * @param newUpdate
 * @throws ValidationException
 */
	public static void validateUpdate(int id, Product newUpdate) throws ValidationException {
		if (newUpdate == null) {
			throw new ValidationException("Invalid Product input");
		}

		if (id <= 0) {
			throw new ValidationException("Invalid Product id");
		}

		StringUtil.rejectIfInvalidString(newUpdate.getProduct_weight(), "Product quantity");
		StringUtil.rejectIfInvalidString(newUpdate.getApplication(), "Product Application");
		StringUtil.rejectIfInvalidString(newUpdate.getBenefits(), "Product Benefits");
		StringUtil.rejectIfInvalidString(newUpdate.getDescription(), "Product Description");

		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			productDao.findById(id);

		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}

	/**
	 * 
	 * @param newId
	 * @throws ValidationException
	 */
	public static void ValidateId(int newId) throws ValidationException {

		if (newId <= 0) {
			throw new ValidationException("Invalid Product id");
		}
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			productDao.findById(newId);

		} catch (DAOException e) {
			throw new ValidationException(e);
		}


	}
	
	/**
	 * 
	 * @param newId
	 * @throws ValidationException
	 */
	public static void ValidateDeleteId(int newId) throws ValidationException {

		if (newId <= 0) {
			throw new ValidationException("Invalid Product id");
		}
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			productDao.findById(newId);

		} catch (DAOException e) {
			throw new ValidationException(e);
		}

		try {
			productDao = new ProductDAO();
			productDao.isDeletedProduct(newId);

		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void ValidateCategoryId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Invalid Category id");
		}
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			boolean categoryExists = productDao.isCategoryExists(id);
			if (categoryExists) {
				productDao.listAllTheProductsByCategoryId(id);
			} else {
				throw new ValidationException("Invalid Category id");
			}
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}

}
