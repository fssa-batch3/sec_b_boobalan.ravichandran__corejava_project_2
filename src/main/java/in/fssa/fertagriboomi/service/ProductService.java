package in.fssa.fertagriboomi.service;

import java.util.List;

import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.model.Stocks;
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
			ProductValidator.validateCreate(newProduct);

			int priceValue = newProduct.getPrice();

			if (priceValue <= 50 || priceValue >= 50000) {
				throw new ValidationException("Price should be between a minimum of 50 and a maximum of 50000.");
			}

			
			
			int productId = productDao.create(newProduct);
			
			StockService stockService = new StockService();
			Stocks stock = new Stocks();
			stock.setProductId(productId);
			stock.setStockCount(newProduct.getStockCount());
			stockService.createStock(stock);
			
			PriceService priceService = new PriceService();
			priceService.createPrice(productId, newProduct.getPrice(), newProduct.getDiscount());
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a list of all products.
	 *
	 * @return A list of Product objects.
	 * @throws ServiceException
	 */
	public List<Product> getAllProducts() throws ServiceException {
		ProductDAO productDao = new ProductDAO();
		List<Product> productList = null;
		try {
			productList = productDao.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		for (Product product : productList) {
			int priceValue, discountValue;
			try {

				Price price = new PriceService().getPrice(product.getId());
				priceValue = price.getPrice();
				discountValue = price.getDiscount();
				// System.out.println(priceValue + " " + discountValue);
				product.setPrice(priceValue);
				product.setDiscount(discountValue);

			} catch (ServiceException | ValidationException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

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
			throw new ServiceException(e.getMessage());
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
			Price price = new PriceService().getPrice(newId);
			int productPrice = price.getPrice();
			int productDiscount = price.getDiscount();
			product.setPrice(productPrice);
			product.setDiscount(productDiscount);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
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
			throw new ServiceException(e.getMessage());
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
		List<Product> productList = null;
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			ProductValidator.validateCategoryId(id);
			productList = productDao.listAllTheProductsByCategoryId(id);
			for (Product product : productList) {
				Price price = new PriceService().getPrice(product.getId());
				int priceValue = price.getPrice();
				int discountValue = price.getDiscount();
				// System.out.println(price);
				product.setPrice(priceValue);
				product.setDiscount(discountValue);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return productList;
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
			throw new ServiceException(e.getMessage());
		}
	}
}
