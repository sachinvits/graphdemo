package com.discovery.graphdemo.dto;

public class EmployeeRequestDto {

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
		return "EmployeeRequestDto [empId=" + empId + ", name=" + name + "]";
	}

}
