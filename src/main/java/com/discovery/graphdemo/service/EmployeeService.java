package com.discovery.graphdemo.service;

import java.util.List;

import com.discovery.graphdemo.dto.EmployeeRequestDto;
import com.discovery.graphdemo.model.Employee;

public interface EmployeeService {
	Integer addEmployee(EmployeeRequestDto requestDto);

	List<Employee> getAllEmployees();
}
