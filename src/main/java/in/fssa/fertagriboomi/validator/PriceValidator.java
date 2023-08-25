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
	public static void validate(int productId, Price price) throws ValidationException {
		if (price == null) {
			throw new ValidationException("Invalid Price input");
		}

		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

		if (!(price.getPrice() >= 50 && price.getPrice() <= 10000)) {
			throw new ValidationException("Price should be between a minimum of 50 and a maximum of 10000.");
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
	public static void validateUpdate(int productId, Price newPrice) throws ValidationException {
		if (newPrice == null) {
			throw new ValidationException("Invalid Price input");
		}

		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

		if (newPrice.getPrice() <= 50 || newPrice.getPrice() >= 10000) {
			throw new ValidationException("Price should be between a minimum of 50 and a maximum of 10000.");
		}

		PriceDAO priceDAO = null;
		try {
			priceDAO = new PriceDAO();
			priceDAO.isProductExists(productId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

		
		try {
			priceDAO = new PriceDAO();
			priceDAO.isPriceAlreadyExists(productId, newPrice);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
	}
}
