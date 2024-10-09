# Student-Tracking-Application
This is a demo project to learn how to integrate Spring Boot with JavaServer Faces (JSF). It shows how these two technologies can work together in a web application.

## Database Setup

To set up the database, you must first create it using the provided SQL file:

1. Locate the `student_tracker_db.sql` file in the project folder.
2. Run the SQL script in your database management system to create the necessary tables and data.

## Accessing the Application

Add or replace the following lines in the src/main/resources/application.properties file with your MySQL credentials:

    ``` java
        # MySQL Database Configuration
        spring.application.name=employee-management
        spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker?useSSL=false&serverTimezone=UTC
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        # spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true

You can access it at the following -- URL: http://localhost:8080/ui/home.xhtml

## Login Credentials

To access the system, use the following credentials:

- **Username:** admin
- **Password:** admin123

Feel free to explore the application!
