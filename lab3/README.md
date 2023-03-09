# Review Questions

## a) Identify a couple of examples that use AssertJ expressive methods chaining.

### In A_EmployeeRepositoryTest.java  file:

```assertThat(fromDb).isNull();```

```assertThat(fromDb).isNotNull();```

```assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());```

### In B_EmployeeService_UnitTest file:

```assertThat(fromDb.getName()).isEqualTo("john");```

### In E_EmployeeRestControllerTemplateIT file:

```assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");```

## b) Identify an example in which you mock the behavior of the repository (and avoid involving a database). 

We can see this in the `B_EmployeeService_UnitTest.java`, because we define the expected behavior of the mock `EmployeeRepository` when the `findByName()`  or `findById()` or `findAll()`  methods are called with their parameters. We also use the `ẁhen()` method from Mockito to define the behavior and the `thenReturn()` method to specify the result that should be returned.

```Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);```

```Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));```

```Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);```

```Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findByName(name);```

As we mock the behavior of the `EmployeeRepository` we can test  the behavior of the `B_EmployeeService_UnitTest`.

## c) What is the difference between standard @Mock and @MockBean?

### @Mock:

Is part of the Mockito mocking framework and is used to create a mock object of a class or interface. It is typically used in unit testing to isolate the unit under test and simulate the behavior of dependencies.

### @MockBean:

Is part of the Spring Framework and is used to create a mock object of a Spring bean. It is typically used in integration testing to replace a real bean with a mock object.

Both the @Mock and @MockBean are annotations that are used in testing frameworks to create mock objects, but they are used in different contexts.

In summary, @Mock is ised to create mock objects of classes or interfaces in unit tests, while @MockBean is used to create mock objects of Spring beans in integration tests.

## d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The `application-integrationtest.properties` file is a configuration file used in Spring Boot applications to define properties specific to integration tests. Integration tests are typically used to test the interaction between  different parts of an application, such as multiple components or  services.

These tests often require a more complex setup tah unit tests, and may need to use additional infrastructure or resources, such as databases, message brokers, or external APIs.

## e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

### C:

It uses the `@WebMvcTest` annotation, that tells Spring to create a minimal application context taht only conatins the beans required for testing Spring MVC controllers. This means that you can test your controllers in isolation from the rest of the application, without having to load the entire application  context.

Also, it encapsulates all web application beans and makes them available for testing the providing and entry point to server-side testing.

### D:

Uses @SpringBootTest, to start the full web context.

This annotation is a Spring Boot annotation used to test Spring Boot applications. It can be used to load the entire Spring application context and run integration tests with the application's dependencies.

The  @SpringBootTest annotation tells Spirng Boot to look for a main configuration class( annoted with @SpringBootApplication ou @Configuration) and load the entire application context.

### E:

Similar to D, but instead of using the `@AutoConfigureMockMvc` annotation that is used to configure a `MockMvc` instance for testing Spirng MVC controllers, uses an API client.




