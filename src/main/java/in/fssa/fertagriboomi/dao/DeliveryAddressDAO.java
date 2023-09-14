package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.model.DeliveryAddresses;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class DeliveryAddressDAO {

	public void create(DeliveryAddresses newAddress) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO addresses (address_title, street_name, location, city, pincode, person_name, state, mobile_number, user_email) VALUES (?,?,?,?,?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newAddress.getAddressTitle());
			ps.setString(2, newAddress.getStreetName());
			ps.setString(3, newAddress.getLocation());
			ps.setString(4, newAddress.getCity());
			ps.setInt(5, newAddress.getPincode());
			ps.setString(6, newAddress.getPersonName());
			ps.setString(7, newAddress.getState());
			ps.setLong(8, newAddress.getMobileNumber());
			ps.setString(9, newAddress.getUserEmail());
			ps.executeUpdate();

			System.out.println("Address has been created succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	public void update(int id) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE addresses SET is_active=? WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
            ps.setBoolean(1, false);
			ps.setInt(2, id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Address with ID " + id + " has been updated successfully.");
			} else {
				System.out.println("Address with ID " + id + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	public void Delete(int addressId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE addresses SET is_active=? WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, addressId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Address with ID " + addressId + " has been deleted successfully.");
			} else {
				System.out.println("Address with ID " + addressId + " not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}
	
	
	public DeliveryAddresses findById(int id) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DeliveryAddresses address = null;
		try {
			String query = "SELECT id, address_title, street_name, location, city, pincode, person_name, state, mobile_number, user_email, is_active FROM addresses WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("The Address is not listed among the available Addresses");
			}

			address = new DeliveryAddresses();
			address.setId(rs.getInt("id"));
			address.setAddressTitle(rs.getString("address_title"));
			address.setActive(rs.getBoolean("is_active"));
			address.setStreetName(rs.getString("street_name"));
			address.setLocation(rs.getString("location"));
			address.setCity(rs.getString("city"));
			address.setPincode(rs.getInt("pincode"));
			address.setPersonName(rs.getString("person_name"));
			address.setState(rs.getString("state"));
			address.setMobileNumber(rs.getLong("mobile_number"));
			address.setUserEmail(rs.getString("user_email"));

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return address;
	}

	
	public List<DeliveryAddresses> listAllAddressesByUserUniqueId(String userEmil) throws DAOException {

		List<DeliveryAddresses> addressList = new ArrayList<DeliveryAddresses>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT id, address_title, street_name, location, city, pincode, person_name, state, mobile_number, user_email, is_active FROM addresses WHERE is_active=1 AND user_email=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, userEmil);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				DeliveryAddresses address = new DeliveryAddresses();
				
				address.setId(rs.getInt("id"));
				address.setAddressTitle(rs.getString("address_title"));
				address.setActive(rs.getBoolean("is_active"));
				address.setStreetName(rs.getString("street_name"));
				address.setLocation(rs.getString("location"));
				address.setCity(rs.getString("city"));
				address.setPincode(rs.getInt("pincode"));
				address.setPersonName(rs.getString("person_name"));
				address.setState(rs.getString("state"));
				address.setMobileNumber(rs.getLong("mobile_number"));
				address.setUserEmail(rs.getString("user_email"));
				
				addressList.add(address);

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}

		return addressList;

	}
	
	public boolean isAddressDetailsExists(DeliveryAddresses address) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id, address_title, street_name, location, city, pincode, person_name, state, mobile_number, user_email, is_active FROM addresses WHERE is_active=1 AND address_title=? AND street_name=? AND location=? AND city=? AND pincode=? AND person_name=? AND state=? AND mobile_number=? AND user_email=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, address.getAddressTitle());
			ps.setString(2, address.getStreetName());
			ps.setString(3, address.getLocation());
			ps.setString(4, address.getCity());
			ps.setInt(5, address.getPincode());
			ps.setString(6, address.getPersonName());
			ps.setString(7, address.getState());
			ps.setLong(8, address.getMobileNumber());
			ps.setString(9, address.getUserEmail());
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
	
	
	public List<DeliveryAddresses> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		List<DeliveryAddresses> addressList = new ArrayList<DeliveryAddresses>();
		ResultSet rs = null;
		try {
			String query = "SELECT id, address_title, street_name, location, city, pincode, person_name, state, mobile_number, user_email, is_active FROM addresses";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				DeliveryAddresses address = new DeliveryAddresses();

				address.setId(rs.getInt("id"));
				address.setAddressTitle(rs.getString("address_title"));
				address.setActive(rs.getBoolean("is_active"));
				address.setStreetName(rs.getString("street_name"));
				address.setLocation(rs.getString("location"));
				address.setCity(rs.getString("city"));
				address.setPincode(rs.getInt("pincode"));
				address.setPersonName(rs.getString("person_name"));
				address.setState(rs.getString("state"));
				address.setMobileNumber(rs.getLong("mobile_number"));
				address.setUserEmail(rs.getString("user_email"));
				
				addressList.add(address);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return addressList;
	}

	
}
