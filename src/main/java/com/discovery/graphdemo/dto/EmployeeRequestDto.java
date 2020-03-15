package com.discovery.graphdemo.dto;

//@Valid
public class EmployeeRequestDto {

	/**
	 * Employee Id.
	 */
//	@NotNull(message = "empId cannot be null")
//	@Min(value = 1, message = "empId cannot have value < 1")
	private int empId;

	/**
	 * Employee Name.
	 */
//	@NotNull(message = "name cannot be null")
//	@Size(min = 3, max = 10, message = "name should be min(${min}) and max(${max} characters long.")
	private String name;

	public int getEmpId() {
		return empId;
	}

	public String getName() {
		return name;
	}

	public void setEmpId(final int empId) {
		this.empId = empId;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EmployeeRequestDto [empId=" + empId + ", name=" + name + "]";
	}

}
