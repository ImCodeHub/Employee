# `Building a Employee Management System`
### Objective:
Develop a simple Employee Management System with a RESTful API using Spring Boot. The application should allow basic CRUD (Create, Read, Update, Delete) operations on employee data.

#### Requirements:

1. *Project Setup:*
   - Initialize a new Spring Boot project using Spring Initializr.
   - Include the following dependencies:
     - Spring Web
     - Spring Data JPA
     - ***H2 Database (for in-memory database)***
     - Spring Boot DevTools
     - Lombok (optional but recommended for reducing boilerplate code)

2. *Model:*
   - Create an Employee entity with the following fields:
     - id (Long, Primary Key)
     - firstName (String)
     - lastName (String)
     - email (String, unique)
     - department (String)

3. *Repository:*
   - Create a JPA repository interface for the Employee entity.

4. *Service:*
   - Implement a service layer that uses the repository to perform CRUD operations.
   - Methods to include:
     - createEmployee(Employee employee)
     - getAllEmployees()
     - getEmployeeById(Long id)
     - updateEmployee(Long id, Employee employeeDetails)
     - deleteEmployee(Long id)

5. *Controller:*
   - Create a REST controller with endpoints for each of the CRUD operations.
     - `POST` **/api/employee** to create a new employee.
     - `GET` **/api/employees** to retrieve all employees.
     - `GET` **/api/employee/{id}** to retrieve an employee by ID.
     - `PUT` **/api/udpate/{id}** to update an employee.
     - `DELETE` **/api/delete/{id}** to delete an employee.

6. *Exception Handling:*
   - Implement `Global exception` handling for the application to manage cases like:
     - Employee not found.
     - Duplicate email entries.
     - To validate E-mail format.

7. *Testing:*
   - Write unit tests for the service layer using JUnit and Mockito.
   - Write integration tests for the controller layer using MockMvc.

8. *Documentation:*
   - Use `Swagger/OpenAPI` for API documentation.
   - Add the `springdoc-openapi-ui` library to the list of your project dependencies (No additional configuration is needed):

```xml 
   <dependency>
         <groupId>org.springdoc</groupId>
         <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
         <version>2.5.0</version>
   </dependency>
```
   - Example of Swagger annotations:
```java
@PostMapping("employee")
@Operation(summary = "Add a new employee")
@ApiResponses(value = {
   @ApiResponse(responseCode = "201", description = "Employee created successfully"),
   @ApiResponse(responseCode = "400", description = "Bad request")
})
```
   - Use `http://localhost:8080/swagger-ui/index.html#/` to access the swagger UI for API documentation.
---

### Guidance:

- *Project Setup:*
  bash
  spring init --dependencies=web,data-jpa,h2,devtools,lombok employee-management
  
  Or use Spring Initializr web interface to generate the project.

- *Application Properties:*
  Configure `application.properties` for `H2 database:
  properties`
  spring.datasource.url=jdbc:h2:mem:testdb
  
  spring.datasource.driverClassName=org.h2.Driver
  
  spring.datasource.username=sa
  
  spring.datasource.password=password
  
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  
  spring.h2.console.enabled=true

  ```properties
   spring.application.name=Employee
   # H2 database
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   ```
  
  

