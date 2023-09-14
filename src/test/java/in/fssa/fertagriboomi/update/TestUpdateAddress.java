package in.fssa.fertagriboomi.update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.DeliveryAddresses;
import in.fssa.fertagriboomi.service.DeliveryAddressService;

public class TestUpdateAddress {
	@Test
	public void testUpdateAddresstWithValideInput() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("Manaparai");
		address.setCity("Trichy");
		address.setPincode(620041);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		assertDoesNotThrow(() -> {
			deliveryAddress.updateAddress(1, address);

		});

	}

	@Test
	public void testUpdateProductWithInvalidInput() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		Exception exception = assertThrows(Exception.class, () -> {
			deliveryAddress.updateAddress(1, null);
		});
		String expectedMessage = "Invalid Address input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateAddresstWithAlreadyCreatedAddress() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "This address details is already exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressTitletWithEmptyString() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);
		});

		String exceptedMessage = "Address Title cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressTitletWithNull() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle(null);
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Address Title cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressStreetNameWithEmptyString() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Street Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressStreetNameWithNull() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName(null);
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Street Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddresstLocationWithEmptyString() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Location cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddresstLocationWithNull() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation(null);
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Location cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressCityWithEmptyString() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Please Select Your Delivery City";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressCityWithNull() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity(null);
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Please Select Your Delivery City";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressPersonNameWithEmptyString() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Please Enter Your Name in Specific field";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressPersonNameWithNull() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName(null);
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);
		});

		String exceptedMessage = "Please Enter Your Name in Specific field";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddresstStateWithEMptyString() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Please Select Your Delivery State";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddresstStateWithNull() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState(null);
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Please Select Your Delivery State";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddressPincodeWithInvalidPinCode() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(520001);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Invalid Pincode. This pincode is not available for delivery.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddresstWithInvalidMobileNumber() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(448788732);
		// address.setActive(true);
		address.setUserEmail("rboomibaln459@gmail.com");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "Invalid phone number. The phone number must be in the range of 6000000001 to 9999999999.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddresstUserEmailWithEmptyString() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "User Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateAddresstUserEmailWithNull() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setAddressTitle("Home");
		address.setStreetName("kudi street");
		address.setLocation("thirunedungulam");
		address.setCity("Trichy");
		address.setPincode(620015);
		address.setPersonName("Ragunath");
		address.setState("Tamil nadu");
		address.setMobileNumber(9878987898l);
		// address.setActive(true);
		address.setUserEmail(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.updateAddress(2, address);

		});

		String exceptedMessage = "User Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

}
