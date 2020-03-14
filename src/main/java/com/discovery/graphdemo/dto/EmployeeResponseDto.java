package com.discovery.graphdemo.dto;

import java.util.List;

import com.discovery.graphdemo.model.Employee;

public class EmployeeResponseDto {

	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
