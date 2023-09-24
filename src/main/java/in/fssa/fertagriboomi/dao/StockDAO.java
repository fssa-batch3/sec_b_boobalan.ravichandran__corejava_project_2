package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.model.OrderItems;
import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.model.Stocks;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class StockDAO {

	public void create(Stocks newStock) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO stocks (product_id,total_stock) VALUES (?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newStock.getProductId());
			ps.setInt(2, newStock.getStockCount());
			
			ps.executeUpdate();
			System.out.println("Stock created succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
		
	}

	public int findStockIdByProductId(int productId) throws DAOException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int stockId = -1; 

	    try {
	        String query = "SELECT id FROM stocks WHERE product_id = ? AND status = 1";
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, productId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            stockId = rs.getInt("id");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new DAOException(e);
	    } finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }

	    return stockId;
	}


	public void update(int stockId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE stocks SET status=? WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, stockId);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Stock with ID " + stockId + " has been updated successfully.");
			} else {
				System.out.println("Stock with ID " + stockId + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
		
		
	}

	
	public Stocks getStockByProductId(int productId) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Stocks stock = null;
		try {
			String query = "SELECT id, product_id, total_stock, status FROM stocks WHERE product_id = ? AND status=1";
			
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("This product stock is not available");
			}
			stock = new Stocks();
			stock.setId(rs.getInt("id"));
			stock.setProductId(rs.getInt("product_id"));
			stock.setStockCount(rs.getInt("total_stock"));
			stock.setStatus(rs.getBoolean("status"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

		return stock;
	}
	
	public List<OrderItems> getQuantitiesByProductId(int productId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		List<OrderItems> ordersArray = new ArrayList<OrderItems>();
		ResultSet rs = null;
		try {
			String query = "SELECT id, product_id, quantity FROM order_items WHERE product_id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderItems order = new OrderItems();
				order.setId(rs.getInt("id"));
				order.setProductId(rs.getInt("product_id"));
				order.setQuantity(rs.getInt("quantity"));
				ordersArray.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return ordersArray;
	}
}
