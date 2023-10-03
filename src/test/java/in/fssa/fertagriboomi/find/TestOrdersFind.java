package in.fssa.fertagriboomi.find;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.OrderItems;
import in.fssa.fertagriboomi.model.Orders;
import in.fssa.fertagriboomi.service.OrdersService;
import in.fssa.fertagriboomi.service.ProductService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class TestOrdersFind {

	    @Test
	    public void testFindAllOrders() {
	        OrdersService ordersService = new OrdersService();
	       

			assertDoesNotThrow(() -> {
				List<Orders> ordersList = ordersService.getAllOrders();
				 for (Orders order : ordersList) {
				        System.out.println("Order ID: " + order.getId());


				        List<OrderItems> orderItems = order.getOrderItems();
				        System.out.println("Order Items:");
				        for (OrderItems orderItem : orderItems) {
				            System.out.println("  Order Item ID: " + orderItem.getId());
				            System.out.println("  Order ID: " + orderItem.getOrderId());
				            System.out.println("  Product ID: " + orderItem.getProductId());
				            System.out.println("  Price ID: " + orderItem.getPriceId());
				            System.out.println("  Quantity: " + orderItem.getQuantity());
				            System.out.println("  Order Date: " + orderItem.getOrderDate());
				            System.out.println("  Delivery Date: " + orderItem.getDeliveryDate());
				            System.out.println("  Product Name: " + orderItem.getProductName());
				            System.out.println("  Product Image: " + orderItem.getProductImage());
				            System.out.println("  Price: " + orderItem.getPrice());
				            System.out.println("  Discount: " + orderItem.getDiscount());
				        }

				        System.out.println();
				    }
			});
	        
	    }
	    @Test
	    public void testFindAllOrdersByUSerEmail() {
	        OrdersService ordersService = new OrdersService();

			assertDoesNotThrow(() -> {
				System.out.println(ordersService.getAllOrdersByUserEmail("rboomibaln459@gmail.com"));
			});
	        
	    }
	    
	    
	    @Test
		public void testFindAllOrdersByInvalidUSerEmail() {
	    	 OrdersService ordersService = new OrdersService();

			Exception exception = assertThrows(ValidationException.class, () -> {
				ordersService.getAllOrdersByUserEmail("rboomibjjmaln459@gmail.com");
			});

			String exceptedMessage = "User Email Cannot find";
			String actualMessage = exception.getMessage();

			assertTrue(exceptedMessage.equals(actualMessage));
		}
	    @Test
	 		public void testFindAllOrdersByEmptyStringUSerEmail() {
	 	    	 OrdersService ordersService = new OrdersService();

	 			Exception exception = assertThrows(ValidationException.class, () -> {
	 				ordersService.getAllOrdersByUserEmail("");
	 			});

	 			String exceptedMessage = "User Email Cannot be null or empty";
	 			String actualMessage = exception.getMessage();

	 			assertTrue(exceptedMessage.equals(actualMessage));
	 		}

	    @Test
 		public void testFindAllOrdersByNull() {
 	    	 OrdersService ordersService = new OrdersService();

 			Exception exception = assertThrows(ValidationException.class, () -> {
 				ordersService.getAllOrdersByUserEmail(null);
 			});

 			String exceptedMessage = "User Email Cannot be null or empty";
 			String actualMessage = exception.getMessage();

 			assertTrue(exceptedMessage.equals(actualMessage));
 		}

	    @Test
	    public void testFindAllOrdersByOrderId() {
	        OrdersService ordersService = new OrdersService();
	       

			assertDoesNotThrow(() -> {
				List<OrderItems> ordersList = ordersService.findAllOrderItemsByOrderId(7);
				
				        for (OrderItems orderItem : ordersList) {
				            System.out.println("  Order Item ID: " + orderItem.getId());
				            System.out.println("  Order ID: " + orderItem.getOrderId());
				            System.out.println("  Product ID: " + orderItem.getProductId());
				            System.out.println("  Price ID: " + orderItem.getPriceId());
				            System.out.println("  Quantity: " + orderItem.getQuantity());
				            System.out.println("  Order Date: " + orderItem.getOrderDate());
				            System.out.println("  Delivery Date: " + orderItem.getDeliveryDate());
				            System.out.println("  Product Name: " + orderItem.getProductName());
				            System.out.println("  Product Image: " + orderItem.getProductImage());
				            System.out.println("  Price: " + orderItem.getPrice());
				            System.out.println("  Discount: " + orderItem.getDiscount());
				        }

				        System.out.println();
	        
	    });
	    
}
	    
	    
	    @Test
 		public void testFindAllOrderItemsByInvalidOrderId() {
 	    	 OrdersService ordersService = new OrdersService();

 			Exception exception = assertThrows(ValidationException.class, () -> {
 				ordersService.findAllOrderItemsByOrderId(76);
 			});

 			String exceptedMessage = "This Order is not available";
 			String actualMessage = exception.getMessage();

 			assertTrue(exceptedMessage.equals(actualMessage));
 		}
	    
	    
	    @Test
 		public void testFindAllOrderItemsByInvalidNegativeOrderId() {
 	    	 OrdersService ordersService = new OrdersService();

 			Exception exception = assertThrows(ValidationException.class, () -> {
 				ordersService.findAllOrderItemsByOrderId(-76);
 			});

 			String exceptedMessage = "Invalid Order Id";
 			String actualMessage = exception.getMessage();

 			assertTrue(exceptedMessage.equals(actualMessage));
 		}
	    
	    
	    @Test
	    public void testFindAllOrdersByUserId() {
	        OrdersService ordersService = new OrdersService();

			assertDoesNotThrow(() -> {
				System.out.println(ordersService.getAllOrdersByUserId("rboomibaln459@gmail.com"));
			});
	        
	    }
	    
	    
	    @Test
	    public void testChangeDeliverDate() {
	        OrdersService ordersService = new OrdersService();

			assertDoesNotThrow(() -> {
				ordersService.changeDeliveryDate("2023-10-08",1);
			});
	        
	    }

}
