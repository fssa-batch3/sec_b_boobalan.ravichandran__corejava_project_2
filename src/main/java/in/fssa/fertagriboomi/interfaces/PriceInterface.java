package in.fssa.fertagriboomi.interfaces;

import java.sql.Timestamp;

import in.fssa.fertagriboomi.model.Price;

public interface PriceInterface {

	public void create(int ProductId, Price newPrice, Timestamp dateTime) throws Exception;

}
