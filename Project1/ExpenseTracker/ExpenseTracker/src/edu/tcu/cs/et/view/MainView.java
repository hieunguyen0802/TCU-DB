package edu.tcu.cs.et.view;
/*

 */

import java.util.List;
import java.util.Scanner;

import edu.tcu.cs.et.controller.ActivityController;
import edu.tcu.cs.et.domain.Activity;

public class MainView {
	//MainView holds an instance of ActivityController
	private ActivityController controller = new ActivityController();

	/*
	 * The control flow in run() is 1.print the menu 2.get the input from user
	 * 3.call the corresponding method to update or query the database 4.print
	 * results to user 5.go back to step 1 until the input is exit.
	 */
	public void run() {

		Scanner sc = new Scanner(System.in);
		while (true) {
			// print the menu
			System.out.println("---------------Expense Tracker Software---------------");
			System.out.println("1.Add Activity 2.Edit Activity 3.Delete Activity 4.Search Activity 5.Exit");
			System.out.println("Please select the function, type [1-5] and press enter:");

			int choose = sc.nextInt();

			switch (choose) {
			case 1:
				addActivity();
				break;
			case 2:
				editActivity();
				break;
			case 3:
				deleteActivity();
				break;
			case 4:
				selectActivity();
				break;
			case 5:
				System.exit(0);
				break;
			}
		}
	}

	/*
	 * 
	 */
	public void addActivity() {
		System.out.println("Please enter the following information:");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name of the activity:");
		String name = sc.nextLine();
		System.out.println("Enter Amount of Money:");
		double money = Double.parseDouble(sc.nextLine());
		System.out.println("Enter Account Name:");
		String account = sc.nextLine();
		System.out.println("Enter Time in this format: YYYY-MM-DD");
		String createtime = sc.nextLine();
		System.out.println("Description of this activity:");
		String description = sc.nextLine();
		//Create an Activity instance to hold the collected data
		Activity act = new Activity(0, name, money, account, createtime, description);
		//pass the object act to controller
		controller.addActivity(act);
		System.out.println("Activity Added Successfully!");
	}
	/*
	 */
	public void editActivity() {
		// We first display all activities to the user
		selectAll();
		System.out.println("Which activity do you want to edit?");
		Scanner sc = new Scanner(System.in);
		System.out.print("Type ID and press enter:");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Enter new name:");
		String name = sc.nextLine();
		System.out.println("Enter new amount of money:");
		double money = Double.parseDouble(sc.nextLine());
		System.out.println("Enter new account:");
		String account = sc.nextLine();
		System.out.println("Enter new time in this format: YYYY-MM-DD");
		String createtime = sc.nextLine();
		System.out.println("Enter new description:");
		String description = sc.nextLine();
		// Create an Activity instance to encapsulate all the data above
		Activity act = new Activity(id, name, money, account, createtime, description);
		// pass the object to controller
		controller.editActivity(act);
		System.out.println("Edit Successfully!");
	}
	/*
	 * 
	 */
	public void selectActivity() {
		System.out.println("1. Search All Activities 2. Search Activities Based on Criteria");
		Scanner sc = new Scanner(System.in);
		int selectChooser = sc.nextInt();
		switch (selectChooser) {
		case 1:
			// select all activities from DB
			selectAll();
			break;
		case 2:
			// select based on certain criteria
			select();
			break;
		}
	}

	/*
	 * 
	 */
	public void selectAll() {
		// Call ActivityController's selectAll method
		List<Activity> list = controller.selectAll();
		if (list.size() != 0)
			print(list);
		else
			System.out.println("No Activity is found!");
	}

	/*
	 * 
	 */
	public void select() {
		System.out.println("Time Format: YYYY-MM-DD");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter start time:");
		String startDate = sc.nextLine();
		System.out.print("Enter end time:");
		String endDate = sc.nextLine();
		List<Activity> list = controller.select(startDate, endDate);
		if (list.size() != 0)
			print(list);
		else
			System.out.println("No Activity is found during the specified time period!");
	}
	/*
	 */
	public void deleteActivity() {
		// First, we display all existing activities to user
		selectAll();
		System.out.println("Enter the ID of the activity you want to delete and press enter:");
		int id = new Scanner(System.in).nextInt();
		// pass the id to the controller
		controller.deleteActivity(id);
		System.out.println("Activity is deleted.");
	}
	private void print(List<Activity> list) {
		System.out.printf("%-5s %-25s %-35s%-15s%-15s%-30s%n", "ID", "Name", "Account", "Money", "Time", "Description");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		// Iterate the list and print each item to console
		for (Activity act : list) {
			System.out.printf("%-5s %-25s %-35s%-15s%-15s%-30s%n", act.getId(), act.getName(), act.getAccount(),
					act.getMoney(), act.getCreatetime(), act.getDescription());
		}
	}
}
