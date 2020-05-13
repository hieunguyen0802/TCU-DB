package edu.tcu.cs.JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.tcu.cs.domain.Employee;

/**
 * In this example, we demo how to convert ResultSet to JavaBeans First, create
 * a class Employee under domain package Then, use a while loop to populate the
 * list of employees As you can see, it is pretty tedious to convert table to
 * objects. In next lecture, we will introduce something simply to use.
 * 
 * @author bingyang
 *
 */
public class SelectStatementDemo2 {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// Step 1: register MySQL JDBC driver, this is optional now.
			Class.forName("com.mysql.cj.jdbc.Driver");// Class.forname() method dynamically loads the driver class into
														// RAM

			// Step 2: get a database connection
			String url = "jdbc:mysql://172.16.197.139/Company?serverTimezone=UTC";
			// 172.16.197.139 is the IP of my database server, yours will be different, use
			// localhost if you want to access
			// a DB installed locally.
			// Company is the name of the DB
			// ?serverTimezone=UTC is required, otherwise you will get an exception.

			String user = "bingyang";
			String password = "ABc123456$!";

//			conn = DriverManager.getConnection("jdbc:mysql://172.16.197.139/Company?serverTimezone=UTC", "bingyang",
//					"ABc123456$!");
			conn = DriverManager.getConnection(url, user, password);

			// Step 3: create a statement from connection
			Statement stmt = conn.createStatement();// Statement interface contains the essential methods for executing
													// SQL commands

			// Step 4: execute SQL statement
			String sql = "select * from employee";
			ResultSet rs = stmt.executeQuery(sql);

			// Step 5: process the result if step 4 returns anything
			List<Employee> list = new ArrayList<Employee>();
			while (rs.next()) {
				Employee e = new Employee();
				e.setFname(rs.getString("fname"));
				e.setMint(rs.getString("minit"));
				e.setSsn(rs.getString("ssn"));
				e.setBdate(rs.getDate("bdate"));
				e.setAddress(rs.getString("address"));
				e.setSex(rs.getString("sex"));
				e.setSalary(rs.getDouble("salary"));
				e.setSuperssn(rs.getString("superssn"));
				e.setDno(rs.getInt("dno"));
				list.add(e);
			}

			for (Employee employee : list) {
				System.out.println(employee);
			}

			// Step 6: release resources when done
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
