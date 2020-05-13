package edu.tcu.cs.et.dao;
/*
 
 */

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import edu.tcu.cs.et.domain.Activity;
import edu.tcu.cs.et.tools.JDBCUtils;

public class ActivityDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/*
	 * 
	 */
	public void deleteActivity(int id) {
		try {
			// Write SQL statement with placeholder
			String sql = "DELETE FROM activity WHERE id=?";
			qr.update(sql, id);
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Delete Exception!");
		}
	}

	/*
	 * 
	 */
	public void editActivity(Activity act) {
		try {
			// Write SQL statement with placeholder
			String sql = "UPDATE activity SET name=?,money=?,account=?,createtime=?,description=? WHERE id=?";
			//
			Object[] params = {act.getName(), act.getMoney(), act.getAccount(), act.getCreatetime(),
					act.getDescription(), act.getId() };
			//
			qr.update(sql, params);
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Edit Exception!");
		}
	}

	/*
	 * 
	 */
	public void addActivity(Activity act) {
		try {
			// Write SQL statement with placeholder
			String sql = "INSERT INTO activity (name,money,account,createtime,description) VALUES(?,?,?,?,?)";
			// 
			Object[] params = { act.getName(), act.getMoney(), act.getAccount(), act.getCreatetime(),
					act.getDescription() };
			//
			qr.update(sql, params);
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Add Exception!");
		}
	}

	/*
	 * 
	 */
	public List<Activity> select(String startDate, String endDate) {
		try {
			//
			String sql = "SELECT * FROM activity WHERE createtime BETWEEN ? AND ?";
			// Put the two parameters in an array
			Object[] params = { startDate, endDate };
			//
			return qr.query(sql, new BeanListHandler<>(Activity.class), params);
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select Exception!");
		}
	}

	/*
	 * 
	 */
	public List<Activity> selectAll() {
		try {
			String sql = "SELECT * FROM activity";
			//
			List<Activity> list = qr.query(sql, new BeanListHandler<>(Activity.class));
			return list;
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select All Exception!");
		}
	}

}
