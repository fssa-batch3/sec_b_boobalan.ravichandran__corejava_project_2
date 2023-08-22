package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.UserDAO;
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
	public List<User> getAll() {
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
	 * @throws Exception If an error occurs while interacting with the database or
	 *                   validating user data.
	 */
	public void create(User newUser) throws Exception {
		UserDAO userDao = new UserDAO();
		UserValidator.validate(newUser);
		userDao.create(newUser);
	}

	/**
	 * Updates a user by their ID.
	 *
	 * @param id        The ID of the user to update.
	 * @param newUpdate The updated user object.
	 * @throws Exception If an error occurs while interacting with the database or
	 *                   validating user data.
	 */
	public void update(int id, User newUpdate) throws Exception {
		UserDAO userDAO = new UserDAO();
		UserValidator.validateUpdate(newUpdate, id);
		userDAO.update(id, newUpdate);
	}
}
