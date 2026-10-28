
# ShopCartAutomation

A UI test automation framework built with Java, Selenium WebDriver, TestNG, and Maven to test the SauceDemo e-commerce application.

## Project Overview

This project automates key e-commerce workflows, including login validation, shopping cart operations, and checkout functionality. It uses the Page Object Model (POM) to keep page locators and interactions separate from test logic.

## Tech Stack

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Jackson for JSON test data
- ExtentReports for test reporting
- Log4j2 for logging

## Project Structure

```text
ShopCartAutomation/
├── src/
│   ├── main/java/
│   │   ├── pageobjects/
│   │   └── reusable/
│   └── test/
│       ├── java/
│       │   ├── testcomponents/
│       │   ├── tests/
│       │   └── utilities/
│       └── resources/
│           └── testdata/
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Automated Test Scenarios

1. Verify login with valid credentials.
2. Verify login with invalid credentials.
3. Complete an end-to-end purchase.
4. Remove a product from the shopping cart.
5. Verify checkout validation when customer details are missing.
6. Verify checkout validation when the postal code is missing.

## Framework Features

- Page Object Model for maintainable test code.
- Reusable WebDriver setup and teardown.
- JSON-based login test data.
- Browser configuration through `GlobalData.properties`.
- ExtentReports test reporting.
- Screenshot capture on test failure.
- Log4j2 console and file logging.
- Maven and TestNG test execution.

## Prerequisites

- JDK 21
- Maven
- Google Chrome
- IntelliJ IDEA or another Java IDE

## How to Run

Clone the repository:

```bash
git clone https://github.com/Shakil278/ShopCartAutomation.git
cd ShopCartAutomation
```

Run the test suite:

```bash
mvn clean test
```

## Reports and Logs

After test execution, check the locally generated output:

- ExtentReports: `reports/index.html`
- Logs: `logs/automation.log`
- Failure screenshots: `screenshots/`

Generated reports, logs, and screenshots are excluded from Git using `.gitignore`.

## Application Under Test

[SauceDemo](https://www.saucedemo.com/)

## Author

Shakil Mahmood
