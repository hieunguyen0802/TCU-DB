package edu.tcu.cs.company.tests;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import edu.tcu.cs.company.bootstrap.HibernateBootstrap;
import edu.tcu.cs.company.domain.Department;
import edu.tcu.cs.company.domain.Employee;
import edu.tcu.cs.company.domain.Project;
import edu.tcu.cs.company.tools.HibernateUtils;

public class HibernateAPITest {

	@Test
	/**
	 * Save an Employee object to MySQL database using session.save() API
	 * 
	 * @throws ParseException
	 */
	public void testHibernate1() throws ParseException {

		// configures settings from hibernate.cfg.xml and then build a registry
		/*
		 * First we build a StandardServiceRegistry instance which incorporates
		 * configuration information into a working set of Services for use by the
		 * SessionFactory. In this project we defined all configuration information in
		 * hibernate.cfg.xml so there is not much interesting to see here.
		 */
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		/*
		 * Using the StandardServiceRegistry we create the
		 * org.hibernate.boot.MetadataSources which is the start point for telling
		 * Hibernate about your domain model. Again, since we defined that in
		 * hibernate.cfg.xml so there is not much interesting to see here.
		 * 
		 * Metadata (returned from buildMetadata method) represents the complete,
		 * partially validated view of the application domain model which the
		 * SessionFactory will be based on.
		 * 
		 * Finally, a SessionFactory is then set up once for an application!The
		 * SessionFactory is a thread-safe object that is instantiated once to serve the
		 * entire application.
		 */
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		/*
		 * The SessionFactory acts as a factory for org.hibernate.Session instances,
		 * which should be thought of as a corollary to a "unit of work".
		 */
		Session session = sessionFactory.openSession(); // a session is created by session factory,you can think of a
														// Hibernate session as a JDBC Connection
														// Hibernate session is not thread safe, which means it can only
														// be used by one thread.
		Transaction transaction = session.beginTransaction(); // a session is used to open and commit a transaction
		// create an object of Employee class
		// set all the attributes
		Employee emp = new Employee();
		emp.setSSN("123456789");
		emp.setFname("John");
		emp.setLname("Smith");

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		emp.setBdate(simpleDateFormat.parse("1965-01-09"));

		emp.setAddress("731 Fondren, Houston, TX");
		emp.setSex("M");
		emp.setSalary(Double.valueOf(30000));

		// We can directly save an object into DB, Hibernate will generate corresponding
		// SQL for us
		Serializable sid = session.save(emp);// save is one API provided by Session, it is used to persist the Employee
												// object
		System.out.println("The returned serialized id is " + sid);
		transaction.commit();
		session.close();
	}

	@Test
	/**
	 * We first create three Department objects and one Employee object. Then we add
	 * this employee to one of the department. In the end, we save all four object
	 * into DB.
	 * 
	 * @throws ParseException
	 */
	public void testHibernate2() throws ParseException {
		/*
		 * Session class: The main runtime interface between a Java application and
		 * Hibernate. This is the central API class abstracting the notion of a
		 * persistence service. The life-cycle of a Session is bounded by the beginning
		 * and end of a logical transaction. (Long transactions might span several
		 * database transactions.) The main function of the Session is to offer CRUD
		 * operations for instances of mapped entity classes.
		 */
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		// create three Department objects
		Department dept5 = new Department();
		dept5.setDname("Research");
		dept5.setDnumber(Long.valueOf(5));

		Department dept1 = new Department();
		dept1.setDname("Headquarters");
		dept1.setDnumber(Long.valueOf(1));

		Department dept4 = new Department();
		dept4.setDname("Administration");
		dept4.setDnumber(Long.valueOf(4));

		// save them to DB using session.save
		Serializable sid1 = session.save(dept1);
		Serializable sid4 = session.save(dept4);
		Serializable sid5 = session.save(dept5);

		System.out.println("The returned serialized ids for depts are " + sid1 + " " + sid4 + " " + sid5);

		// create an Employee object
		Employee emp = new Employee();
		emp.setSSN("333444555");
		emp.setFname("Franklin");
		emp.setLname("Wong");

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		emp.setBdate(simpleDateFormat.parse("1955-12-08"));

		emp.setAddress("638 Voss, Houston, TX");
		emp.setSex("M");
		emp.setSalary(Double.valueOf(40000));

		// add emp to dept5
		dept5.addEmployee(emp);
		// save emp using session.save
		Serializable sid = session.save(emp);// save is one API provided by Session, it is used to persist the Employee
												// object
		System.out.println("The returned serialized id is " + sid);

		transaction.commit();
		session.close();
	}

	@Test
	/**
	 * In this test case, we call the loadCompanyData method in class
	 * HibernateBootstrap to populate the COMPANY database. Then, we query this DB
	 * for an employee with PK equal to 123456789
	 */
	public void testHibernate3() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		Employee employee = session.get(Employee.class, "123456789");// session.get API is used to retrieve one record
		System.out.println(employee);

		transaction.commit();
		session.close();
	}

	@Test
	/**
	 * In this test case, we call the loadCompanyData method in class
	 * HibernateBootstrap to populate the COMPANY database. Then, we query this DB
	 * for an employee with PK equal to 123456789. Then we update this employee's
	 * salary to 36000.0
	 */
	public void testHibernate4() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		// We are going to set John Smith's salary to 36000
		// Step 1, retrieve John Smith using get method
		Employee employee = session.get(Employee.class, "123456789");// session.get method is used to retrieve one
																		// record
		System.out.println(employee);
		// Step 2, set salary to a new value
		employee.setSalary(36000.0);// set John Smith's salary to 36000.0
		// that's it, DB will capture this change and update the corresponding record
		// automatically.

		transaction.commit();
		session.close();
		// check the DB to see what happened?
	}

	@Test
	/**
	 * In this test case, we call the loadCompanyData method in class
	 * HibernateBootstrap to populate the COMPANY database. Then, we query this DB
	 * for an employee with PK equal to 123456789. Then we delete this employee
	 */
	public void testHibernate5() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		// We are going to delete John Smith
		// Step 1, retrieve John Smith using get method
		Employee employee = session.get(Employee.class, "123456789");// get API is used to retrieve one record
		System.out.println(employee);
		// Step 2, delete!!!
		session.delete(employee);
		transaction.commit();
		session.close();
	}

	@Test
	/**
	 * In this test case, we show how to use session.saveOrUpdate
	 */
	public void testHibernate6() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		Employee employee = session.get(Employee.class, "123456789");// get API is used to retrieve one record
		employee.setFname("Johnny");
		employee.setSalary(42000.0);
		session.saveOrUpdate(employee);// saveOrUpdate an existing object

		Employee employee2 = new Employee();
		employee2.setFname("Ming");
		employee2.setLname("Yao");
		employee2.setSSN("917384662");
		session.saveOrUpdate(employee2);// saveOrUpdate a new object
		transaction.commit();
		session.close();
	}

	@Test
	/**
	 * In this test case, we show the status of a Java object. Recall that, in
	 * Hibernate's eyes, a Java object may be in one of the three status: transient
	 * when the object is just being created using new persistent when we use
	 * session.save or saveOrUpdate, or persist methods on it detached when we close
	 * the session
	 */
	public void testHibernate7() {

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		Department dept5 = new Department();
		// 1. at this point, dept5 is in transient state, it is not managed by with any
		// session
		// there is no OID attached to it
		dept5.setDname("Research");
		dept5.setDnumber(Long.valueOf(5));

		session.save(dept5);
		// 2. at this point, dept5 is in persistent state, it represents a row in the
		// table of your DB
		// any modification will be automatically reflected in DB
		dept5.setDname("Research123");// if you look at database, the name is changed to Research123

		transaction.commit();
		session.close();

		// 3. at this point, dept5 becomes detached from the session. There is still
		// OID.
		System.out.println(dept5.getDname());
	}

	@Test
	/**
	 * In this test case, we show the power of cascading in Hibernate We will first
	 * query the DB for an employee with PK equal to 123456789 then find his/her
	 * department information then find the projects he/she is involved. How do you
	 * do this in SQL? A lot of JOIN, right? Here is how we do it in Hibernate.
	 */
	public void testHibernate8() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		Employee employee = session.get(Employee.class, "123456789");// get API is used to retrieve one record

		System.out.println(employee);

		Department d = employee.getDept();
		System.out.println(d);

		List<Project> listOfPorjects = employee.getProjects();
		for (Project project : listOfPorjects) {
			System.out.println(project);
		}

		transaction.commit();
		session.close();
	}

	@Test
	/**
	 * In this test case, we want to see a list of employee in dept 5
	 */
	public void testHibernate9() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		Department dept5 = session.get(Department.class, 5L);// get API is used to retrieve one record

		List<Employee> listOfEmployees = dept5.getEmployees();
		for (Employee employee : listOfEmployees) {
			System.out.println(employee);
		}

		transaction.commit();
		session.close();
	}

	@Test
	/**
	 * In this test case, we show how to use HQL (Hibernate Query Language) to
	 * compose complex queries. We also show how to write SQL in Java using
	 * Hibernate
	 */
	public void testHibernate10() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		/*
		 * How do we implement select all employees? There are two ways 1. HQL 2. SQL
		 */
		// HQL approach
		Query query = session.createQuery("from Employee");// Employee refers to class name
		List<Employee> list = query.list();
		for (Employee e : list) {
			System.out.println(e);
		}

		// SQL approach
		// SQLQuery query = session.createSQLQuery("select * from Employee");// here,
		// Employee refers to table name
		// List<Object[]> list = query.list();// each row is saved in an object array
		// for (Object[] e : list) {
		// System.out.println(Arrays.toString(e));
		// }
		/*
		 * Result of SQL approach looks like this: [123456789, 731 Fondren, Houston,
		 * TX,1965-01-09 00:00:00.0, John, Smith, B, 30000.0, M, 1, 5, 33344555]
		 * [33344555, 638 Voss, Houston, TX, 1955-12-08 00:00:00.0, Franklin, Wong, T,
		 * 40000.0, M, 1, 5, 888665555] [453453453, 5631 Rice, Houston, TX, 1972-07-31
		 * 00:00:00.0, Joyce, English, A, 25000.0, F, 0, 5, 33344555] [666884444, 975
		 * Fire Oak, Humble, TX, 1962-09-15 00:00:00.0, Ramesh, Narayan, K, 38000.0, M,
		 * 0, 5, 33344555] [888665555, 450 Stone, Houston, TX, 1937-11-10 00:00:00.0,
		 * James, Borg, E, 55000.0, M, 0, 1, null] [987654321, 291 Berry, Bellaire,
		 * TX,1941-06-20 00:00:00.0, Jennifer, Wallace, S, 43000.0, F, 1, 4, 888665555]
		 * [987987987, 980 Dallas, Houston, TX, 1969-03-29 00:00:00.0, Ahmad, Jabbar, V,
		 * 25000.0, M, 0, 4, 987654321] [99988777, 3321 Castle, Spring, TX, 1968-01-19
		 * 00:00:00.0, Alicia, Zelaya, J, 25000.0, F, 1, 4, 987654321]
		 */

		// SQLQuery query = session.createSQLQuery("select * from
		// Employee").addEntity(Employee.class);
		// List<Employee> list = query.list();// each row is saved in an object array
		// for (Employee e : list) {
		// System.out.println(e);
		// }

		transaction.commit();
		session.close();

	}

	@Test
	/**
	 * More about HQL statements
	 */
	public void testHibernate11() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		// HQL approach
		Query query = session.createQuery("from Employee where fname = ? order by lname desc");// Employee refers to
																								// class name
		// fname and lname refer to attributes
		query.setParameter(0, "Franklin");
		List<Employee> list = query.list();
		for (Employee e : list) {
			System.out.println(e);
		}

		transaction.commit();
		session.close();

	}

	@Test
	/**
	 * More about HQL statements
	 */
	public void testHibernate12() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		// HQL approach
		Query query = session.createQuery("from Employee where fname = :aaa and lname =:bbb");// Employee refers to
																								// class name
		// fname and lname refer to attributes
		query.setParameter("aaa", "Franklin");
		query.setParameter("bbb", "Wong");
		List<Employee> list = query.list();
		for (Employee e : list) {
			System.out.println(e);
		}

		transaction.commit();
		session.close();

	}

	@Test
	/**
	 * Another way to query in Hibernate is call QBC (Query By Criteria) This is
	 * used a lot in industry.
	 */
	public void testHibernate13() {

		// prepare data
		try {
			HibernateBootstrap.loadCompanyData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		// Create an instance of CriteriaBuilder by calling the getCriteriaBuilder()
		// method
		CriteriaBuilder cb = session.getCriteriaBuilder();
		// Create an instance of CriteriaQuery by calling the CriteriaBuilder
		// createQuery() method
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		criteria.select(root);

		criteria.select(root).where(cb.equal(root.get("fname"), "Franklin"));
		criteria.select(root).where(cb.equal(root.get("lname"), "Wong"));
		criteria.select(root).where(cb.gt(root.get("salary"), 10000.0));

		Query<Employee> query = session.createQuery(criteria);
		List<Employee> list = query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}
		transaction.commit();
		session.close();
	}
}
