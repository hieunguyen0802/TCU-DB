package edu.tcu.cs.et.service;

import java.util.List;

import edu.tcu.cs.et.dao.ActivityDao;
import edu.tcu.cs.et.domain.Activity;

public class ActivityService {
	private ActivityDao dao = new ActivityDao();
	/*
	 * Service passes the id of an Activity instance to DAO
	 */
	public void deleteActivity(int id) {
		dao.deleteActivity(id);
	}
	
	/*
	 * Service passes the Activity object 
	 */
	public void editActivity(Activity act) {
		dao.editActivity(act);
	}
	
	/*
	 * Service 
	 */
	public void addActivity(Activity act) {
		dao.addActivity(act);
	}
	/*
	 * 
	 */
	public List<Activity> select(String startDate,String endDate){
		return dao.select(startDate, endDate);
	}
	
	/*
	 * 
	 */
	public List<Activity> selectAll(){
		return dao.selectAll();
	}

	
	
}
