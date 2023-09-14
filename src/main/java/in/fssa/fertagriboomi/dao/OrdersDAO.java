package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.model.OrderItems;
import in.fssa.fertagriboomi.model.Orders;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class OrdersDAO {

	public int create(Orders order) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int productId = 0;

		try {

			String query = "INSERT INTO orders (address_id, user_email) VALUES (?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getAddressId());
			ps.setString(2, order.getUserEmail());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				productId = rs.getInt(1);
			}
			System.out.println("Order has been created succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
		return productId;
	}

	public boolean isPriceIdExists(int priceId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id  FROM prices WHERE id=? AND end_date is NULL";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, priceId);
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

	public boolean isProductExists(int productId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM products WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
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

	public boolean isAddressExists(int addressId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM addresses WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
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

//	public List<OrderItems> findAll() throws DAOException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		List<OrderItems> ordersArray = new ArrayList<OrderItems>();
//		ResultSet rs = null;
//		try {
//			String query = "SELECT id, price_id, status, address_id, product_id, quantity, ordered_date, delivery_date, user_email FROM orders";
//			conn = ConnectionUtil.getConnection();
//			ps = conn.prepareStatement(query);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				OrderItems order = new OrderItems();
//				order.setId(rs.getInt("id"));
//				order.setPriceId(rs.getInt("price_id"));
//				order.setStatus(rs.getBoolean("status"));
//				order.setAddressId(rs.getInt("address_id"));
//				order.setProductId(rs.getInt("product_id"));
//				order.setQuantity(rs.getInt("quantity"));
//				order.setOrderDate(rs.getTimestamp("ordered_date"));
//				order.setDeliveryDate(rs.getTimestamp("delivery_date"));
//				order.setUserEmail(rs.getString("user_email"));
//
//				ordersArray.add(order);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			throw new DAOException(e);
//		} finally {
//			ConnectionUtil.close(conn, ps, rs);
//		}
//		return ordersArray;
//	}

//	public List<OrderItems> findAllOrderByUserEmail(String userEmail) throws DAOException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		List<OrderItems> ordersArray = new ArrayList<OrderItems>();
//		ResultSet rs = null;
//		try {
//			String query = "SELECT id, price_id, status, address_id, product_id, quantity, ordered_date, delivery_date, user_email FROM orders WHERE user_email=?";
//			conn = ConnectionUtil.getConnection();
//			ps = conn.prepareStatement(query);
//			ps.setString(1, userEmail);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				OrderItems order = new OrderItems();
//				order.setId(rs.getInt("id"));
//				order.setPriceId(rs.getInt("price_id"));
//				order.setStatus(rs.getBoolean("status"));
//				order.setAddressId(rs.getInt("address_id"));
//				order.setProductId(rs.getInt("product_id"));
//				order.setQuantity(rs.getInt("quantity"));
//				order.setOrderDate(rs.getTimestamp("ordered_date"));
//				order.setDeliveryDate(rs.getTimestamp("delivery_date"));
//				order.setUserEmail(rs.getString("user_email"));
//
//				ordersArray.add(order);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			throw new DAOException(e);
//		} finally {
//			ConnectionUtil.close(conn, ps, rs);
//		}
//		return ordersArray;
//	}

//	public OrderItems findAllOrderByOrderId(int orderId) throws DAOException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		OrderItems order = null;
//		ResultSet rs = null;
//		try {
//			String query = "SELECT id, price_id, status, address_id, product_id, quantity, ordered_date, delivery_date, user_email FROM orders WHERE id=?";
//			conn = ConnectionUtil.getConnection();
//			ps = conn.prepareStatement(query);
//			ps.setInt(1, orderId);
//			rs = ps.executeQuery();
//
//			if (rs.next()) {
//				order = new OrderItems();
//				order.setId(rs.getInt("id"));
//				order.setPriceId(rs.getInt("price_id"));
//				order.setStatus(rs.getBoolean("status"));
//				order.setAddressId(rs.getInt("address_id"));
//				order.setProductId(rs.getInt("product_id"));
//				order.setQuantity(rs.getInt("quantity"));
//				order.setOrderDate(rs.getTimestamp("ordered_date"));
//				order.setDeliveryDate(rs.getTimestamp("delivery_date"));
//				order.setUserEmail(rs.getString("user_email"));
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			throw new DAOException(e);
//		} finally {
//			ConnectionUtil.close(conn, ps, rs);
//		}
//		return order;
//	}

	public void createOrderItem(int orderId, OrderItems orderItems) throws DAOException {
	
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {

			String query = "INSERT INTO order_items (order_id, price_id, product_id, quantity, ordered_date, delivery_date) VALUES (?,?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setInt(2, orderItems.getPriceId());
			ps.setInt(3, orderItems.getProductId() );
			ps.setInt(4, orderItems.getQuantity());
			ps.setTimestamp(5, orderItems.getOrderDate());
			ps.setTimestamp(6, orderItems.getDeliveryDate());
			ps.executeUpdate();
			
			System.out.println("Order Items has been created succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

}
