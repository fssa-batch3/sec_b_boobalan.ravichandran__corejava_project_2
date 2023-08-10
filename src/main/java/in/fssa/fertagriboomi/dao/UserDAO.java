package in.fssa.fertagriboomi.dao;

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

	@Override
	public void create(User newUser) throws RuntimeException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO users (name, email,mobile_number, password) VALUES (?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			ps.setLong(3, newUser.getPhoneNumber());
			ps.setString(4, newUser.getPassword());
			ps.executeUpdate();

			System.out.println("User has been created succesfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

	@Override
	public void update(int id, User updatedUser) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET name=?, password=? , mobile_number=?  WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			ps.setString(1, updatedUser.getName());
			ps.setString(2, updatedUser.getPassword());
			ps.setLong(3, updatedUser.getPhoneNumber());
			ps.setInt(4, id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("User with ID " + id + " has been updated successfully.");
			} else {
				System.out.println("User with ID " + id + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new Exception();
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	@Override
	public List<User> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		List<User> userArray = new ArrayList<User>();
		ResultSet rs = null;

		try {
			String query = "select * from users where is_active = 1";

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

	public void isEmailAlreadyExists(String email) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM users where email=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				throw new DAOException("The email already exists");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

	}

	public void isValidUserId(int id) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExists = true;
		try {
			String query = "SELECT * FROM users where id=?";
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

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
