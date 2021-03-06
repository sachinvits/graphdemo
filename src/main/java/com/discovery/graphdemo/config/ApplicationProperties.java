/**
 *
 */
package com.discovery.graphdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author sachi
 *
 */
@Configuration
@ConfigurationProperties(prefix = "com.discovery.graphdemo.config")
public class ApplicationProperties {

	private String addEmployeeQuery;
	private String allEmployeeQuery;
	private String dbPassword;
	private String dbUrl;
	private String dbUser;
	private String deleteAllEmployeesQuery;
	private String deleteEmployeeQuery;
	private String findEmployeeQuery;
	private String updateEmployeeQuery;

	public String getAddEmployeeQuery() {
		return addEmployeeQuery;
	}

	public String getAllEmployeeQuery() {
		return allEmployeeQuery;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDeleteAllEmployeesQuery() {
		return deleteAllEmployeesQuery;
	}

	public String getDeleteEmployeeQuery() {
		return deleteEmployeeQuery;
	}

	public String getFindEmployeeQuery() {
		return findEmployeeQuery;
	}

	public String getUpdateEmployeeQuery() {
		return updateEmployeeQuery;
	}

	public void setAddEmployeeQuery(final String addEmployeeQuery) {
		this.addEmployeeQuery = addEmployeeQuery;
	}

	public void setAllEmployeeQuery(final String allEmployeeQuery) {
		this.allEmployeeQuery = allEmployeeQuery;
	}

	public void setDbPassword(final String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public void setDbUrl(final String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public void setDbUser(final String dbUser) {
		this.dbUser = dbUser;
	}

	public void setDeleteAllEmployeesQuery(final String deleteAllEmployeesQuery) {
		this.deleteAllEmployeesQuery = deleteAllEmployeesQuery;
	}

	public void setDeleteEmployeeQuery(final String deleteEmployeeQuery) {
		this.deleteEmployeeQuery = deleteEmployeeQuery;
	}

	public void setFindEmployeeQuery(final String findEmployeeQuery) {
		this.findEmployeeQuery = findEmployeeQuery;
	}

	public void setUpdateEmployeeQuery(final String updateEmployeeQuery) {
		this.updateEmployeeQuery = updateEmployeeQuery;
	}

}
