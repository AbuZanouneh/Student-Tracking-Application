# Student-Tracking-Application
This is a demo project to learn how to integrate Spring Boot with JavaServer Faces (JSF). It shows how these two technologies can work together in a web application.

# Prerequisites
Before you begin, ensure you have met the following requirements:

* Java Development Kit (JDK) 8 or higher
* Apache Maven (for building the Spring Boot project)
* JavaServer Faces (JSF) Setup (configured in your IDE)
* Database Server (e.g., MySQL) (if not using H2)
* Postman (optional, for API testing)

# Database Setup
To set up the database, you must first create it using the provided SQL file:

1. Locate the `student_tracker_db.sql` file in the project folder.
2. Run the SQL script in your database management system to create the necessary tables and data.

# Getting Started

1. Clone the Repository
   If you haven't cloned the repository yet, open your terminal and run:
   
   ``` bash
       git clone https://github.com/AbuZanouneh/student-tracking-app-springboot-jsf.git


2. Navigate to the Project Directory

   ``` bash
       git clone https://github.com/AbuZanouneh/student-tracking-app-springboot-jsf.git
   
3. Add or replace the following lines in the src/main/resources/application.properties file with your MySQL credentials:

    ``` java
        # MySQL Database Configuration
        spring.application.name=employee-management
        spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker?useSSL=false&serverTimezone=UTC
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        # spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true

4. Running the Application
   
You can access it at the following -- URL: http://localhost:8080/ui/home.xhtml

5. Login Credentials

To access the system, use the following credentials:

- **Username:** admin
- **Password:** admin123

# Technologies Used
  * Backend:
     * Spring Boot
     * Spring Data JPA
     * Spring Security
     * Maven
     * JavaServer Faces (JSF)
     * MySQL (Optional)
  * Frontend:
     * JavaServer Faces (JSF)
  * Others:
     * Git (Optional)
     * Postman (Optional)

## Feel free to explore the application!
