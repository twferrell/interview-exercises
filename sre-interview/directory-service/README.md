# Directory Service

This is a Spring Boot service written in Kotlin. The primary purpose is to provide email addresses in response to a request given an individual's name.

The application responds to GET requests of the form `/email?name=some%20name` with plain text responses of the form:
`some.name@asurint.com`


## Running the App Locally

To run the application locally, run:
```bash
gradle bootRun
```
This starts a local Tomcat dev server listening on port 9000.

The application also hosts a Spring Boot actuator endpoint on port 9001 which publishes health and metric info. The following actuator endpoints are exposed:
* http://localhost:9001/actuator/health
* http://localhost:9001/actuator/info


## Building the App for Production

Spring Boot applications are packaged into a single jar file using the default `gradle build` command.

This produces a single jar file located at `./build/libs/directory-service-1.0.jar`.

This jar file can be run directly via the following command:
```bash
java -jar ./build/libs/directory-service-1.0.jar --spring.config.location=file:./application.yml
```