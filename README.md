# Nbyula Automation Framework

This repository contains automated test scripts for the Nbyula web application using Java, Selenium, and TestNG.

**GitHub Repository** : https://github.com/karnikaram099/nbyula-automation-tests.git

## Tech Stack
1. Java 21
2. Selenium 4.12.0
3. TestNG 7.8.0
4. WebDriverManager 5.5.3
5. Maven - 3.9.6
6. Extent Reports 5.1.1
7. Logback Logging Framework

## Prerequisites
1. Install Java 21
2. Install Maven
3. Install Google Chrome (latest version)
4. Install Chromedriver (handled via WebDriverManager)

## Running Tests
**Run all tests using Maven:**
mvn test
**Run specific TestNG suite:**
mvn test -DsuiteXmlFile=testng.xml
**Run tests in different browsers:**
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox

## Reporting
Test execution reports are generated using Extent Reports and can be found in:
/test-output/extent-report.html

## CI/CD Integration
The project is integrated with Jenkins for continuous testing. To set up Jenkins:

1. Install Jenkins and add the Maven Plugin.
2. Create a new Freestyle Project.
3. Link this Git repository under Source Code Management.
4. Under Build Steps, add:
mvn clean test
5. Run the build and check reports.

**License**
This project is for Assessment testing purposes.

# nbyula-automation-tests