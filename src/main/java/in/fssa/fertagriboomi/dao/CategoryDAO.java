package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.interfaces.CategoryInterface;
import in.fssa.fertagriboomi.model.Category;
import in.fssa.fertagriboomi.model.CategoryType;
import in.fssa.fertagriboomi.util.ConnectionUtil;

/**
 * This class provides methods for interacting with the database to perform
 * operations related to Categories.
 */
public class CategoryDAO implements CategoryInterface {

	/**
	 * Retrieves a list of all active categories from the database.
	 *
	 * @return List of Category objects representing all active categories.
	 * @throws RuntimeException if a database access error occurs.
	 */
	@Override
	public List<Category> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		List<Category> categoryArray = new ArrayList<Category>();
		ResultSet rs = null;
		try {
			String query = "select * from categories where is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setActive(rs.getBoolean("is_active"));
				category.setCategory_type_id(rs.getInt("category_type_id"));
				categoryArray.add(category);
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
	 * Retrieves a category by its ID from the database.
	 *
	 * @param id The ID of the category to retrieve.
	 * @return The Category object corresponding to the given ID.
	 * @throws DAOException     if the category is not found in the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
	@Override
	public Category findById(int id) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		Category category = null;

		ResultSet rs = null;

		try {
			String query = "SELECT * From categories WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("Category not available");
			}

			category = new Category();
			category.setId(rs.getInt("id"));
			category.setName(rs.getString("name"));
			category.setActive(rs.getBoolean("is_active"));
			category.setCategory_type_id(rs.getInt("category_type_id"));

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return category;
	}

	/**
	 * Retrieves a list of active categories belonging to a specific category type.
	 *
	 * @param typeId The ID of the category type.
	 * @return List of Category objects belonging to the specified category type.
	 * @throws DAOException     if an error occurs while accessing the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
	@Override
	public List<Category> findCategoryByCategoryTypeId(int typeId) throws DAOException {
		List<Category> categoryArray = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * From categories WHERE is_active=1 AND category_type_id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, typeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setCategory_type_id(rs.getInt("category_type_id"));
				category.setActive(rs.getBoolean("is_active"));
				categoryArray.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}

		return categoryArray;

	}

	/**
	 * Checks if a given category type ID exists in the database.
	 *
	 * @param categoryTypeId The ID of the category type to check.
	 * @throws DAOException     if the category type does not exist in the database.
	 * @throws RuntimeException if a database access error occurs.
	 */
	public void isCategoryTypeIdExists(int categoryTypeId) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * From categories_type WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, categoryTypeId);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("Category type not available");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}

	}

}
