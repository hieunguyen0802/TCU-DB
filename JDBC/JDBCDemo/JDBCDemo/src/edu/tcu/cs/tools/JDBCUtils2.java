package edu.tcu.cs.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils2 {
	private JDBCUtils2() {
	}

	private static Connection con;
	// Static block is used for initializing the static variables.
	// This block gets executed when the class is loaded in the memory.
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://172.16.197.139/Company?serverTimezone=UTC";
			String username = "bingyang";
			String password = "ABc123456$!";
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
