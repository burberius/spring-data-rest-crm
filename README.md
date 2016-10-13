# Spring Data Rest CRM
Little example project, a simple CRM developed with Spring Data Rest

## Description

## Run
You can start the CRM with the following command:
```java
# mvn clean spring-boot:run
```
Wait a view seconds and you will see an output like
```
....Started CrmApplication in 15.368 seconds (JVM running for 25.137)
```

Now you can point your browser to http://localhost:8080 and surf the rest endpoints 
via the integrated [HAL browser](https://github.com/mikekelly/hal-browser).

## User Stories
Some user story example implementations can be found in the tests folder (*src/test/java*)
under the package *net.troja.application*:
* UserStory1Test - as an audiologist I want to create my customers
* UserStory2Test - as an audiologist I want to create appointments with a customer
* UserStory3Test - as an audiologist I want to create appointments with a customer
* UserStory4Test - as an audiologist I want to see an overview of the next week’s appointments
* UserStory5Test - as a customer I want to see my next appointment
* UserStory6Test - as a customer I want to rate my last appointment

## Tests
To run the tests, execute the following command:
```java
# mvn clean test
```