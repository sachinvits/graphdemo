package com.discovery.graphdemo.service;

import java.util.List;

import com.discovery.graphdemo.dto.EmployeeRequestDto;
import com.discovery.graphdemo.model.Employee;

public interface EmployeeService {
	Integer addEmployee(EmployeeRequestDto requestDto);

	void deleteAllEmployees();

	List<Employee> getAllEmployees();

	Employee getEmployee(Integer empId);

	Integer updateEmployee(EmployeeRequestDto requestDto);
}
