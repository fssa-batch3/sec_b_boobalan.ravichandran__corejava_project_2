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

		// if we use local use this
		url = System.getenv("DATABASE_HOSTNAME");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

//			Dotenv env = Dotenv.load();
//			url = env.get("DATABASE_HOSTNAME");
//			userName = env.get("DATABASE_USERNAME");
//			passWord = env.get("DATABASE_PASSWORD");

		// if we use cloud use this
//			url = "jdbc:mysql://164.52.216.41:3306/boobalan_ravichandran_corejava_project";
//			userName = "vcMmMZBgHbzR";
//			passWord = "ccf7e6b8-34a3-47b3-ad19-ab83f8512e3f";

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
