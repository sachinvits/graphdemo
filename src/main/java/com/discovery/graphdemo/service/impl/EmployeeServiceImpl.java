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
import com.discovery.graphdemo.exception.GraphDbException;
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
	public Integer addEmployee(final EmployeeRequestDto requestDto) {
		final Employee emp = new Employee();
		emp.setEmpId(requestDto.getEmpId());
		emp.setName(requestDto.getName());

		final Integer savedEmpId = employeeRepo.addEmployee(emp);

		if (savedEmpId == null) {
			LOG.error("Error occurred while adding new Employee id={}, name={}", requestDto.getEmpId(),
					requestDto.getName());
			throw new GraphDbException("Error occurred while adding new Employee");
		}

		return savedEmpId;
	}

	@Override
	public void deleteAllEmployees() {
		employeeRepo.deleteAllEmployees();
	}

	@Override
	public void deleteEmployee(final Integer empId) {
		employeeRepo.deleteEmployee(empId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		final List<Employee> employeeList = new ArrayList<>();

		final List<Record> records = employeeRepo.getAllEmployees();

		if (CollectionUtils.isNotEmpty(records)) {

			records.forEach(rec -> {
				final Employee emp = new Employee();
				emp.setName(rec.get("emp").get("name").asString());
				emp.setEmpId(rec.get("emp").get("empId").asInt());
				employeeList.add(emp);

				LOG.debug("Emp. Id={}, Name={}", rec.get("emp").get("empId").asInt(),
						rec.get("emp").get("name").asString());
			});
		}

		return employeeList;
	}

	@Override
	public Employee getEmployee(final Integer empId) {
		final Record rec = employeeRepo.getEmployee(empId);

		final Employee emp = new Employee();
		emp.setName(rec.get("emp").get("name").asString());
		emp.setEmpId(rec.get("emp").get("empId").asInt());

		LOG.debug("Emp. Id={}, Name={}", rec.get("emp").get("empId").asInt(), rec.get("emp").get("name").asString());

		return emp;
	}

	@Override
	public Integer updateEmployee(final EmployeeRequestDto requestDto) {
		final Employee emp = new Employee();
		emp.setEmpId(requestDto.getEmpId());
		emp.setName(requestDto.getName());

		final Integer savedEmpId = employeeRepo.updateEmployee(emp);

		if (savedEmpId == null) {
			LOG.error("Error occurred while updating an Employee id={}, name={}", requestDto.getEmpId(),
					requestDto.getName());
			throw new GraphDbException("Error occurred while updating an Employee");
		}

		return savedEmpId;
	}

}
