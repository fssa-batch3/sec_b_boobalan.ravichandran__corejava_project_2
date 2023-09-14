package in.fssa.fertagriboomi.service;

import java.time.LocalDateTime;

import in.fssa.fertagriboomi.dao.PriceDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.validator.PriceValidator;

/**
 * Service class for managing Price entities.
 */
public class PriceService {

	public Price getPrice(int productId) throws ServiceException, ValidationException {
		PriceDAO priceDAO = new PriceDAO();
		Price price = null;
		try {
			PriceValidator.validateProductId(productId);
			price = priceDAO.getPriceByProductId(productId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return price;

	}

	public int getPriceId(int productId) throws ServiceException, ValidationException {
		PriceDAO priceDAO = new PriceDAO();
		int priceId;
		try {
			PriceValidator.validateProductId(productId);
			priceId = priceDAO.getPriceIdByProductId(productId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return priceId;

	}

	/**
	 * Creates a new price entry for a product.
	 *
	 * @param productId The ID of the product for which the price is created.
	 * @param newPrice  The new price object to create.
	 * @throws ValidationException If the provided data is not valid for creating a
	 *                             price.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 */
	public void createPrice(int productId, int newPrice, int newDiscount) throws ValidationException, ServiceException {
		try {
			PriceDAO priceDao = new PriceDAO();
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);
			PriceValidator.validate(productId, newPrice, newDiscount);
			priceDao.create(productId, newPrice, newDiscount, dateTime);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates the price of a product and creates a new price entry.
	 *
	 * @param productId The ID of the product for which the price is updated.
	 * @param newPrice  The new price object to update and create.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided data is not valid for updating
	 *                             the price.
	 */
	public void updatePrice(int productId, int newPrice, int newDiscount) throws ServiceException, ValidationException {
		PriceDAO priceDao = new PriceDAO();
		PriceValidator.validateUpdate(productId, newPrice, newDiscount);

		try {
			int priceId = priceDao.getPriceIdByProductId(productId);
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);

			priceDao.updateProductPrice(priceId, dateTime);
			priceDao.create(productId, newPrice, newDiscount, dateTime);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
