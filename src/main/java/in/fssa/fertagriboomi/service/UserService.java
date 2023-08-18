package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.UserDAO;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.validator.UserValidator;

public class UserService {
	/**
	 * 
	 * @return
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
	 * 
	 * @param newUser
	 * @throws Exception
	 */
	public void create(User newUser) throws Exception {
		UserDAO userDao = new UserDAO();
		UserValidator.validate(newUser);
		userDao.create(newUser);
	}

	/**
	 * 
	 * @param id
	 * @param newUpdate
	 * @throws Exception
	 */
	public void update(int id, User newUpdate) throws Exception {
		UserDAO userDAO = new UserDAO();
		UserValidator.validateUpdate(newUpdate, id);
		userDAO.update(id, newUpdate);
	}

}
