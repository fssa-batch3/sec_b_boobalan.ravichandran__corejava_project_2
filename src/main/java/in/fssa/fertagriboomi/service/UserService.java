package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.UserDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.validator.UserValidator;

/**
 * Service class for managing User entities.
 */
public class UserService {

	/**
	 * Retrieves a list of all users.
	 *
	 * @return A list of User objects.
	 */
	public List<User> getAllUsers() {
		UserDAO userDao = new UserDAO();
		List<User> userList = userDao.findAll();
		Iterator<User> iterator = userList.iterator();

		while (iterator.hasNext()) {
			User user = iterator.next();
			System.out.println(user);
		}
		return userList;
	}

	/**
	 * Creates a new user.
	 *
	 * @param newUser The new user to create.
	 * @throws ValidationException
	 * @throws Exception           If an error occurs while interacting with the
	 *                             database or validating user data.
	 */
	public void createUser(User newUser) throws ServiceException, ValidationException {
		try {
			UserDAO userDao = new UserDAO();
			UserValidator.validate(newUser);
			userDao.create(newUser);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Updates a user by their ID.
	 *
	 * @param id        The ID of the user to update.
	 * @param newUpdate The updated user object.
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws Exception           If an error occurs while interacting with the
	 *                             database or validating user data.
	 */
	public void updateUser(int id, User newUpdate) throws ValidationException, ServiceException {
		try {
			UserDAO userDAO = new UserDAO();
			UserValidator.validateUpdate(newUpdate, id);
			userDAO.update(id, newUpdate);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public boolean findEmailAndPasswordExists(String userEmail, String password) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		boolean isRegister = false;

		try {
			isRegister = UserValidator.ValidateEmailAndPassword(userEmail, password);

		} catch (ValidationException e) {
			throw new ServiceException(e);
		}
		return isRegister;

	}
	
	public User findByEmail(String email) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		User user = null;
		try {
			UserValidator.ValidateEmailAddress(email);
			user = new User();
			user= userDAO.findUserByEmail(email);
		} catch (DAOException | ValidationException e) {
			
			throw new ServiceException(e);
		}
		
		return user;
		
	}
}
