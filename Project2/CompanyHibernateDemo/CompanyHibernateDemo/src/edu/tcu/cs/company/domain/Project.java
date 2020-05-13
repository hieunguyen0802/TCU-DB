package edu.tcu.cs.company.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Project {
	private String pname;
	@Id
	private Long pnumber;
	@Version
	private Long version;
	private String location;
	@ManyToOne
	private Department dept;
	@ManyToMany(mappedBy = "projects")
	private List<Employee> employees = new ArrayList<>();

	public void addEmployee(Employee e) {
		this.employees.add(e);
		e.getProjects().add(this);
	}

	public void removeEmployee(Employee e) {
		this.employees.remove(e);
		e.getProjects().remove(this);
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Long getPnumber() {
		return pnumber;
	}

	public void setPnumber(Long pnumber) {
		this.pnumber = pnumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Project(String pname, Long pnumber, String location, Department dept, List<Employee> employees) {
		super();
		this.pname = pname;
		this.pnumber = pnumber;
		this.location = location;
		this.dept = dept;
		this.employees = employees;
	}

	public Project() {
		super();
	}

	@Override
	public String toString() {
		return "Project [Project Name=" + pname + ", Project Num=" + pnumber + "]";
	}

}
