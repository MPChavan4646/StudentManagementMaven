# Student and Course Management System

This is a simple Spring Boot application that manages students and courses. It provides CRUD operations for students and courses, with the relationship that a student can be enrolled in multiple courses. The application uses H2 database for persistence.

## Features

- **CRUD Operations** for Students and Courses
- **Student-Course relationship** (Many-to-Many)
- **Validation and Exception Handling**
- **Logging**
- **Unit Tests** for Controller and Service layers

## Tech Stack

- **Backend**: Spring Boot, Spring Data JPA, H2 Database
- **Testing**: JUnit 5, Mockito, Spring Boot Test
- **API Documentation**: Postman (Optional)

## Requirements

- Java 11 or higher
- Maven 3.x
- Spring Boot 2.x
- H2 Database

## API Endpoints

### Student Endpoints

1. **Get all students**

   - **Endpoint**: `GET /api/students`
   - **Response**: List of all students.

2. **Get a student by ID**

   - **Endpoint**: `GET /api/students/{id}`
   - **Response**: Details of the student.

3. **Create a new student**

   - **Endpoint**: `POST /api/students`
   - **Request body**: JSON object with `studentName` and `studentAge`.
   - **Response**: Newly created student.

4. **Update student details**

   - **Endpoint**: `PUT /api/students/{id}`
   - **Request body**: Updated student details.
   - **Response**: Updated student.

5. **Delete a student**
   - **Endpoint**: `DELETE /api/students/{id}`
   - **Response**: Confirmation of student deletion.

### Course Endpoints

1. **Get all courses**

   - **Endpoint**: `GET /api/courses`
   - **Response**: List of all courses.

2. **Create a new course**

   - **Endpoint**: `POST /api/courses`
   - **Request body**: JSON object with `courseName`.
   - **Response**: Newly created course.

3. **Update course details**

   - **Endpoint**: `PUT /api/courses/{id}`
   - **Request body**: Updated course details.
   - **Response**: Updated course.

4. **Delete a course**
   - **Endpoint**: `DELETE /api/courses/{id}`
   - **Response**: Confirmation of course deletion.

## Database Configuration

This project uses an in-memory **H2 Database** for simplicity. You can find the database configurations in `src/main/resources/application.properties`. The default H2 configuration is set to use a file-based database.

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sam
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Sample Data (data.sql)

Sample data is provided for `Course`, `Student`, and the `Student_Course` relationship in `src/main/resources/data.sql`.

```sql
-- Courses
INSERT INTO Course (course_id, course_name) VALUES
(1, 'B.Tech'),
(2, 'MCA'),
(3, 'BBA');

-- Students
INSERT INTO Student (student_id, student_name, student_age) VALUES
(1, 'Alice Johnson', 25),
(2, 'Bob Smith', 20),
(3, 'Carol Davis', 30),
(4, 'David Wilson', 40),
(5, 'Eve Brown', 25),
(6, 'Frank Miller', 28),
(7, 'Grace Taylor', 26),
(8, 'Henry And', 22),
(9, 'Ivy Thomas', 23),
(10, 'Jack White', 21);

-- Student_Course (Many-to-Many Relationship)
INSERT INTO Student_Course (student_id, course_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(6, 3);
```

## How to Run the Application

1. Build the project with Maven:

   ```bash
   mvn clean install
   ```

2. Run the application:

   ```bash
   mvn spring-boot:run
   ```

3. The application will be accessible at `http://localhost:8080`.

4. You can access the H2 database console at `http://localhost:8080/h2-console`.

   - JDBC URL:

## Running Unit Tests

To run unit tests for this application, use Maven:

```bash
mvn test
```
