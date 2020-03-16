Neo4j DB Demo Application Using Spring Boot and Bolt APIs
===

This sample project will demonstrate using Spring Boot and Neo4j together.

Neo4j has provided many convenient ways to integrate Neo4j with Spring-based application. <br>
Neo4j provides official drivers for many popular programming languages including Java. Here I have used [Bolt Driver](https://neo4j.com/docs/developer-manual/current/drivers/) to access Neo4j from Spring Boot App.

Quickstart
===

1. [Download](https://neo4j.com/download-center/#enterprise) and download the zip file. In this project, I have used using Community Server (4.0.1).
2. Unzip the file into any folder of your choice. Lets call this folder as **`Neo4j_Home`** so that we can refer to this folder later sections.
3. Open Console and enter below commands to start the server:
    ```
    cd <Neo4j_Home>
    cd bin
    neo4j console
    ```
    **Output**
    ```
    2020-03-16 15:26:22.768+0000 INFO  ======== Neo4j 4.0.1 ========
    2020-03-16 15:26:22.776+0000 INFO  Starting...
    2020-03-16 15:26:36.348+0000 INFO  Bolt enabled on localhost:7687.
    2020-03-16 15:26:36.349+0000 INFO  Started.
    2020-03-16 15:26:37.291+0000 INFO  Remote interface available at http://localhost:7474/
    ```
4. Open web browser and enter URL: http://localhost:7474/. This will open Neo4j Browser web application.
5. Connect to the Neo4j server using username: `neo4j` and password: `neo4j`. This is the default username and password for neo4j server. 
6. Once you are authenticated with default username and password; the application will prompt you to generate new password. <br> Just in case if you want to change password again then follow instruction given in this [guide](https://neo4j.com/docs/operations-manual/current/configuration/set-initial-password/).
7. Please refer to this [guide](https://neo4j.com/docs/operations-manual/current/configuration/) for more configurations.
8. Clone this project from GitHub.
9. Update `src/main/resources/application.properties` file with the username and password you set above.
10. Run the project with `mvn spring-boot:run`.


I have also hosted this project on AWS. In Aws environment; initially I struggled to establish connectivity between neo4j & Spring Boot app. I was keep getting Authentication error in Spring Boot App. I have tried setting up neo4j using Docker, downloading and extracting tar file. But none of that approach worked. I always had poroper Security Group(Inbound rule) but still no success.
<br>
So as a last option, I have created EC2 instance using one of the `neo4j-community` public `Amazon Machine Image (AMI)` and this worked! :happy:


Code Walkthrough
===
## Key Maven Dependencies
```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>

    <!-- neo4j dependencies -->
    <dependency>
        <groupId>org.neo4j.driver</groupId>
        <artifactId>neo4j-java-driver</artifactId>
        <version>4.0.1</version>
    </dependency>
```
include::pom.xml[tags=dependencies]


I have created a Spring `@Component` DBClient which is responsible for all interactions with Neo4j database instance.

Method `init()` initializes bolt driver.
```
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

```
include::src/main/java/com/discovery/graphdemo/config/DBClient.java


Method `closeDriver()` closes connection with database.
```
	@PreDestroy
	private void closeDriver() {
		driver.close();
	}
```
include::src/main/java/com/discovery/graphdemo/config/DBClient.java


Finally, open any Web browser and enter URL **`http://localhost:8080/graphdemo/index.html`** to see the working aplication.
<br> <br>


Application Stack
=== 
* Application Type:         Spring-Boot Java Web Application
* Web framework:            Spring-Boot enabled Spring-WebMVC
* Persistence Access:       Neo4j Bolt Driver (4.0.1)
* Database:                 Neo4j-Server 4.0.1
* Frontend:                 Angularjs

REST Endpoints
===

| Description | HTTP Method | URL | Payload |
| --- | --- | --- | --- |
| Get All Employees |GET | http://localhost:8080/graphdemo/v1/employee/all | |
| Get Employee | GET | http://localhost:8080/graphdemo/v1/employee/{empId} | |
| Add Employee | POST | http://localhost:8080/graphdemo/v1/employee | {"empId":1, "name": "John"} |
| Update Employee | PUT | http://localhost:8080/graphdemo/v1/employee | {"empId":1, "name": "Sam"} |
| Delete Employee | DELETE | http://localhost:8080/graphdemo/v1/{empId} |  |
| Delete All Employee | DELETE | http://localhost:8080/graphdemo/v1/all |  |
