package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.dao.PriceDAO;
import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Price;

public class PriceValidator {
	public static void validate(int productId, Price price) throws ValidationException {

		if (price == null) {
			throw new ValidationException("Invalid Price input");
		}

		if (productId <= 0) {
			throw new ValidationException("Invalid Product Id ");

		}
		if (!(price.getPrice() >= 50 && price.getPrice() <= 10000)) {
			throw new ValidationException("Price should be between a minimum of 50 and a maximum of 10000.");
		}

	}

	public static void validateUpdate(int productId, Price newPrice) throws ValidationException {
		if (newPrice == null) {
			throw new ValidationException("Invalid Price input");
		}

		if (productId <= 0) {
			throw new ValidationException("Invalid Product Id ");

		}
		if (!(newPrice.getPrice() >= 50 && newPrice.getPrice() <= 10000)) {
			throw new ValidationException("Price should be between a minimum of 50 and a maximum of 10000.");
		}

		PriceDAO priceDao = null;
		try {
			priceDao = new PriceDAO();
			priceDao.isProductExists(productId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}

}
