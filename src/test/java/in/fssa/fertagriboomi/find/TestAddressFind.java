package in.fssa.fertagriboomi.find;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.service.DeliveryAddressService;

public class TestAddressFind {

	@Test
	public void testFindAllProducts() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		assertDoesNotThrow(() -> {
			System.out.println(deliveryAddress.getAllAddress());
		});
	}
	
	@Test
	public void testFindAllAddressWithValidUserEmail() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		assertDoesNotThrow(() -> {
			System.out.println(deliveryAddress.findAllAddressesByUserEmail("rboomibaln459@gmail.com"));
		});
	}
	
	@Test
	public void testFindAllAddressByValidUserEmail() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		assertDoesNotThrow(() -> {
			System.out.println(deliveryAddress.findAllAddressesByUserEmail("rboomibaln459@gmail.com"));
		});
	}
	
	
	@Test
	public void testFindAddressByValidAddressId() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		assertDoesNotThrow(() -> {
			System.out.println(deliveryAddress.findAddressById(3));
		});
	}

	@Test
	public void testFindAddressByInValidAddressNegaiveIdOrZero() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		Exception exception = assertThrows(Exception.class, () -> {
			deliveryAddress.findAddressById(-22);
		});
		String expectedMessage = "Invalid address Id";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	

	@Test
	public void testFindAddressByInValidAddressIdWithNonExistenId() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		Exception exception = assertThrows(Exception.class, () -> {
			deliveryAddress.findAddressById(52);
		});
		String expectedMessage = "The Address is not listed among the available Addresses";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
}
