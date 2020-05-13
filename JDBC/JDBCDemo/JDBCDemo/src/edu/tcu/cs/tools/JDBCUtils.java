package edu.tcu.cs.tools;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * JDBCUtils using external property file
 * @author bingyang
 *
 */
public class JDBCUtils {
	private static Connection con;
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;

	// Static block is used for initializing the static variables.
	// This block gets executed when the class is loaded in the memory.
	static {
		try {
			readProperty();
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception ex) {
			throw new RuntimeException(ex + " Database connection failure");
		}
	}

	/*
	 * Returns connection
	 */
	public static Connection getConnection() {
		return con;
	}

	private static void readProperty() throws IOException {
		//Load a properties file from classpath
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("database.properties");
		Properties prop = new Properties();
		prop.load(is);
		driverClass = prop.getProperty("driverClass");
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
	}

	/*
	 * close and release resources
	 */
	public static void close(Connection con, Statement stat) {
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException ex) {
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
			}
		}
	}

	public static void close(Connection con, Statement stat, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException ex) {
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
			}
		}
	}
}
