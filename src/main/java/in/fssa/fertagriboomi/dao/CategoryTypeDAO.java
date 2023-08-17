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

	@Override
	public List<CategoryType> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		List<CategoryType> categoryTypeArray = new ArrayList<CategoryType>();
		ResultSet rs = null;

		try {
			String query = "select * from categories_type where is_active = 1";
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
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return categoryTypeArray;
	}

	@Override
	public CategoryType findById(int categoryTypeId) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		CategoryType categoryType = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * From categories_type WHERE is_active=1 AND id=?";
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

	public void isCategoryIdExists(int newId) throws DAOException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			String query = "SELECT * From categories_type WHERE is_active=1 AND id=?";
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
