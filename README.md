# Government funded training centers

## Description
This project is a a search page where buyers can search for manufacturers based on their customised requirements

## Table of Contents
- [Usage](#usage)
- [Configuration](#configuration)
- [Running the Tests](#running-the-tests)
- [Built With](#built-with)
- [Project Setup](#project-setup)


### Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher
- Git
- Postgresql database

## Project Setup
step-by-step instructions to set up the project locally.

##### Clone the repository
git clone https://github.com/BhaskarKumar7/MakerSharkSearch.git

##### Navigate to the project directory
cd {project-location}

##### Build the project
mvn clean install

##### Run the application
mvn spring-boot:run

## Usage
This project has got single Api
- [POST] http://localhost:9988/api/supplier
	- This end point takes query parameters values as filters to fetch  list of manufacters based on the search criteria accordingly.

- [SWAGGER END POINT] http://localhost:9988/swagger-ui/index.html

## Configuration
##### Changes need to be done in application.properties file
- spring.datasource.username={your db username}
- spring.datasource.password={your db password}
- spring.datasource.url=jdbc:postgresql://localhost:{your db port no}/{data base name}

## Running the Tests
- Written test cases for controller and service using junit5 and Mockito
- mvn test

## Built With
- spring boot version (3.2.5)
- swagger ui version (2.5.0)
- postgresql version 15
- jakarta validation api
- spring tool suite IDE



