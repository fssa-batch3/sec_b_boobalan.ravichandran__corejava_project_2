package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.CategoryDAO;
import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.*;

import in.fssa.fertagriboomi.validator.ProductValidator;

public class ProductService {
/**
 * 
 * @param newProduct
 * @throws ServiceException
 * @throws ValidationException
 */
	public void create(Product newProduct) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			ProductValidator.validate(newProduct);

			Price productPrice = newProduct.getPrice();
			int priceValue = productPrice.getPrice();

			if (priceValue <= 50 || priceValue >= 10000) {
				throw new ValidationException("Price should be between a minimum of 50 and a maximum of 10000.");
			}

			int productId = productDao.create(newProduct);
			PriceService priceService = new PriceService();
			priceService.create(productId, newProduct.getPrice());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * 
	 * @return
	 */
	public List<Product> getAll() {
		ProductDAO productDao = new ProductDAO();
		List<Product> productList = productDao.findAll();
		Iterator<Product> iterator = productList.iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			System.out.println(product);
		}
		return productList;

	}

	/**
	 * 
	 * @param id
	 * @param newUpdate
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void update(int id, Product newUpdate) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			ProductValidator.validateUpdate(id, newUpdate);
			productDao.update(id, newUpdate);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * 
	 * @param newId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Product findById(int newId) throws ServiceException, ValidationException {
		Product product = null;
		ProductDAO productDao = null;
		try {
			 productDao = new ProductDAO();
			ProductValidator.ValidateId(newId);
			product = productDao.findById(newId);

		}catch (DAOException e) {
			throw new ServiceException(e);
		}
		return product;

	}

	/**
	 * 
	 * @param id
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void delete(int id) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			ProductValidator.ValidateDeleteId(id);
			productDao.delete(id);
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public List<Product> listAllTheProductsByCategoryId(int id) throws ServiceException, ValidationException {
		List<Product> product = null;
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
		ProductValidator.ValidateCategoryId(id);
		product =  productDao.listAllTheProductsByCategoryId(id);
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
		return product;
	}

}
