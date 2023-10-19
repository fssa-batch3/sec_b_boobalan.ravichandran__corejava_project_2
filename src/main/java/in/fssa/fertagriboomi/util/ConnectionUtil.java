package in.fssa.fertagriboomi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import io.github.cdimascio.dotenv.Dotenv;

/**
 * Utility class for managing database connections and resources.
 */
public class ConnectionUtil {

	/**
	 * Establishes a database connection based on environment variables.
	 *
	 * @return A database Connection object.
	 */
	public static Connection getConnection() {
		String url;
		String userName;
		String passWord;

		// Check if the "CI" environment variable is set
		String environment = System.getenv("CI");

		if (environment != null && environment.equalsIgnoreCase("true")) {
			// Use local database credentials
			url = System.getenv("DATABASE_HOST");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		} else {
			// Use cloud database credentials
			url = System.getenv("LOCAL_DATABASE_HOSTNAME");
			userName = System.getenv("LOCAL_DATABASE_USERNAME");
			passWord = System.getenv("LOCAL_DATABASE_PASSWORD");

//		url = System.getenv("CLOUD_DATABASE_HOSTNAME");
//		userName = System.getenv("CLOUD_DATABASE_USERNAME");
//		passWord = System.getenv("CLOUD_DATABASE_PASSWORD");
		
		}


		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}

	/**
	 * Closes the database connection and PreparedStatement.
	 *
	 * @param connection The database Connection to close.
	 * @param presta     The PreparedStatement to close.
	 */
	public static void close(Connection connection, PreparedStatement presta) {
		try {
			if (presta != null) {
				presta.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the database connection, PreparedStatement, and ResultSet.
	 *
	 * @param connection The database Connection to close.
	 * @param presta     The PreparedStatement to close.
	 * @param rs         The ResultSet to close.
	 */
	public static void close(Connection connection, PreparedStatement presta, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (presta != null) {
				presta.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
