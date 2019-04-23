package com.FirstBatchApplication.BatchApplication.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserDetails")
public class User {
	
	@Id
	private int id;
	private String name;
	private String dept;
	private long salary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dept=" + dept + ", salary=" + salary + "]";
	}
	
	
}
