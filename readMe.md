# Lolaflore Test Case
###Selenium Test Framework

This is the task prepared for Lolaflora test case request. It includes the steps of Sign In page happy path and negative test cases. Please check the instruction below for running the script.

(Java, Selenium WebDriver, JUnit5, Log4j, Allure Reports)

## Running the tests

In order to run the test please use the code below on the command prompt

- mvn clean test

##To See Report
- install allure from here https://docs.qameta.io/allure/#_installing_a_commandline

When the test execution finished, enter the command below inside the project in order to see Allure Results:

- allure serve target/allure-reports/

### Break down into end to end tests

JUnit5 is used in this case due to the easing mechanism of the display name, description, tags annotation and also concurrent execution option. All the steps are created separately, dynamically and understandably.

The plain language can be seen in the logs and allure reports and each clause are applied one by one to make it easier to understand and maintain.

```
 Fill the login form with valid credentials and see 'My Account' on the Dashboard
```

### Coding Style

There are OOP and POM principles are used in this coding.

```
OOP can be seen with the page classes, Base and driver classes
POM can be seen with the implementation of the webelements on their own specific webpages such as LoginPage, DashboardPage etc.
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Eren Can Degerli** - *Initial work*