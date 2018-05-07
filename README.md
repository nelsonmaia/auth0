# Auth0 Technical Exercise 

This project is an example how to call APIs from Client Side Web Apps. The Web Application receives credentials from the user and invoke the APIs in the user's behalf. The project contains two Spring Boot applications:

- gs-rest-service-cors-complete - Sample application from Spring Boots that expose REST APIs. This application was extended to handle Auth0 authentication. 
- auth0 - Simple Web Application secured by Auth0. The application provides functionalities to invoke the REST APIs from gs-spring-boot. 

Both applications were developed using [Spring Boots framework](https://projects.spring.io/spring-boot/). 

The application allow authenticated user to receive, change or receive greetings. 

When the user wants to receive a greeting he will see a default message of greeting plus his name.

If the user has permission, he can update the default greeting message.

If the user has permission, he can delete the changes on the default greeting message.



## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

For the purpose of this exercise you will need to have the following software installed. 

- jdk 8
- Spring Tool Suite 3.9.4+

If you don't want to use Spring Tool Suite, you can use Eclipse or any other IDE and install the Spring Boot dependencies. 

### Installing

[Spring Tool Suite] (https://spring.io/tools/sts/all)
[JDK 8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


## Deployment

Configure your application on auth0.properties. Both applications contain this file.

By default Auth0 will run on http://localhost:8090/ and gs-rest-service-cors-complete will run on http://localhost:8080. You can change this configuration on application.properties

Using Spring Tool Suite, just run both applications as Spring Boot App.

If you want to deploy using only maven, please see instructions [here] (http://TODO.

# Permissions

The application will allow access for any user in Auth0 but it will allow operations based on the Job Title metadata from the user.

- Head of Customer Success - Will have full access and he can Read, Update or Delete a greeting. 
- Customer Success - Senior Architect - Has permission to read and update greetings
- Customer Success - Junior Architect - Has permission to read greetings


## How to use

After you configure and deploy both applications, you can access the following URL (or the URL you configured in your application):

http://localhost:8090/

This is the Welcome Page and is public, you don't need to login to see this page. Click on the link and you will see Auth0 authentication page.

After the login, if the user has permission he will see a greeting. If he has permission he will see all the operations he has access to. 

To understand a bit more of the classes structure, please refer to the javadoc


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Nelson Maia Matias** - *Initial work* -
