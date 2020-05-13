package edu.tcu.cs.JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatementDemo {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// Step 1: register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Step 2: get a database connection
			conn = DriverManager.getConnection("jdbc:mysql://172.16.197.139/Company?serverTimezone=UTC", "bingyang",
					"ABc123456$!");

			// Step 3: create a statement from connection
			String sql = "select * from employee where fname=? and salary=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, "John");
			preparedStmt.setDouble(2, 44000.00);

			// Step 4: execute SQL statement
			ResultSet rs = preparedStmt.executeQuery();// no need to provide sql here

			// Step 5: process the result if step 4 returns anything

			while (rs.next()) {
				System.out.println(rs.getString("fname") + ", " + rs.getString("lname") + ", " + rs.getString("ssn"));
			}

			// Step 6: release resources when done
			preparedStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
