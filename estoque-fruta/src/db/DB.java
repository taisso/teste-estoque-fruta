package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	private static Properties carregaProperties() {

		try (FileInputStream fs = new FileInputStream("db.properties")) {

			Properties ps = new Properties();
			ps.load(fs);
			return ps;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}

	}

	public static Connection getConnection() {

		if (conn == null) {

			Properties prop = carregaProperties();
			String url = prop.getProperty("dburl");

			try {

				conn = DriverManager.getConnection(url, prop);

			} catch (SQLException e) {

				throw new DbException(e.getMessage());
			}

		}

		return conn;
	}

	public static void fechaConnection() {

		if (conn != null) {
			try {
				conn.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

	public static void fechaStatement(Statement st) {

		if (st != null) {

			try {

				st.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}

	}

	public static void fechaResultSet(ResultSet rs) {

		if (rs != null) {

			try {

				rs.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}

	}
}
