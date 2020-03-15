/**
 *
 */
package com.discovery.graphdemo.config;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.collections4.CollectionUtils;
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

import com.discovery.graphdemo.exception.GraphDbException;

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

	public Boolean delete(final String query) {
		Boolean isDeleted = Boolean.FALSE;

		LOG.debug("Executing with transaction, query: {}", query);

		try (Session session = driver.session()) {
			isDeleted = session.writeTransaction(new TransactionWork<Boolean>() {
				@Override
				public Boolean execute(final Transaction tx) {
					tx.run(query);
					return Boolean.TRUE;
				}
			});

		} catch (final Exception ex) {
			LOG.error("Exception occurred while deleting entity, query: {}", query, ex);
			throw new GraphDbException("Exception occurred while deleting entity", ex);
		}

		return isDeleted;
	}

	public List<Record> findAll(final String query) {
		LOG.debug("Executing query: {}", query);

		try (Session session = driver.session()) {
			final Result result = session.run(query);
			return result.list();

		} catch (final Exception ex) {
			LOG.error("Exception occurred while finding all entities, query: {}", query, ex);
			throw new GraphDbException("Exception occurred while finding all entities", ex);
		}
	}

	public Record findOne(final String query) {
		LOG.debug("Executing query: {}", query);

		try (Session session = driver.session()) {
			final Result result = session.run(query);

			if (result != null) {
				final List<Record> records = result.list();
				if (CollectionUtils.isNotEmpty(records)) {
					return records.get(0);
				}
			}

		} catch (final Exception ex) {
			LOG.error("Exception occurred while executing query: {}", query, ex);
			throw new GraphDbException("Exception occurred while finding an entity", ex);
		}

		return null;
	}

	@PostConstruct
	private void init() {
		LOG.info("Initializing Neo4j DB driver.");
		driver = GraphDatabase.driver(applicationProperties.getDbUrl(),
				AuthTokens.basic(applicationProperties.getDbUser(), applicationProperties.getDbPassword()));

		LOG.info("Neo4j connectivity, user={}, password={}, url={}", applicationProperties.getDbUser(),
				applicationProperties.getDbPassword(), applicationProperties.getDbUrl());

		try {
			driver.verifyConnectivity();
		} catch (final Exception ex) {
			LOG.error("Neo4j connectivity failed.", ex);
			throw new GraphDbException("Neo4j connectivity failed.", ex);
		}

		LOG.info("Neo4j connectivity, success.");

		LOG.info("Finished initializing Neo4j DB driver.");
	}

	public Integer saveOrUpdate(final String query) {
		Integer empId = null;

		LOG.debug("Executing with transaction, query: {}", query);

		try (Session session = driver.session()) {
			empId = session.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(final Transaction tx) {
					final Result result = tx.run(query);

					if (result != null) {

						final List<Record> records = result.list();
						if (CollectionUtils.isNotEmpty(records)) {
							return records.get(0).get("emp.empId").asInt();
						}

						// return result.single().get(0).asInt();
					}
					return null;
				}
			});

		} catch (final Exception ex) {
			LOG.error("Exception occurred while saving or updating entity, query: {}", query, ex);
			throw new GraphDbException("Exception occurred while saving or updating entity", ex);
		}

		return empId;
	}

}
