package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.model.OrderItems;
import in.fssa.fertagriboomi.model.Orders;
import in.fssa.fertagriboomi.service.OrdersService;

public class TestCreateOrders {
	@Test
	public void testCreateOrderWithValidInput() {
		OrdersService ordersService = new OrdersService();
		Orders orders = new Orders();
		
		orders.setAddressId(2);
		orders.setUserEmail("rboomibaln459@gmail.com");
	
		
		orders.setQuantity(5);
		assertDoesNotThrow(() -> {
			int orderId = ordersService.createOrder(orders);
			OrderItems orderItem = new OrderItems();
			orderItem.setPriceId(2);
			orderItem.setProductId(2);
			orderItem.setOrderId(orderId);
			orderItem.setQuantity(2);
			ordersService.createOrderItems(orderId, orderItem);
		});
	}
}

//	@Test
//	public void testCreateOrderWithInValidInput() {
//		OrdersService ordersService = new OrdersService();
//
//		Exception exception = assertThrows(Exception.class, () -> {
//			ordersService.createOrder(null);
//		});
//		String expectedMessage = "Invalid Order Input";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(expectedMessage.equals(actualMessage));
//	}
//
//	@Test
//	public void testCreateProductWithInvalidPriceId() {
//		OrdersService ordersService = new OrdersService();
//		OrderItems orders = new OrderItems();
//		orders.setPriceId(-22);
//		orders.setProductId(1);
//		orders.setAddressId(3);
//		orders.setUserEmail("rboomibaln459@gmail.com");
//		orders.setQuantity(5);
//		Exception exception = assertThrows(Exception.class, () -> {
//			ordersService.createOrder(orders);
//		});
//
//		String expectedMessage = "Invalid Price Id";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(expectedMessage.equals(actualMessage));
//	}
//
//	@Test
//	public void testCreateProductWithINonExistenPriceId() {
//		OrdersService ordersService = new OrdersService();
//		OrderItems orders = new OrderItems();
//		orders.setPriceId(120);
//		orders.setProductId(1);
//		orders.setAddressId(3);
//		orders.setUserEmail("rboomibaln459@gmail.com");
//		orders.setQuantity(5);
//		Exception exception = assertThrows(Exception.class, () -> {
//			ordersService.createOrder(orders);
//		});
//
//		String expectedMessage = "This product price is not available";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(expectedMessage.equals(actualMessage));
//	}
//
//	@Test
//	public void testCreateProductWithInvalidProductId() {
//		OrdersService ordersService = new OrdersService();
//		OrderItems orders = new OrderItems();
//		orders.setPriceId(2);
//		orders.setProductId(-11);
//		orders.setAddressId(3);
//		orders.setUserEmail("rboomibaln459@gmail.com");
//		orders.setQuantity(5);
//		Exception exception = assertThrows(Exception.class, () -> {
//			ordersService.createOrder(orders);
//		});
//
//		String expectedMessage = "Invalid Product Id";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(expectedMessage.equals(actualMessage));
//	}
//
//	@Test
//	public void testCreateProductWithNonExistenProductId() {
//		OrdersService ordersService = new OrdersService();
//		OrderItems orders = new OrderItems();
//		orders.setPriceId(2);
//		orders.setProductId(111);
//		orders.setAddressId(3);
//		orders.setUserEmail("rboomibaln459@gmail.com");
//		orders.setQuantity(5);
//		Exception exception = assertThrows(Exception.class, () -> {
//			ordersService.createOrder(orders);
//		});
//
//		String expectedMessage = "This product is not available in the product list";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(expectedMessage.equals(actualMessage));
//	}
//
//	@Test
//	public void testCreateProductWithInvalidAddressId() {
//		OrdersService ordersService = new OrdersService();
//		OrderItems orders = new OrderItems();
//		orders.setPriceId(2);
//		orders.setProductId(1);
//		orders.setAddressId(-20);
//		orders.setUserEmail("rboomibaln459@gmail.com");
//		orders.setQuantity(5);
//		Exception exception = assertThrows(Exception.class, () -> {
//			ordersService.createOrder(orders);
//		});
//
//		String expectedMessage = "Invalid Address Id";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(expectedMessage.equals(actualMessage));
//	}
//
//	@Test
//	public void testCreateProductWithNonExistenAddressId() {
//		OrdersService ordersService = new OrdersService();
//		OrderItems orders = new OrderItems();
//		orders.setPriceId(2);
//		orders.setProductId(111);
//		orders.setAddressId(3);
//		orders.setUserEmail("rboomibaln459@gmail.com");
//		orders.setQuantity(5);
//		Exception exception = assertThrows(Exception.class, () -> {
//			ordersService.createOrder(orders);
//		});
//
//		String expectedMessage = "This address is not available in the address list";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(expectedMessage.equals(actualMessage));
//	}
//
//}
