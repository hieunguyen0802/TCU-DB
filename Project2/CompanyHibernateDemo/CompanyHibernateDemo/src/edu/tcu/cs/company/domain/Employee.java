package edu.tcu.cs.company.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Employee {
	private String fname;
	private String mint;
	private String lname;
	@Id
	private String SSN;
	@Version
	private Long version;
	private Date bdate;
	private String address;
	private String sex;
	private Double salary;
	@ManyToOne
	private Employee supervisor;
	@ManyToOne
	private Department dept;
	@ManyToMany
	private List<Project> projects = new ArrayList<>();

	public Employee() {
		super();
	}

	public Employee(String fname, String mint, String lname, String sSN, Date bdate, String address, String sex,
			Double salary) {
		super();
		this.fname = fname;
		this.mint = mint;
		this.lname = lname;
		SSN = sSN;
		this.bdate = bdate;
		this.address = address;
		this.sex = sex;
		this.salary = salary;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMint() {
		return mint;
	}

	public void setMint(String mint) {
		this.mint = mint;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Employee [First Name=" + fname + ", Last Name=" + lname + ", SSN=" + SSN + ", Birthday=" + bdate
				+ ", Sex=" + sex + ", Salary=" + salary + "]";
	}
}
