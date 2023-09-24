package in.fssa.fertagriboomi.service;

import java.util.*;

import in.fssa.fertagriboomi.dao.StockDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.OrderItems;
import in.fssa.fertagriboomi.model.Stocks;
import in.fssa.fertagriboomi.validator.StockValidator;

public class StockService {
	public void createStock(Stocks newStock) throws ServiceException, ValidationException {
		StockDAO stockDAO = new StockDAO();
		StockValidator.validateCreate(newStock);
		
		try {
			stockDAO.create(newStock);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
			
	}
	public void updateStock(Stocks newStock) throws ServiceException, ValidationException {
		StockDAO stockDAO = new StockDAO();
		StockValidator.validateUpdate(newStock);
          
		try {
		    Stocks stock = stockDAO.getStockByProductId(newStock.getProductId());
		    int stockCount = stock.getStockCount() + newStock.getStockCount();
		    Stocks newProductStock = new Stocks();
		    newProductStock.setProductId(newStock.getProductId());
		    newProductStock.setStockCount(stockCount);
		    stockDAO.update(stock.getId());
		    stockDAO.create(newProductStock);
		} catch (DAOException e) {
		    e.printStackTrace();
		    throw new ServiceException(e.getMessage());
		}
			
	}
	
	public int findQuantities(int productId) throws ServiceException, ValidationException {
		StockDAO stockDAO = new StockDAO();
		StockValidator.validateProductId(productId);
          int totalQuantitiesInOrers = 0;
		try {
		   List<OrderItems> orderItem = stockDAO.getQuantitiesByProductId(productId);
		   for(OrderItems order: orderItem) {
			   
			   totalQuantitiesInOrers += order.getQuantity();
			   
		   }
		} catch (DAOException e) {
		    e.printStackTrace();
		    throw new ServiceException(e.getMessage());
		}
			return totalQuantitiesInOrers;
	}
	public int findStockCountByProductId(int productId) throws ServiceException, ValidationException {
		StockDAO stockDAO = new StockDAO();
		StockValidator.validateProductId(productId);
          int totalStockCount = 0;
		try {
		   Stocks orderItem = stockDAO.getStockByProductId(productId);
		   totalStockCount = orderItem.getStockCount();
		  
		} catch (DAOException e) {
		    e.printStackTrace();
		    throw new ServiceException(e.getMessage());
		}
		return totalStockCount;
	}
	
	
	
}
