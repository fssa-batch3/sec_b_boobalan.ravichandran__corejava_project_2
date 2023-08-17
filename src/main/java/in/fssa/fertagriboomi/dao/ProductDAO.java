package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.fertagriboomi.exception.DAOException;

import in.fssa.fertagriboomi.interfaces.ProductInterface;
import in.fssa.fertagriboomi.model.Category;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class ProductDAO implements ProductInterface {

	@Override
	public List<Product> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		List<Product> categoryArray = new ArrayList<Product>();
		ResultSet rs = null;
		try {
			String query = "select * from products where is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setActive(rs.getBoolean("is_active"));
				product.setProduct_weight(rs.getString("product_weight"));
				product.setDescription(rs.getString("description"));
				product.setBenefits(rs.getString("benefits"));
				product.setApplication(rs.getString("application"));
				product.setManufacture(rs.getString("manufacture"));
				product.setCategory_id(rs.getInt("category_id"));
				categoryArray.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return categoryArray;
	}

	@Override
	public int create(Product newProduct) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int productId = 0;
		try {
			String query = "INSERT INTO products (name, product_weight, description, benefits, application, manufacture, category_id) VALUES (?,?,?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getProduct_weight());
			ps.setString(3, newProduct.getDescription());
			ps.setString(4, newProduct.getBenefits());
			ps.setString(5, newProduct.getApplication());
			ps.setString(6, newProduct.getManufacture());
			ps.setInt(7, newProduct.getCategory_id());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				productId = rs.getInt(1);
			}
			System.out.println("Product has been created succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

		return productId;

	}

	public boolean isProductAlreadyExists(String name) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM products where is_active = true AND name=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
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
	public Product findById(int id) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;
		try {
			String query = "SELECT * FROM products where id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("The product is not listed among the available products");
			}

			product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setActive(rs.getBoolean("is_active"));
			product.setProduct_weight(rs.getString("product_weight"));
			product.setDescription(rs.getString("description"));
			product.setBenefits(rs.getString("benefits"));
			product.setApplication(rs.getString("application"));
			product.setManufacture(rs.getString("manufacture"));
			product.setCategory_id(rs.getInt("category_id"));

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs); // Close the ResultSet here
		}
		return product;
	}

	@Override
	public void update(int id, Product updateProduct) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE products SET product_weight=?, description=?, benefits=?, application=? WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, updateProduct.getProduct_weight());
			ps.setString(2, updateProduct.getDescription());
			ps.setString(3, updateProduct.getBenefits());
			ps.setString(4, updateProduct.getApplication());
			ps.setInt(5, id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Product with ID " + id + " has been updated successfully.");
			} else {
				System.out.println("Product with ID " + id + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

	@Override
	public void delete(int id) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE products SET is_active=? WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Product with ID " + id + " has been deleted successfully.");
			} else {
				System.out.println("Product with ID " + id + " not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

	public boolean isCategoryExists(int id) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * From categories WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
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

	public List<Product> listAllTheProductsByCategoryId(int categoryId) throws DAOException {

		List<Product> ProductList = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * From products WHERE is_active=1 AND category_id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("Invalid Category id");
			}
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setActive(rs.getBoolean("is_active"));
				product.setProduct_weight(rs.getString("product_weight"));
				product.setDescription(rs.getString("description"));
				product.setBenefits(rs.getString("benefits"));
				product.setApplication(rs.getString("application"));
				product.setManufacture(rs.getString("manufacture"));
				product.setCategory_id(rs.getInt("category_id"));
				ProductList.add(product);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}

		return ProductList;

	}
	
	public void isDeletedProduct(int productId) throws DAOException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		String query = "SELECT * From products WHERE is_active=0 AND id=?";
		conn = ConnectionUtil.getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, productId);
		rs = ps.executeQuery();
		if (rs.next()) {
			throw new DAOException("This product has already been removed");
		}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
	}

}
