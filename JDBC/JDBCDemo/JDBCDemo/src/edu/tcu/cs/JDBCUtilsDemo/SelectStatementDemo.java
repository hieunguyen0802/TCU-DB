package edu.tcu.cs.JDBCUtilsDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.tcu.cs.tools.JDBCUtils;

public class SelectStatementDemo {

	public static void main(String[] args) {
		Connection conn = null;

		try {

			conn = JDBCUtils.getConnection();

			Statement stmt = conn.createStatement();// Statement interface contains the essential methods for executing
													// SQL commands
			String sql = "select * from employee";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.printf("%-10s, %-10s, %-10s \n", rs.getString("fname"), rs.getString("lname"),
						rs.getString("ssn"));
			}

			JDBCUtils.close(conn, stmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
