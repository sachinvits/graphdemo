package com.discovery.graphdemo.service;

import java.util.List;

import com.discovery.graphdemo.dto.EmployeeRequestDto;
import com.discovery.graphdemo.dto.EmployeeResponseDto;
import com.discovery.graphdemo.model.Employee;

public interface EmployeeService {
	EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto);

	List<Employee> getAllEmployees();
}
