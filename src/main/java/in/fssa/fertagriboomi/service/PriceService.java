package in.fssa.fertagriboomi.service;

import java.time.LocalDateTime;

import in.fssa.fertagriboomi.dao.PriceDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.validator.PriceValidator;

public class PriceService {
	public void create(int productId, Price newPrice) throws Exception {
		PriceDAO priceDao = new PriceDAO();
		LocalDateTime localDateTime = LocalDateTime.now();
		java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);
		PriceValidator.validate(productId, newPrice);
		priceDao.create(productId, newPrice, dateTime);

	}

	public void updatePrice(int productId, Price newPrice) throws Exception {
		PriceDAO priceDao = new PriceDAO();
		PriceValidator.validateUpdate(productId, newPrice);
		int priceId = 0;
		try {
			priceId = priceDao.getPriceIdByProductId(productId);
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);

			priceDao.updateProductPrice(priceId, dateTime);
			priceDao.create(productId, newPrice, dateTime);
		} catch (DAOException e) {
			throw new Exception(e);
		}
	}

}
