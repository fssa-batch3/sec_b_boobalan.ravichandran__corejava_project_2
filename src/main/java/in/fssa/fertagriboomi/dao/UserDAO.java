package in.fssa.fertagriboomi.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.interfaces.UserInterface;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class UserDAO implements UserInterface {

	/**
	 * Creates a new user in the database.
	 *
	 * @param newUser The User object representing the new user.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
	@Override
	public void create(User newUser) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO users (name, email,mobile_number, password) VALUES (?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			ps.setLong(3, newUser.getPhoneNumber());
			ps.setString(4, hashPassword(newUser.getPassword()).trim());
			ps.executeUpdate();

			System.out.println("User has been created succesfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

	/**
	 * Updates user information in the database.
	 *
	 * @param id          The ID of the user to update.
	 * @param updatedUser The User object representing the updated user information.
	 * @throws Exception        if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
	@Override
	public void update(int id, User updatedUser) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET name=?, mobile_number=?  WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			ps.setString(1, updatedUser.getName());
			ps.setLong(2, updatedUser.getPhoneNumber());
			ps.setInt(3, id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("User with ID " + id + " has been updated successfully.");
			} else {
				System.out.println("User with ID " + id + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Retrieves a list of all active users from the database.
	 *
	 * @return A list of User objects representing all active users.
	 * @throws RuntimeException if a database access error occurs.
	 */
	@Override
	public List<User> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		List<User> userArray = new ArrayList<User>();
		ResultSet rs = null;

		try {
			String query = "SELECT id, name, email, password, mobile_number, is_active FROM users WHERE is_active = 1";

			// String query = "select * from users";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));

				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("mobile_number"));

				user.setActive(rs.getBoolean("is_active"));
				userArray.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userArray;
	}

	/**
	 * Checks if an email address already exists in the database.
	 *
	 * @param email The email address to check for existence.
	 * @throws DAOException If an error occurs while interacting with the database
	 *                      or if the email already exists.
	 */
	public boolean isEmailAlreadyExists(String email) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT email FROM users WHERE email=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

	}

	/**
	 * Checks if a user with the given ID exists in the database.
	 *
	 * @param id The ID of the user to check for existence.
	 * @throws DAOException If an error occurs while interacting with the database
	 *                      or if the user ID is not found.
	 */
	public void isValidUserId(int id) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExists = true;
		try {
			String query = "SELECT id FROM users WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new Exception("User Id not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

	}

	public boolean findByEmail(String email) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT email FROM users WHERE email=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	public boolean findUserRegisterOrNot(String userEmail, String password) throws DAOException {

		String sql = "SELECT password FROM users WHERE email = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, userEmail.trim());

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String storedHashedPassword = rs.getString("password");
					
					return checkPassword(password, storedHashedPassword);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		}

		return false;
	}

	/**
	 * Checks if the entered password matches the stored hashed password.
	 *
	 * @param enteredPassword      The password entered by the user during login.
	 * @param storedHashedPassword The hashed password stored in the database.
	 * @return true if the entered password is correct; otherwise, false.
	 * @throws InvalidEmployeeException if an error occurs during hashing.
	 */
	public static boolean checkPassword(String enteredPassword, String storedHashedPassword) throws DAOException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] enteredHashedBytes = md.digest(enteredPassword.getBytes(StandardCharsets.UTF_8));

			// Convert the byte array to a hexadecimal string
			StringBuilder enteredHashedPassword = new StringBuilder();
			for (byte b : enteredHashedBytes) {
				enteredHashedPassword.append(String.format("%02x", b));
			}

			// Compare the entered hashed password with the stored hashed password
			return enteredHashedPassword.toString().equals(storedHashedPassword);
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	public User findUserByEmail(String email) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		User user = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id, name, email, password, mobile_number, is_active FROM users WHERE email=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("mobile_number"));
				user.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

		return user;

	}
	
	
	/**
	 * Hashes the provided password using the SHA-256 cryptographic hash function.
	 *
	 * @param password the raw password to be hashed.
	 * @return the hashed password as a hexadecimal string.
	 * @throws InvalidEmployeeException if an error occurs during hashing (e.g.,
	 *                                  NoSuchAlgorithmException).
	 */
	public static String hashPassword(String password) throws DAOException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			// Convert the byte array to a hexadecimal string
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	
	public boolean isMobileNumberAlreadyExists(long number) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT mobile_number FROM users WHERE mobile_number=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setLong(1, number);
			rs = ps.executeQuery();
			
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

	}
}
