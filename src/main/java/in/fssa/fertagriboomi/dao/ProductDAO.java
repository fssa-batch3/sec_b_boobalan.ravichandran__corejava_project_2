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

	/**
	 * Retrieves a list of all active products from the database.
	 *
	 * @return A list of Product objects representing all active products.
	 * @throws RuntimeException if a database access error occurs.
	 */
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

	/**
	 * Creates a new product entry in the database.
	 *
	 * @param newProduct The Product object representing the new product.
	 * @return The ID of the newly created product.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
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

	/**
	 * Checks if a product with the given name already exists in the database.
	 *
	 * @param name The name of the product to check.
	 * @return true if the product with the given name already exists, false
	 *         otherwise.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
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

	/**
	 * Retrieves a product by its ID from the database.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return The Product object representing the retrieved product.
	 * @throws DAOException     if the product is not listed among the available
	 *                          products.
	 * @throws RuntimeException if a database access error occurs.
	 */
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

	/**
	 * Updates a product's information in the database.
	 *
	 * @param id            The ID of the product to update.
	 * @param updateProduct The Product object representing the updated product
	 *                      information.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
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

	/**
	 * Deletes a product from the database.
	 *
	 * @param id The ID of the product to delete.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
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

	/**
	 * Checks if a category with the given ID exists in the database.
	 *
	 * @param id The ID of the category to check.
	 * @return true if the category with the given ID exists, false otherwise.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
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

	/**
	 * Lists all products belonging to a specific category.
	 *
	 * @param categoryId The ID of the category to filter products by.
	 * @return A list of Product objects belonging to the specified category.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
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

	/**
	 * Checks if a product with the given ID has been deleted (is_active=0) in the
	 * database.
	 *
	 * @param productId The ID of the product to check.
	 * @throws DAOException     if the product has already been removed.
	 * @throws RuntimeException if a database access error occurs.
	 */
	public boolean isDeletedProduct(int productId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * From products WHERE is_active=0 AND id=?";
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

	/**
	 * Changes the active status of a product with the given ID to "active".
	 *
	 * @param id The ID of the product to be marked as active.
	 * @throws DAOException If an error occurs while interacting with the database
	 *                      or if the product with the specified ID is not found.
	 */
	public void changeActive(int id) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE products SET is_active=? WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, true);
			ps.setInt(2, id);
			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Product with ID " + id + " has been isActive updated successfully.");
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

}
