package in.fssa.fertagriboomi.interfaces;

import java.sql.Timestamp;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.model.Price;

public interface PriceInterface {

	void create(int productId, int newPrice, int newDiscount, Timestamp dateTime) throws DAOException;

}
