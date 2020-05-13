package edu.tcu.cs.company.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Department {
	private String dname;
	@Id
	private Long dnumber;
	@Version
	private Long version;
	@OneToOne
	private Employee manager;
	private Date mgrstartdate;
	@OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
	private List<Employee> employees = new ArrayList<>();
	@OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
	private List<Project> projects = new ArrayList<>();

	public void addEmployee(Employee e) {
		this.employees.add(e);
		e.setDept(this);
	}

	public void removeEmployee(Employee e) {
		this.employees.remove(e);
		e.setDept(null);
	}

	public void addProject(Project p) {
		this.projects.add(p);
		p.setDept(this);
	}

	public void removeProject(Project p) {
		this.projects.remove(p);
		p.setDept(null);
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Long getDnumber() {
		return dnumber;
	}

	public void setDnumber(Long dnumber) {
		this.dnumber = dnumber;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Date getMgrstartdate() {
		return mgrstartdate;
	}

	public void setMgrstartdate(Date mgrstartdate) {
		this.mgrstartdate = mgrstartdate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Department() {
		super();
	}

	@Override
	public String toString() {
		return "Department [Dept Name=" + dname + ", Dept Num=" + dnumber + "]";
	}

}
