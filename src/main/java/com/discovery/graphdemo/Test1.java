package com.discovery.graphdemo;

import static org.neo4j.driver.Values.parameters;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.Value;

public class Test1 {

	private static Driver driver;

	public static void main(final String... args) throws Exception {

		final String uri = "bolt://localhost:7687";
		final String user = "neo4j";
		final String password = "sachin$123";

		try {

			driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));

			final Session session = driver.session();

			///
			final String name = session.writeTransaction(new TransactionWork<String>() {
				@Override
				public String execute(final Transaction tx) {
					final Result result = tx
							.run("CREATE (emp:Employee) SET emp.name = 'Sachin', emp.empId=2 RETURN emp.name");
					final Value value = result.single().get(0);
					return value.asString();
				}
			});

			System.out.println(name);
			///

			final Result result = session.run("MATCH (emp:Employee) RETURN emp");

			result.forEachRemaining(r -> {
				System.out.println(String.format("Id=%d, Name=%s", r.get("emp").get("empId").asInt(),
						r.get("emp").get("name").asString()));
			});

		} catch (final Exception ex) {
			ex.printStackTrace();
		} finally {
			if (driver != null) {
				driver.close();
			}
		}
	}

	public void printGreeting(final String message) {
		try (Session session = driver.session()) {
			final String greeting = session.writeTransaction(new TransactionWork<String>() {
				@Override
				public String execute(final Transaction tx) {
					final Result result = tx.run("CREATE (a:Greeting) " + "SET a.message = $message "
							+ "RETURN a.message + ', from node ' + id(a)", parameters("message", message));

					return result.single().get(0).asString();
				}
			});
			System.out.println(greeting);
		}
	}

}
