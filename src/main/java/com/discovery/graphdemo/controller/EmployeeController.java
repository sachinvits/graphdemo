package com.discovery.graphdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.discovery.graphdemo.dto.AddEmployeeResponseDto;
import com.discovery.graphdemo.dto.EmployeeRequestDto;
import com.discovery.graphdemo.dto.EmployeeResponseDto;
import com.discovery.graphdemo.model.Employee;
import com.discovery.graphdemo.service.EmployeeService;

@RestController
@RequestMapping(value = "/v1/employee")
@Validated
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/add-employee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddEmployeeResponseDto> addEmployee(
			@Valid @RequestBody final EmployeeRequestDto employeeRequest) throws Exception {

		final Integer empId = employeeService.addEmployee(employeeRequest);

		final AddEmployeeResponseDto response = new AddEmployeeResponseDto();
		response.setEmpId(empId);
		response.setMessage("Employee added succefully");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeResponseDto> getAllEmployees() throws Exception {

		final List<Employee> employeeList = employeeService.getAllEmployees();
		final EmployeeResponseDto response = new EmployeeResponseDto();
		response.setEmployees(employeeList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}