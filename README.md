# Selenium Testing Suite

![Front page of tested website](front-page.png)

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Structure](#test-structure)
- [Contributing](#contributing)
- [License](#license)

## Overview

This Selenium Testing Suite is built using Java to provide automated testing for a specific web application. It utilizes the Selenium WebDriver for browser automation, enabling the execution of functional tests on various browsers.

## Prerequisites

Before you start, ensure you have the following installed on your system:

- Java Development Kit (JDK) 8 or above
- Maven 3.0 or higher
- Selenium WebDriver
- An IDE such as IntelliJ IDEA or Eclipse

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/selenium-test-suite.git
   ```
2. Navigate into the project directory:
   ```bash
   cd selenium-test-suite
   ```
3. Install dependencies using Maven:
   ```bash
   mvn clean install
   ```

## Configuration

1. **WebDriver Setup**: Ensure the appropriate WebDriver for your desired browser (e.g., ChromeDriver for Chrome, GeckoDriver for Firefox) is available on your system PATH or specify the WebDriver executable path in the tests.
2. **Test Configuration**: Modify `src/test/resources/test-config.properties` for any configuration details specific to your application (e.g., URLs, timeouts).

## Running Tests

To run the test suite, use the following Maven command:

```bash
mvn test
```

You can also specify individual tests or groups:

- To run a specific test class:
  ```bash
  mvn -Dtest=YourTestClass test
  ```
- To run a specific test method:
  ```bash
  mvn -Dtest=YourTestClass#yourTestMethod test
  ```

## Test Structure

The project is structured as follows:

```
selenium-test-suite/
│
├── src/
│   ├── main/resources
│   │    │   └── data/
│   │    │   │       └── DataExcel.xlsx
│   │    │   └── drivers/
│   │    │         └── chromedriver.exe
│   │    │
│   │    └── test/java
│   │    ├── pages/
│   │    │       ├── HomePage.java
│   │    │       ├── PagoEnLineaPage.java
│   │    │       └── SucursalesPage.java
│   │    ├── tests/
│   │    │       └── CasosDePrueba.java
│   │    └── utilidades/
│   │           ├── ClaseBase.java
│   │           ├── ClaseDatos.java
│   │           └── ClaseProperties.java
│   └── setup.properties
│
├── pom.xml                               # Maven configuration file
├── testng.xml
└── README.md                             # This README file
```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a branch for your feature/bugfix
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Feel free to customize this README based on your specific project needs!
