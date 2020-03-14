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

	private String dbPassword;
	private String dbUrl;
	private String dbUser;

	public String getDbPassword() {
		return dbPassword;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public String getDbUser() {
		return dbUser;
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

}
