package edu.tcu.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * This is a JavaBean Each attribute correspond to a column in the DB table You
 * need to define getters and setters and also the toString method
 * 
 * @author bingyang
 *
 */
public class Employee implements Serializable {
	private String fname;
	private String mint;
	private String lname;
	private String ssn;
	private Date bdate;
	private String address;
	private String sex;
	private Double salary;
	private String superssn;
	private Integer dno;

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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
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

	public String getSuperssn() {
		return superssn;
	}

	public void setSuperssn(String superssn) {
		this.superssn = superssn;
	}

	public Integer getDno() {
		return dno;
	}

	public void setDno(Integer dno) {
		this.dno = dno;
	}

	@Override
	public String toString() {
		return "Employee [fname=" + fname + ", mint=" + mint + ", lname=" + lname + ", ssn=" + ssn + ", bdate=" + bdate
				+ ", address=" + address + ", sex=" + sex + ", salary=" + salary + ", superssn=" + superssn + ", dno="
				+ dno + "]";
	}

}
