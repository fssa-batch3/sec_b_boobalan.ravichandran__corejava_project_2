package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.validator.ProductValidator;

/**
 * Service class for managing Product entities.
 */
public class ProductService {

	/**
	 * Creates a new product.
	 *
	 * @param newProduct The new product to create.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided data is not valid for creating a
	 *                             product.
	 */
	public void createProduct(Product newProduct) throws ServiceException, ValidationException {
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
			priceService.createPrice(productId, newProduct.getPrice());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Retrieves a list of all products.
	 *
	 * @return A list of Product objects.
	 */
	public List<Product> getAllProducts() {
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
	 * Updates a product by its ID.
	 *
	 * @param id        The ID of the product to update.
	 * @param newUpdate The updated product object.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided data is not valid for updating
	 *                             the product.
	 */
	public void updateProduct(int id, Product newUpdate) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			ProductValidator.validateUpdate(id, newUpdate);
			productDao.update(id, newUpdate);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Finds a product by its ID.
	 *
	 * @param newId The ID of the product to find.
	 * @return The Product object with the given ID.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided ID is not valid.
	 */
	public Product findProductById(int newId) throws ServiceException, ValidationException {
		Product product = null;
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			ProductValidator.validateId(newId);
			product = productDao.findById(newId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return product;
	}

	/**
	 * Deletes a product by its ID.
	 *
	 * @param id The ID of the product to delete.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided ID is not valid.
	 */
	public void deleteProduct(int id) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			ProductValidator.validateDeleteId(id);
			productDao.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Lists all products belonging to a specific category.
	 *
	 * @param id The ID of the category.
	 * @return A list of Product objects belonging to the specified category.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided category ID is not valid.
	 */
	public List<Product> listAllProductsByCategoryId(int id) throws ServiceException, ValidationException {
		List<Product> product = null;
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			ProductValidator.validateCategoryId(id);
			product = productDao.listAllTheProductsByCategoryId(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return product;
	}

	/**
	 * Changes the active status of a product by its ID.
	 *
	 * @param id The ID of the product to change the active status for.
	 * @throws ServiceException If an error occurs while interacting with the
	 *                          database.
	 */
	public void changeIsActiveToActive(int id) throws ServiceException {
		try {
			ProductDAO productDao = new ProductDAO();
			productDao.changeActive(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
