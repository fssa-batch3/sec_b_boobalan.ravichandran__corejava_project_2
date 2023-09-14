package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.DeliveryAddresses;
import in.fssa.fertagriboomi.service.DeliveryAddressService;

public class TestCreateAddress {
	@Test
	public void testCreateAddresstWithValideInput() {
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
		assertDoesNotThrow(() -> {
			deliveryAddress.createDeliveryAddress(address);

		});

	}

	@Test
	public void testCreateProductWithInvalidInput() {
		DeliveryAddressService deliveryAddress = new DeliveryAddressService();
		Exception exception = assertThrows(Exception.class, () -> {
			deliveryAddress.createDeliveryAddress(null);
		});
		String expectedMessage = "Invalid Address input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateAddresstWithAlreadyCreatedAddress() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "This address details is already exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressTitletWithEmptyString() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Address Title cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressTitletWithNull() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Address Title cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressStreetNameWithEmptyString() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Street Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressStreetNameWithNull() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Street Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstLocationWithEmptyString() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Location cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstLocationWithNull() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Location cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressCityWithEmptyString() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Please Select Your Delivery City";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressCityWithNull() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Please Select Your Delivery City";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressPersonNameWithEmptyString() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Please Enter Your Name in Specific field";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressPersonNameWithNull() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Please Enter Your Name in Specific field";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstStateWithEMptyString() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Please Select Your Delivery State";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstStateWithNull() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Please Select Your Delivery State";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressPincodeWithInvalidPinCode() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Invalid Pincode. This pincode is not available for delivery.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstWithInvalidMobileNumber() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Invalid phone number. The phone number must be in the range of 6000000001 to 9999999999.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstUserEmailWithEmptyString() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "User Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstUserEmailWithNull() {
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
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "User Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

}
