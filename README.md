# Data-Management-System-for-a-University
University Data Management System
This project is a comprehensive data management system designed to handle various aspects of managing data in a university. It includes the design and implementation of a relational database, SQL and PLSQL scripts, automation scripts using Bash, a Java application, and integration of these components.

Database Schema and Mapping
The relational database schema for the university system consists of tables for students, courses, departments, and grades. The tables are designed with proper normalization and relationships between entities. The schema includes foreign keys to maintain referential integrity and ensure data consistency.

Tables
Student: Contains information about students, including student ID, first name, last name, address, email, gender, phone, and department ID.
Department: Stores department details such as department ID, department name, and manager name.
Course: Holds information about courses offered, including course ID, course name, description, credit hours, and department ID.
Student_Course: Represents the relationship between students and courses, storing information such as grades, student ID, course ID, and date.
SQL Script
The SQL script provided creates the necessary tables for the university system and establishes primary and foreign key relationships between them. It ensures data integrity and enables efficient data storage and retrieval.

PLSQL Procedure and Function
The PLSQL components of the system include a procedure and a function:

PLSQL Procedure (PROJECT.update_info): This procedure is responsible for updating student information. It takes parameters for student details such as ID, name, gender, address, email, phone, and department. The procedure utilizes an UPDATE statement to modify the corresponding student record in the STUDENT table.

PLSQL Function (PROJECT.calc_gpa): This function calculates the GPA (Grade Point Average) based on the provided student ID. It employs a SELECT statement with JOINs and aggregation to calculate grade points and total credit hours. Based on the calculated GPA, it determines the corresponding letter grade ('A', 'B', 'C', or 'F') using conditional logic.

Bash Scripts for Automation
The automation scripts included in the project are:

Disk Space Monitoring Script: This Bash script monitors disk space using the df and awk commands. It includes conditional checks to send alerts when disk space exceeds a predefined threshold.

Scheduled Anomaly Checking Script: The anomaly checking script is scheduled using the Windows Task Scheduler. A scheduled task is configured to execute the script at specified intervals, enabling regular anomaly checks.

Oracle Database Backup Script: This Bash script is designed for Oracle database backup. It uses exe to create a backup file with a timestamp for uniqueness. Appropriate permissions are granted from sys to enable the backup process.

Java Application
The Java application developed as part of the project provides a user-friendly interface for managing university data. It includes the following functionalities:

Student Management: Methods for adding, updating, and deleting students. The application maintains a TableView for displaying student information and performs input validation to ensure data integrity.

Course Management: Functionality for adding, updating, and deleting courses. The application utilizes SQL queries and user interactions to ensure proper management of course-related data.

Department Management: Methods for department-related operations, including adding, updating, and deleting departments. SQL queries are executed to handle department-related functionalities.

Grades Management: The application includes a procedure for managing student grades. SQL queries are employed to handle grade-related functionalities.

Reports: The Java application provides detailed student and department reports. Complex SQL queries with JOIN operations are executed to fetch and display data in a TableView. The application also utilizes a PLSQL function to obtain GPA information.