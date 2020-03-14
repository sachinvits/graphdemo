package com.discovery.graphdemo.repo;

import java.util.List;

import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discovery.graphdemo.config.DBClient;
import com.discovery.graphdemo.exception.GraphDbException;
import com.discovery.graphdemo.model.Employee;

@Component
public class EmployeeRepository {

	@Autowired
	private DBClient dbClient;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public void addEmployee(final Employee employee) {

	}

	public final List<Record> getAllEmployees() {

		try {
			return dbClient.execute("MATCH (emp:Employee) RETURN emp");

		} catch (final Exception ex) {
			LOG.error("Exception occurred in getAllEmployees", ex);
			throw new GraphDbException("Exception occurred while fetching all employees", ex);
		}
	}

}
