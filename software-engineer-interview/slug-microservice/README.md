# Slug Microservice

SpringBoot-based microservice which exposes a set of RESTful APIs, allowing the service-consumer to perform a basic set of CRUD operations on slug entities.

This Microservice includes an embedded H2 database for the in-memory persistence via a JPA repository.

The project contains a mixture of unit & integration tests that were developed through an iterative TDD-based approach.

Swagger UI is included, for interactive API documentation.

## Prerequisites

JDK 8

## Tests

To execute the suite of unit/integration tests, run the embedded maven-wrapper:

#### `mvnw clean test`

## Run

You have few options to run the service:

Using SpringBoot Maven Plugin: 

#### `mvnw clean spring-boot:run`

Packaging and running the fat jar: 

#### `mvnw clean package`

#### `java -jar target/slug-microservice-1.0.0-SNAPSHOT.jar`

## Swagger (Interactive API docs)

Start the service and go to following URL:

> http://localhost:8080/api/swagger-ui.html

## DB Web GUI

To interact with the in-memory H2 DB, go to this URL:

> http://localhost:8080/api/h2

Web Access Details:
* JDBC URL: jdbc:h2:mem:testdb
* USER NAME: sa
* PASSWORD:

_The password field can be left empty._
 

