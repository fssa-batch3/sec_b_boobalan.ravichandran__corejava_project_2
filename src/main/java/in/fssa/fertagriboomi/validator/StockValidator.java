package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.dao.StockDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Stocks;

public class StockValidator {

	public static void validateCreate(Stocks newStock) throws ValidationException {
	if(newStock == null) {	
		throw new ValidationException("Invalid Stock input");
	}
		if(newStock.getProductId()<= 0) {
			throw new ValidationException("Invalid Product Id");
		}
		
		if(newStock.getStockCount()<=0) {
			
			throw new ValidationException("Invalid Stock Count");
		}
		ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO();
			productDAO.findById(newStock.getProductId());

		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}

	public static void validateUpdate(Stocks newStock) throws ValidationException {
		if(newStock == null) {	
			throw new ValidationException("Invalid Stock input");
		}
			if(newStock.getProductId()<= 0) {
				throw new ValidationException("Invalid Product Id");
			}
			
			if(newStock.getStockCount()<=0) {
				
				throw new ValidationException("Invalid Stock Count");
			}
			ProductDAO productDAO = null;
			try {
				productDAO = new ProductDAO();
				productDAO.findById(newStock.getProductId());

			} catch (DAOException e) {
				throw new ValidationException(e.getMessage());
			}
		
			StockDAO stockDAO = null;
			
			try {
				stockDAO = new StockDAO();
				
				int stockId = stockDAO.findStockIdByProductId(newStock.getProductId());
				if(stockId<=0) {
					throw new DAOException("This Stock id is not available");
					
				}
			}catch (DAOException e) {
				throw new ValidationException(e.getMessage());
			}
		
	}

	public static void validateProductId(int productId) throws ValidationException {
		if(productId<= 0) {
			throw new ValidationException("Invalid Product Id");
		}
		ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO();
			productDAO.findById(productId);

		} catch (DAOException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}

}
