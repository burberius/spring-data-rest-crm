# Spring Data Rest CRM
Little example project, a simple CRM developed with Spring Data Rest.

## Description
This example project takes a very simple CRM system with just two entities (Customer and Appointment) as
base to showcase some spring boot components. The usage is shown with the implementation of six user stories,
describt later.

The project is based on the latest release of [Spring Boot](https://projects.spring.io/spring-boot/) (1.4.1)
and uses [Spring Boot Data JPA](https://spring.io/guides/gs/accessing-data-jpa/) for data mapping.
The data is stored in the underlaying [H2](http://www.h2database.com/) in memory database.
On top of all that, [Spring Data Rest](http://projects.spring.io/spring-data-rest/) provide access 
to the data via a REST frontend. For human access to the REST API the installed 
[HAL browser](https://github.com/mikekelly/hal-browser) extends and visualizes the frontend as browser
application.

This implementation provides 
[HATEOAS (Hypertext As The Engine Of Application State)](https://en.wikipedia.org/wiki/HATEOAS) the highest
level of maturity in the [Richardson Maturity Model](http://martinfowler.com/articles/richardsonMaturityModel.html).

## Lessons learned
### Backend
The backend implementation is super easy, no boilerplate code, just an interface for the repository. The
trickiest part are special queries!

### Client
The client code was more difficult! There is only few documentation for 
[Spring Traverson](http://docs.spring.io/autorepo/docs/spring-hateoas/0.20.x/reference/html/#client), which
can be used to navigate in and read from the REST API. After some learning this client is very powerful.

To create, update or delete one has to work with another tool, like in the test cases with the Spring 
TestRestTemplate in a normal client you can probably use the Spring RestTemplate. In some cases you can hand 
in object you want to save, in others you have to send a map with the fields. That handling is not very
intuitive!

It's sad there isn't a simple client that provides CRUD functionality and can work with the HATEOAS 
structure, yet.

## Run
You can start the CRM with the following command:
```shell
mvn clean spring-boot:run
```
Wait a view seconds and you will see an output like
```
....Started CrmApplication in 15.368 seconds (JVM running for 25.137)
```

Now you can point your browser to [http://localhost:8080/](http://localhost:8080/) and surf the rest 
endpoints via the integrated [HAL browser](https://github.com/mikekelly/hal-browser).

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
```shell
mvn clean test
```
