package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.dao.PriceDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Price;

/**
 * Validator class for validating Price objects.
 */
public class PriceValidator {

	/**
	 * Validates a Price object for creation.
	 *
	 * @param productId The ID of the associated product.
	 * @param price     The Price object to validate.
	 * @throws ValidationException If the Price object is invalid.
	 */
	public static void validate(int productId, int price) throws ValidationException {

		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

		if (!(price >= 50 && price <= 50000)) {
			throw new ValidationException("Price should be between a minimum of 50 and a maximum of 50000.");
		}
	}

	/**
	 * Validates a Price object for update.
	 *
	 * @param productId The ID of the associated product.
	 * @param newPrice  The updated Price object to validate.
	 * @throws ValidationException If the Price object is invalid or conflicts with
	 *                             existing data.
	 */
	public static void validateUpdate(int productId, int newPrice) throws ValidationException {
//		if (newPrice == null) {
//			throw new ValidationException("Invalid Price input");
//		}

		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

		if (newPrice <= 50 || newPrice >= 50000) {
			throw new ValidationException("Price should be between a minimum of 50 and a maximum of 50000.");
		}

		PriceDAO priceDAO = null;
		try {
			priceDAO = new PriceDAO();
			priceDAO.isProductExists(productId);
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

		try {
			priceDAO = new PriceDAO();
			priceDAO.isPriceAlreadyExists(productId, newPrice);
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	public static void validateProductId(int productId) throws ValidationException {
		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}
		PriceDAO priceDAO = null;
		try {
			priceDAO = new PriceDAO();
			priceDAO.isProductExists(productId);
		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}

	}
}
