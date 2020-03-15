package com.discovery.graphdemo.repo;

import java.util.List;

import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.discovery.graphdemo.config.ApplicationProperties;
import com.discovery.graphdemo.config.DBClient;
import com.discovery.graphdemo.exception.EmployeeNotFoundException;
import com.discovery.graphdemo.exception.GraphDbException;
import com.discovery.graphdemo.model.Employee;

@Component
public class EmployeeRepository {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Autowired
	private DBClient dbClient;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public Integer addEmployee(final Employee employee) {
		try {
			return dbClient.executeWithTransaction(String.format(applicationProperties.getAddEmployeeQuery(),
					employee.getName(), employee.getEmpId()));

		} catch (final Exception ex) {
			LOG.error("Exception occurred in addEmployee", ex);
			throw new GraphDbException("Exception occurred while adding an employee", ex);
		}
	}

	public void deleteAllEmployees() {
		try {
			dbClient.executeWithTransaction(applicationProperties.getDeleteAllEmployeesQuery());

		} catch (final Exception ex) {
			LOG.error("Exception occurred in deleteAllEmployees", ex);
			throw new GraphDbException("Exception occurred while deleting all employees", ex);
		}
	}

	public final List<Record> getAllEmployees() {

		try {
			return dbClient.execute(applicationProperties.getAllEmployeeQuery());

		} catch (final Exception ex) {
			LOG.error("Exception occurred in getAllEmployees", ex);
			throw new GraphDbException("Exception occurred while fetching all employees", ex);
		}
	}

	public Record getEmployee(final Integer empId) {
		final List<Record> result = dbClient
				.execute(String.format(applicationProperties.getFindEmployeeQuery(), empId));

		if (CollectionUtils.isEmpty(result)) {
			throw new EmployeeNotFoundException("Employee not found for empId=" + empId);
		}

		return result.get(0);
	}

	public Integer updateEmployee(final Employee employee) {
		try {
			return dbClient.executeWithTransaction(String.format(applicationProperties.getUpdateEmployeeQuery(),
					employee.getEmpId(), employee.getName()));

		} catch (final Exception ex) {
			LOG.error("Exception occurred in updateEmployee", ex);
			throw new GraphDbException("Exception occurred while updating an employee", ex);
		}
	}

}
