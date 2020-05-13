package injection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * Using Statement and String concatenation for sql query is dangerous
 * In this example, we demo SQL Injection.
 * Run this program, and search for John Smith
 * Run this program again, for fname, type ' OR '1'='1'#
 * and see what happens.
 * @author bingyang
 *
 */
public class SQLInjectionDemo {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		System.out.println("Welcome to my database, we can select by employee first name and last name:");
		System.out.println("Please input the first name:");
		// ' OR '1'='1'# will cause injection
		String fname = s.nextLine();
		System.out.println("Please input the last name:");
		String lname = s.nextLine();
		System.out.println("Retrieving the result......");
		findByFirstNameAndLastName(fname, lname);
	}

	public static void findByFirstNameAndLastName(String fname, String lname) {
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
			String sql = "select * from employee where fname= '" + fname + "' and lname='" + lname + "'";
			ResultSet rs = stmt.executeQuery(sql);

			// Step 5: process the result if step 4 returns anything
			while (rs.next()) {
				System.out.println(rs.getString("fname") + ", " + rs.getString("lname") + ", " + rs.getString("ssn"));
			}

			// Step 6: release resources when done
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
