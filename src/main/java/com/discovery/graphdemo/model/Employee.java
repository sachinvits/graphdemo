package com.discovery.graphdemo.model;

public class Employee {

	/**
	 * Employee Id.
	 */
	private int empId;

	/**
	 * Employee Name.
	 */
	private String name;

	public int getEmpId() {
		return empId;
	}

	public String getName() {
		return name;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EmployeeNode [empId=" + empId + ", name=" + name + "]";
	}

}
