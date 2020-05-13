package edu.tcu.cs.JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Demo how to insert using JDBC
 * @author bingyang
 *
 */
public class InsertStatementDemo {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// Step 1: register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Step 2: get a database connection
			conn = DriverManager.getConnection("jdbc:mysql://172.16.197.139/Company?serverTimezone=UTC", "bingyang",
					"ABc123456$!");

			// Step 3: create a statement from connection
			Statement stmt = conn.createStatement();

			// Step 4: execute SQL statement
			String sql = "insert into employee"
						+"(fname,minit,lname,ssn,bdate,address,sex,salary,superssn,dno)"
						+ "values('Kobe','D','Bryant','928745638','1983-10-10','123 Peachtree, LA, CA','M',85000.00,NULL,NULL)";
			int result = stmt.executeUpdate(sql);

			// Step 5: process the result if step 4 returns anything
			System.out.println(result);
			// Step 6: release resources when done
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
