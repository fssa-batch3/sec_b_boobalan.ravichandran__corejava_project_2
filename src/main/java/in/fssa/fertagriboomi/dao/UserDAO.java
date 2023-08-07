package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import in.fssa.fertagriboomi.interfaces.UserInterface;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class UserDAO implements UserInterface {

	@Override
	public void create(User newUser) throws RuntimeException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO users (name, email, password) VALUES (?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(2, newUser.getName());
			ps.setString(3, newUser.getEmail());
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
	public void update(int id, User updatedUser) throws RuntimeException {
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
			throw new RuntimeException();
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
				user.setPassword(rs.getString("mobile_number"));
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

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
