package edu.tcu.cs.company.bootstrap;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.tcu.cs.company.domain.Department;
import edu.tcu.cs.company.domain.Employee;
import edu.tcu.cs.company.domain.Project;
import edu.tcu.cs.company.tools.HibernateUtils;

public class HibernateBootstrapWithCascading {

	public static void loadCompanyData() throws ParseException {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		Department d1 = new Department();
		d1.setDname("Headquarters");
		d1.setDnumber(1L);

		Department d4 = new Department();
		d4.setDname("Administration");
		d4.setDnumber(4L);

		Department d5 = new Department();
		d5.setDname("Research");
		d5.setDnumber(5L);

		Project p1 = new Project();
		p1.setPnumber(1L);
		p1.setPname("ProductX");
		p1.setLocation("Bellaire");
		d5.addProject(p1);// this statement adds a project to a dept's project list and adds a dept to a
							// project's dept attribute, it is bidirectional

		Project p2 = new Project();
		p2.setPnumber(2L);
		p2.setPname("ProductY");
		p2.setLocation("SugarLand");
		d5.addProject(p2);

		Project p3 = new Project();
		p3.setPnumber(3L);
		p3.setPname("ProductZ");
		p3.setLocation("Houston");
		d5.addProject(p3);

		Project p10 = new Project();
		p10.setPnumber(10L);
		p10.setPname("Computerization");
		p10.setLocation("Stafford");
		d4.addProject(p10);

		Project p20 = new Project();
		p20.setPnumber(20L);
		p20.setPname("Reorganization");
		p20.setLocation("Houston");
		d1.addProject(p20);

		Project p30 = new Project();
		p30.setPnumber(30L);
		p30.setPname("Newbenefits");
		p30.setLocation("Stafford");
		d4.addProject(p30);

		// set up date format
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		// create all employees
		Employee e1 = new Employee("John", "B", "Smith", "123456789", simpleDateFormat.parse("1965-01-09"),
				"731 Fondren, Houston, TX", "M", 30000.0);
		d5.addEmployee(e1);// this statement adds an emp to a dept's emp list and adds a dept to an emp's
							// dept attribute, it is bidirectional
		p1.addEmployee(e1);// this statement adds an emp to a project's emp list and adds a project to an
							// emp's project list, it is bidirectional
		p2.addEmployee(e1);

		Employee e2 = new Employee("Franklin", "T", "Wong", "333444555", simpleDateFormat.parse("1955-12-08"),
				"638 Voss, Houston, TX", "M", 40000.0);
		d5.addEmployee(e2);
		p2.addEmployee(e2);
		p3.addEmployee(e2);
		p10.addEmployee(e2);
		p20.addEmployee(e2);

		Employee e3 = new Employee("Alicia", "J", "Zelaya", "99988777", simpleDateFormat.parse("1968-01-19"),
				"3321 Castle, Spring, TX", "F", 25000.0);
		d4.addEmployee(e3);
		p10.addEmployee(e3);
		p30.addEmployee(e3);

		Employee e4 = new Employee("Jennifer", "S", "Wallace", "987654321", simpleDateFormat.parse("1941-06-20"),
				"291 Berry, Bellaire, TX", "F", 43000.0);
		d4.addEmployee(e4);
		p20.addEmployee(e4);
		p30.addEmployee(e4);

		Employee e5 = new Employee("Ramesh", "K", "Narayan", "666884444", simpleDateFormat.parse("1962-09-15"),
				"975 Fire Oak, Humble, TX", "M", 38000.0);
		d5.addEmployee(e5);
		p3.addEmployee(e5);

		Employee e6 = new Employee("Joyce", "A", "English", "453453453", simpleDateFormat.parse("1972-07-31"),
				"5631 Rice, Houston, TX", "F", 25000.0);
		d5.addEmployee(e6);
		p1.addEmployee(e6);
		p2.addEmployee(e6);

		Employee e7 = new Employee("Ahmad", "V", "Jabbar", "987987987", simpleDateFormat.parse("1969-03-29"),
				"980 Dallas, Houston, TX", "M", 25000.0);
		d4.addEmployee(e7);
		p10.addEmployee(e7);
		p30.addEmployee(e7);

		Employee e8 = new Employee("James", "E", "Borg", "888665555", simpleDateFormat.parse("1937-11-10"),
				"450 Stone, Houston, TX", "M", 55000.0);
		d1.addEmployee(e8);
		p20.addEmployee(e8);

		// set up supervise relation
		e1.setSupervisor(e2);
		e2.setSupervisor(e8);
		e3.setSupervisor(e4);
		e4.setSupervisor(e8);
		e5.setSupervisor(e2);
		e6.setSupervisor(e2);
		e7.setSupervisor(e4);
		e8.setSupervisor(null);

		// set up dept manager relation
		d5.setManager(e2);
		d5.setMgrstartdate(simpleDateFormat.parse("1988-05-22"));
		d4.setManager(e4);
		d4.setMgrstartdate(simpleDateFormat.parse("1995-01-01"));
		d1.setManager(e8);
		d1.setMgrstartdate(simpleDateFormat.parse("1981-06-19"));

		// persist all departments
		session.persist(d1);
		session.persist(d4);
		session.persist(d5);

		transaction.commit();
		session.close();
	}

}
