/**
 *
 */
package com.discovery.graphdemo.config;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sachin
 *
 */
@Component
public class DBClient {

	@Autowired
	private ApplicationProperties applicationProperties;

	private Driver driver = null;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@PreDestroy
	private void closeDriver() {
		driver.close();
	}

	public List<Record> execute(final String query) {
		try (Session session = driver.session()) {
			final Result result = session.run(query);
			return result.list();

		} catch (final Exception ex) {
			LOG.error("Exception occurred while executing query: {}", query);
		}

		return null;
	}

	@PostConstruct
	private void init() {
		LOG.info("Initializing Neo4j DB driver.");
		driver = GraphDatabase.driver(applicationProperties.getDbUrl(),
				AuthTokens.basic(applicationProperties.getDbUser(), applicationProperties.getDbPassword()));

		LOG.info("Finished initializing Neo4j DB driver.");
	}

}
