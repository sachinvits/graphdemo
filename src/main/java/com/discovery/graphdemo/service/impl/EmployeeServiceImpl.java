/**
 *
 */
package com.discovery.graphdemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.discovery.graphdemo.dto.EmployeeRequestDto;
import com.discovery.graphdemo.dto.EmployeeResponseDto;
import com.discovery.graphdemo.model.Employee;
import com.discovery.graphdemo.repo.EmployeeRepository;
import com.discovery.graphdemo.service.EmployeeService;

/**
 *
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public EmployeeResponseDto createEmployee(final EmployeeRequestDto requestDto) {
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		final List<Employee> employeeList = new ArrayList<>();

		final List<Record> records = employeeRepo.getAllEmployees();

		if (CollectionUtils.isNotEmpty(records)) {

			records.forEach(rec -> {
				final Employee emp = new Employee();
				emp.setName(rec.get("emp").get("name").asString());
				emp.setEmpId(rec.get("emp").get("id").asInt());
				employeeList.add(emp);

				LOG.debug("Id={}, Name={}", rec.get("emp").get("id").asInt(), rec.get("emp").get("name").asString());
			});
		}

		return employeeList;
	}

}
