package in.fssa.fertagriboomi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.Timestamp;

import in.fssa.fertagriboomi.dao.OrdersDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.OrderItems;
import in.fssa.fertagriboomi.model.Orders;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.model.Product;
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
	
	public List<Orders> getAllOrders() throws ServiceException, ValidationException {
	    OrdersDAO ordersDAO = new OrdersDAO();
	    List<Orders> totalOrders = new ArrayList<>();

	    try {
	        totalOrders = ordersDAO.findAll();
	        for (Orders order : totalOrders) {
	            List<OrderItems> orderItems = ordersDAO.findAllOrderItemByOrderId(order.getId());
	            order.setOrderItems(orderItems);

	            for (OrderItems orderItem : orderItems) {
	                Product product = new ProductService().findProductById(orderItem.getProductId());
	                orderItem.setProductName(product.getName());
	                orderItem.setProductImage(product.getImageURL());
	                orderItem.setOrderId(order.getId());
	                Price price = ordersDAO.getPriceByPriceId(orderItem.getPriceId());
	                orderItem.setPrice(price.getPrice());
	                orderItem.setDiscount(price.getDiscount());
	            }
	        }

	    } catch (DAOException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	    return totalOrders;
	}

	


	public List<Orders> getAllOrdersByUserEmail(String email) throws ServiceException, ValidationException {
	    OrdersDAO ordersDAO = new OrdersDAO();
	    List<Orders> totalOrders = new ArrayList<>();
	    OrdersValidator.validateUserEmail(email);
	    try {
	        totalOrders = ordersDAO.findAllOrderByEmail(email);
	        for (Orders order : totalOrders) {
	            List<OrderItems> orderItems = ordersDAO.findAllOrderItemByOrderId(order.getId());
	            order.setOrderItems(orderItems);

	            for (OrderItems orderItem : orderItems) {
	                Product product = new ProductService().findProductById(orderItem.getProductId());
	                orderItem.setProductName(product.getName());
	                orderItem.setProductImage(product.getImageURL());
                    orderItem.setOrderId(order.getId());
	                Price price = ordersDAO.getPriceByPriceId(orderItem.getPriceId());
	                orderItem.setPrice(price.getPrice());
	                orderItem.setDiscount(price.getDiscount());
	            }
	        }

	    } catch (DAOException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	    return totalOrders;
	}


	
	public List<OrderItems> findAllOrderItemsByOrderId(int orderId) throws ServiceException, ValidationException{
		OrdersDAO ordersDAO = new OrdersDAO();
		OrdersValidator.validateOrderId(orderId);
		List<OrderItems> orderItems  = null;
		try {
			 orderItems = ordersDAO.findAllOrderItemByOrderId(orderId);
			for (OrderItems orderItem : orderItems){
                Product product = new ProductService().findProductById(orderItem.getProductId());
                orderItem.setProductName(product.getName());
                orderItem.setProductImage(product.getImageURL());
                orderItem.setOrderId(orderItem.getOrderId());
                Price price = ordersDAO.getPriceByPriceId(orderItem.getPriceId());
                orderItem.setPrice(price.getPrice());
                orderItem.setDiscount(price.getDiscount());
            }
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return orderItems;
		
	}
	
	
	
	public List<Orders> getAllOrdersByUserId(String email) throws ServiceException, ValidationException {
	    OrdersDAO ordersDAO = new OrdersDAO();
	    List<Orders> totalOrders = new ArrayList<>();
	    OrdersValidator.validateUserEmail(email);
	    try {
	      return  totalOrders = ordersDAO.findAllOrderByUserId(email);
	        
	    } catch (DAOException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	    
	}
	
	public int getAddressIdByOrderId(int orderId) throws ServiceException {
		 OrdersDAO ordersDAO = new OrdersDAO();
		 
		 try {
			return ordersDAO.getAddressIdByOrderId(orderId);
		} catch (DAOException e) {
			e.printStackTrace();
			 throw new ServiceException(e.getMessage());
		}
		
	}
	
	public Orders findOrderIdByOrderId(int orderId) throws ServiceException {
		 OrdersDAO ordersDAO = new OrdersDAO();
		 
		 try {
			return ordersDAO.findOrderByOrderId(orderId);
		} catch (DAOException e) {
			e.printStackTrace();
			 throw new ServiceException(e.getMessage());
		}
		
	}
	
	public void cancellOrderByOrderId(int orderId) throws ServiceException {
		 OrdersDAO ordersDAO = new OrdersDAO();
		 
		 try {
			 ordersDAO.cancellOrderByOrderId(orderId);
		} catch (DAOException e) {
			e.printStackTrace();
			 throw new ServiceException(e.getMessage());
		}
		
	}
	
//	public List<Orders> getAllOrdersFromOrderTableByUserEmail(String email) throws ServiceException{
//		OrdersDAO ordersDAO = new OrdersDAO();
//		OrdersValidator.validateOrderId(orderId);
//		List<Orders> orderItems  = null;
//		
//	}
}
