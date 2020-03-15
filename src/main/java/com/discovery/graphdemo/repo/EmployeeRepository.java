package com.discovery.graphdemo.repo;

import java.util.List;

import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discovery.graphdemo.config.ApplicationProperties;
import com.discovery.graphdemo.config.DBClient;
import com.discovery.graphdemo.exception.EmployeeNotFoundException;
import com.discovery.graphdemo.model.Employee;

@Component
public class EmployeeRepository {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Autowired
	private DBClient dbClient;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public Integer addEmployee(final Employee employee) {
		return dbClient.saveOrUpdate(
				String.format(applicationProperties.getAddEmployeeQuery(), employee.getName(), employee.getEmpId()));
	}

	public void deleteAllEmployees() {
		dbClient.delete(applicationProperties.getDeleteAllEmployeesQuery());
	}

	public void deleteEmployee(final Integer empId) {
		dbClient.delete(String.format(applicationProperties.getDeleteEmployeeQuery(), empId));
	}

	public final List<Record> getAllEmployees() {
		return dbClient.findAll(applicationProperties.getAllEmployeeQuery());
	}

	public Record getEmployee(final Integer empId) {
		final Record result = dbClient.findOne(String.format(applicationProperties.getFindEmployeeQuery(), empId));

		if (result == null) {
			throw new EmployeeNotFoundException("Employee not found for empId=" + empId);
		}

		return result;
	}

	public Integer updateEmployee(final Employee employee) {
		return dbClient.saveOrUpdate(
				String.format(applicationProperties.getUpdateEmployeeQuery(), employee.getEmpId(), employee.getName()));
	}

}
