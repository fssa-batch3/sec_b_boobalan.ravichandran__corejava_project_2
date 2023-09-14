package in.fssa.fertagriboomi.service;

import java.time.LocalDateTime;
import java.util.List;

import com.google.protobuf.Timestamp;

import in.fssa.fertagriboomi.dao.OrdersDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.OrderItems;
import in.fssa.fertagriboomi.model.Orders;
import in.fssa.fertagriboomi.validator.OrdersValidator;

public class OrdersService {

	public int createOrder(Orders order) throws ServiceException, ValidationException {
		OrdersDAO ordersDAO = new OrdersDAO();
		OrdersValidator.validateCreate(order);
	

		try {
			System.out.println(ordersDAO.create(order));
			return ordersDAO.create(order);
			   
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void createOrderItems(int orderId, OrderItems orderItems) throws ServiceException, ValidationException {
		OrdersDAO ordersDAO = new OrdersDAO();
		OrdersValidator.validateCreateOrderItems(orderId,orderItems);

		try {
			// Get the current date and time as a Timestamp
			LocalDateTime currentDateTime = LocalDateTime.now();
			java.sql.Timestamp orderTimestamp = java.sql.Timestamp.valueOf(currentDateTime);
			// Set the order date as the current date and time
			orderItems.setOrderDate(orderTimestamp);
			// Calculate the delivery date as 10 days from the current date
			LocalDateTime deliveryDateTime = currentDateTime.plusDays(10);
			java.sql.Timestamp deliveryTimestamp = java.sql.Timestamp.valueOf(deliveryDateTime);

			// Set the delivery date as 10 days from the current date
			orderItems.setDeliveryDate(deliveryTimestamp);

		     ordersDAO.createOrderItem(orderId, orderItems);
			   
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
//	public List<OrderItems> getAllOrders() throws ServiceException{
//		OrdersDAO ordersDAO = new OrdersDAO();
//		try {
//			return ordersDAO.findAll();
//		} catch (DAOException e) {
//			e.printStackTrace();
//			throw new ServiceException(e.getMessage());
//		}
//		
//	}
//	
//	public List<OrderItems> findAllOrdersByUserEmail(String userEmail) throws ServiceException{
//		OrdersDAO ordersDAO = new OrdersDAO();
//		try {
//			return ordersDAO.findAllOrderByUserEmail(userEmail);
//		} catch (DAOException e) {
//			e.printStackTrace();
//			throw new ServiceException(e.getMessage());
//		}
//		
//	}
//	
//	public OrderItems findAllOrderByOrderId(int orderId) throws ServiceException{
//		OrdersDAO ordersDAO = new OrdersDAO();
//		try {
//			return ordersDAO.findAllOrderByOrderId(orderId);
//		} catch (DAOException e) {
//			e.printStackTrace();
//			throw new ServiceException(e.getMessage());
//		}
//		
//	}
}
