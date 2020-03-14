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
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
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
		LOG.debug("Executing query: {}", query);

		try (Session session = driver.session()) {
			final Result result = session.run(query);
			return result.list();

		} catch (final Exception ex) {
			LOG.error("Exception occurred while executing query: {}", query);
		}

		return null;
	}

	public Integer executeWithTransaction(final String query) {
		Integer empId = null;

		LOG.debug("Executing with transaction, query: {}", query);

		try (Session session = driver.session()) {
			empId = session.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(final Transaction tx) {
					final Result result = tx.run(query);
					return result.single().get(0).asInt();
				}
			});

		} catch (final Exception ex) {
			LOG.error("Exception occurred while executing query: {}", query);
		}

		return empId;
	}

	@PostConstruct
	private void init() {
		LOG.info("Initializing Neo4j DB driver.");
		driver = GraphDatabase.driver(applicationProperties.getDbUrl(),
				AuthTokens.basic(applicationProperties.getDbUser(), applicationProperties.getDbPassword()));

		LOG.info("Finished initializing Neo4j DB driver.");
	}

}
