package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.interfaces.PriceInterface;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class PriceDAO implements PriceInterface {

	/**
	 * Creates a new price entry for a product in the database.
	 *
	 * @param productId The ID of the product for which the price is being created.
	 * @param newPrice  The Price object representing the new price.
	 * @param dateTime  The timestamp representing the start date of the new price.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
	@Override
	public void create(int productId, Price newPrice, Timestamp dateTime) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO prices (price, product_id, start_date) VALUES (?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newPrice.getPrice());
			ps.setInt(2, productId);
			ps.setTimestamp(3, dateTime);
			ps.executeUpdate();
			System.out.println("Price created succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Checks if a product with the given ID exists in the database.
	 *
	 * @param productId The ID of the product to check.
	 * @throws DAOException     if the product is not available in the product list.
	 * @throws RuntimeException if a database access error occurs.
	 */
	public void isProductExists(int productId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM products where id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("Product not available in the product list");
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
	 * 
	 * @param productId
	 * @return
	 * @throws DAOException
	 */
	public int getPriceIdByProductId(int productId) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int priceId = 0;
		try {
			String query = "SELECT * FROM prices where product_id=? AND end_date is NULL";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("This product price is not available");
			}
			priceId = rs.getInt("id");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

		return priceId;
	}

	/**
	 * 
	 * @param priceId
	 * @param dateTime
	 * @throws DAOException
	 */
	public void updateProductPrice(int priceId, Timestamp dateTime) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE prices SET end_date=? WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setTimestamp(1, dateTime);
			ps.setInt(2, priceId);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Price with ID " + priceId + " has been updated successfully.");
			} else {
				System.out.println("Price with ID " + priceId + " not found.");
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
	 * 
	 * @param productId
	 * @param newPrice
	 * @throws DAOException
	 */
	public void isPriceAlreadyExists(int productId, Price newPrice) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM prices WHERE product_id = ? AND price = ? AND end_date IS NULL";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, newPrice.getPrice());
			rs = ps.executeQuery();
			if (rs.next()) {
				throw new DAOException("Product price should be same");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		}
	}

}
