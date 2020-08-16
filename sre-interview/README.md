# Asurint SRE Exercise

This git repository contains a small application that serves to generate greeting email messages for an individual by name. It is comprised of two microservices:

* **directory-service:** Looks up email addresses when given an individual's name. This is a Spring Boot service written in Kotlin.

* **messaging-service:** Builds email messages to greet an individual when given their name. This service has a dependency on the directory-service (over http). This is a node/express service written in  with ES6.

This application will be containerized using Docker and run in a production environment using Kubernetes.

Developers would also like to have an easy way to run this system locally during development in an environment as similar to production as possible. Ideally this process would involve running a single command/script and would run using a local kubernetes cluster (ie. minikube).


## Goals

* Create production Dockerfiles for each of these microservices.

* Create Kubernetes deployment specs (*it is ok if these are specific to a local environment and not suitable for production*)

* Deploy the application to Kubernetes (*ie. minikube*)

* Create a script that performs this process via a single command (*builds both Docker containers and deploys them to Kubernetes*)

* The application works as expected from the developer's local machine (*the messaging-service responds to GET requests of the form /message?name=YourNameHere*)