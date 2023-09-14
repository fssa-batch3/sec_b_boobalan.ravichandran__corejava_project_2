package in.fssa.fertagriboomi.service;

import java.util.List;

import in.fssa.fertagriboomi.dao.DeliveryAddressDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.DeliveryAddresses;
import in.fssa.fertagriboomi.validator.AddressValidator;

public class DeliveryAddressService {

	public void createDeliveryAddress(DeliveryAddresses newAddress) throws ValidationException, ServiceException {

		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
		AddressValidator.validateCreate(newAddress);
		try {
			deliveryAddressDAO.create(newAddress);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());

		}

	}

	public List<DeliveryAddresses> getAllAddress() {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
		return deliveryAddressDAO.findAll();
	}

	public List<DeliveryAddresses> findAllAddressesByUserEmail(String userEmail)
			throws ValidationException, ServiceException {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
		AddressValidator.validateEmail(userEmail);
		try {
			return deliveryAddressDAO.listAllAddressesByUserUniqueId(userEmail);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	public DeliveryAddresses findAddressById(int addressId) throws ValidationException, ServiceException {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
		AddressValidator.validateId(addressId);
		try {
			return deliveryAddressDAO.findById(addressId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateAddress(int addressId, DeliveryAddresses address) throws ValidationException, ServiceException {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
		AddressValidator.validateUpdate(addressId, address);
		try {
			deliveryAddressDAO.update(addressId);
			deliveryAddressDAO.create(address);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteAddress(int addressId) throws ValidationException, ServiceException {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
		AddressValidator.validateDelete(addressId);
		try {
			deliveryAddressDAO.update(addressId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
