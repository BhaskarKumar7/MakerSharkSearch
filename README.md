# Government funded training centers

## Description
This project is a minimum viable product for a registry of government funded training centers

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
git clone https://github.com/BhaskarKumar7/Traini8.git

##### Navigate to the project directory
cd {project-location}

##### Build the project
mvn clean install

##### Run the application
mvn spring-boot:run

## Usage
This project has got two Api's
- [POST] http://localhost:9181/api/trainingCenter
	- This end point validates and saves the training center information in the database
- [GET] http://localhost:9181/api/trainingCentersList
	- This end points fetches the training centers list from the database based on optional request parameters
- [SWAGGER END POINT] http://localhost:9181/swagger-ui/index.html

## Configuration
##### Changes need to be done in application.properties file
- spring.datasource.username={your db username}
- spring.datasource.password={your db password}
- spring.datasource.url=jdbc:postgresql://localhost:{your db port no}/Traini8

## Running the Tests
Test cases need to be implemented

## Built With
- spring boot version (3.2.5)
- swagger ui version (2.5.0)
- postgresql version 15
- jakarta validation api
- spring tool suite IDE



