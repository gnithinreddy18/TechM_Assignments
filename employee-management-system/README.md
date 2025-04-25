# Employee Management System

A comprehensive Employee Management System built with Spring Boot, JPA/Hibernate, and Spring Security. This application provides a RESTful API for managing employee data, department information, and user accounts.

## Features

- **Employee Management**: Create, read, update, and delete employee records
- **Department Management**: Create and manage departments and assign employees
- **User Authentication**: Secure API access with role-based authorization
- **RESTful API**: Well-designed API with comprehensive documentation
- **Data Validation**: Input validation for all API requests
- **Exception Handling**: Global exception handling with meaningful error messages
- **Database Support**: H2 database for development and PostgreSQL for production
- **OpenAPI Documentation**: API documentation with Swagger UI

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.3**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (Development)
- **PostgreSQL** (Production)
- **Maven**
- **Lombok**
- **Swagger/OpenAPI**

## Getting Started

### Prerequisites

- Java JDK 17 or later
- Maven 3.6 or later
- PostgreSQL (for production deployment)

### Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/employee-management-system.git
   cd employee-management-system
   ```

2. Build the application:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   # Development mode (uses H2 database)
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   
   # Test mode
   mvn spring-boot:run -Dspring-boot.run.profiles=test
   
   # Production mode (requires PostgreSQL)
   mvn spring-boot:run -Dspring-boot.run.profiles=prod
   ```

4. The application will be available at:
   ```
   http://localhost:8080
   ```

5. Access the Swagger UI documentation at:
   ```
   http://localhost:8080/swagger-ui.html
   ```

### Database Configuration

#### Development (H2 Database)

The development profile uses an in-memory H2 database with sample data loaded at startup.

- H2 Console: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:emsdev
- Username: sa
- Password: password

#### Production (PostgreSQL)

For production, configure your PostgreSQL database in `application-prod.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/empms
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## API Endpoints

### Authentication

- `POST /api/auth/register`: Register a new user
- `POST /api/auth/login`: Authenticate and login

### Employees

- `GET /api/employees`: Get all employees (paginated)
- `GET /api/employees/{id}`: Get employee by ID
- `GET /api/employees/email/{email}`: Get employee by email
- `GET /api/employees/department/{departmentId}`: Get employees by department
- `GET /api/employees/search`: Search employees by first name, last name, or email
- `POST /api/employees`: Create a new employee
- `PUT /api/employees/{id}`: Update an employee
- `DELETE /api/employees/{id}`: Delete an employee

### Departments

- `GET /api/departments`: Get all departments (paginated)
- `GET /api/departments/all`: Get all departments (list)
- `GET /api/departments/{id}`: Get department by ID
- `GET /api/departments/name/{name}`: Get department by name
- `POST /api/departments`: Create a new department
- `PUT /api/departments/{id}`: Update a department
- `DELETE /api/departments/{id}`: Delete a department

### Users

- `GET /api/users`: Get all users (paginated)
- `GET /api/users/{id}`: Get user by ID
- `GET /api/users/username/{username}`: Get user by username
- `PUT /api/users/{id}`: Update a user
- `DELETE /api/users/{id}`: Delete a user

## Security

The application uses role-based access control:

- `ROLE_USER`: Can view employee and department data
- `ROLE_ADMIN`: Can perform all operations including user management

Default user accounts (development mode):

- Admin: username=`admin`, password=`admin`
- User: username=`user`, password=`user`

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details. 