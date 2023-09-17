package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.model.RatingsAndReviews;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class RatingsAndReviewsDAO {
	public boolean findOrderItemByOrderItemId(int orderId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT id FROM order_items WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
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

	public boolean findProductByProductId(int productId) throws DAOException {
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
			ConnectionUtil.close(conn, ps, rs); 
		}
	
	}

	public void create(RatingsAndReviews ratings) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO reviews (order_item_id, product_id, customer_name, ratings, review_message) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
		    ps.setInt(1, ratings.getOrderItemId());
		    ps.setInt(2, ratings.getProductId());
			ps.setString(3, ratings.getUserName());
			ps.setInt(4, ratings.getRatings());
			ps.setString(5, ratings.getReviews());
			ps.executeUpdate();
			
			System.out.println("Rating and Reviews has been created succesfully");
	       }catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new DAOException(e);
			} finally {
				ConnectionUtil.close(conn, ps);
			}
	}

	public List<RatingsAndReviews> findAll() throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		List<RatingsAndReviews> reviewsArray = new ArrayList<RatingsAndReviews>();
		ResultSet rs = null;
		try {
			String query = "SELECT id, order_item_id, customer_name, product_id, ratings, review_message FROM reviews ";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				RatingsAndReviews reviews = new RatingsAndReviews();
				
				  reviews.setId(rs.getInt("id"));
				  reviews.setOrderItemId(rs.getInt("order_item_id"));
				  reviews.setProductId(rs.getInt("product_id"));
				  reviews.setUserName(rs.getString("customer_name"));
				  reviews.setRatings(rs.getInt("ratings"));
				  reviews.setReviews(rs.getString("review_message"));
		
				reviewsArray.add(reviews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return reviewsArray;
	}

	public List<RatingsAndReviews> findAllReviewsByProductId(int productId) throws DAOException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		List<RatingsAndReviews> reviewsArray = new ArrayList<RatingsAndReviews>();
		ResultSet rs = null;
		try {
			String query = "SELECT id, order_item_id, customer_name, product_id, ratings, review_message FROM reviews WHERE product_id=? ";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId); 
			rs = ps.executeQuery();
			while (rs.next()) {
				RatingsAndReviews reviews = new RatingsAndReviews();
				
				  reviews.setId(rs.getInt("id"));
				  reviews.setOrderItemId(rs.getInt("order_item_id"));
				  reviews.setProductId(rs.getInt("product_id"));
				  reviews.setUserName(rs.getString("customer_name"));
				  reviews.setRatings(rs.getInt("ratings"));
				  reviews.setReviews(rs.getString("review_message"));
		
				reviewsArray.add(reviews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return reviewsArray;
	}

	public boolean findReviewByOrderItemId(int orderItemId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT order_item_id FROM reviews WHERE order_item_id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderItemId); 
			rs = ps.executeQuery();
			return rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		
		
	}
		
}
