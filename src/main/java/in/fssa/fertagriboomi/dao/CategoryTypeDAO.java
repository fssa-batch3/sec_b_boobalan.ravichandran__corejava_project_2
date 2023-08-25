package in.fssa.fertagriboomi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.interfaces.CategoryTypeInterface;
import in.fssa.fertagriboomi.model.CategoryType;
import in.fssa.fertagriboomi.util.ConnectionUtil;

public class CategoryTypeDAO implements CategoryTypeInterface {


    /**
     * Retrieves a list of all active category types from the database.
     *
     * @return List of CategoryType objects representing all active category types.
     * @throws DAOException if an error occurs while accessing the database.
     * @throws RuntimeException if a database access error occurs.
     */
	@Override
	public List<CategoryType> findAll() throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		List<CategoryType> categoryTypeArray = new ArrayList<CategoryType>();
		ResultSet rs = null;

		try {
			String query = "SELECT id, name, is_active FROM category_types WHERE is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryType category = new CategoryType();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setActive(rs.getBoolean("is_active"));
				categoryTypeArray.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return categoryTypeArray;
	}

	/**
     * Retrieves a category type by its ID from the database.
     *
     * @param categoryTypeId The ID of the category type to retrieve.
     * @return The CategoryType object corresponding to the given ID.
     * @throws DAOException if the category type is not found in the database.
     * @throws RuntimeException if a database access error occurs.
     */
	@Override
	public CategoryType findById(int categoryTypeId) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		CategoryType categoryType = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id, name, is_active FROM category_types WHERE is_active=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, categoryTypeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				categoryType = new CategoryType();
				categoryType.setId(rs.getInt("id"));
				categoryType.setName(rs.getString("name"));
				categoryType.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return categoryType;
	}


    /**
     * Checks if a given category ID exists in the database.
     *
     * @param newId The ID of the category to check.
     * @throws DAOException if an error occurs while accessing the database.
     * @throws ValidationException if the category type is not available.
     * @throws RuntimeException if a database access error occurs.
     */
	public void isCategoryIdExists(int newId) throws DAOException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			String query = "SELECT id FROM category_types WHERE is_active = 1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newId);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Category type not available");
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
