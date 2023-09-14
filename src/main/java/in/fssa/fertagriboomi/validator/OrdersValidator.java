package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.dao.OrdersDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.OrderItems;
import in.fssa.fertagriboomi.model.Orders;

public class OrdersValidator {

	public static void validateCreate(Orders order) throws ValidationException {
		
		if (order == null) {
			throw new ValidationException("Invalid Order Input");
		} 

		if (order.getAddressId() <= 0) {
			throw new ValidationException("Invalid Address Id");
		}

		if (order.getUserEmail() == null || "".equals(order.getUserEmail()) )  {
			throw new ValidationException("User Email Cannot be null or empty");
		}
		
		OrdersDAO ordersDAO = null;
		try {
			ordersDAO = new OrdersDAO();
			boolean addressExists = ordersDAO.isAddressExists(order.getAddressId());
			if (!addressExists) {
				throw new ValidationException("This address is not available in the address list");
			}
		} catch (DAOException e) {

			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

	public static void validateCreateOrderItems(int orderId, OrderItems orderItem) throws ValidationException {
		if (orderItem == null) {
			throw new ValidationException("Invalid OrderItem Input");
		} 
		if(orderId <=0) {
			throw new ValidationException("Invalid Order Id");
		}
		if (orderItem.getPriceId() <= 0) {
			throw new ValidationException("Invalid Price Id");
		}
		if (orderItem.getProductId() <= 0) {
			throw new ValidationException("Invalid Product Id");
		}
		
		OrdersDAO ordersDAO = null;

		try {
			ordersDAO = new OrdersDAO();
			boolean priceExists = ordersDAO.isPriceIdExists(orderItem.getPriceId());
			if (!priceExists) {
				throw new ValidationException("This product price is not available");
			}
		} catch (DAOException e) {

			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		try {
			ordersDAO = new OrdersDAO();
			boolean productExists = ordersDAO.isProductExists(orderItem.getProductId());
			if (!productExists) {
				throw new ValidationException("This product is not available in the product list");
			}
		} catch (DAOException e) {

			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		
		
	}
}
