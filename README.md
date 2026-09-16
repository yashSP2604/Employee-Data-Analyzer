# Employee Data Analyzer

A Java console application built to manage, process, and analyze employee records. This project demonstrates core Java concepts including object-oriented programming, modern data processing, and concurrency.

## Core Features
* **Employee Modeling:** Securely encapsulates employee details like ID, name, department, and salary.
* **Data Management:** Uses a `HashMap` for fast, efficient employee storage and retrieval.
* **Stream API:** Utilizes Java Streams to gracefully filter high-earning employees and sort records dynamically.
* **Multithreading:** Processes multiple employee records concurrently using separate threads to simulate a high-performance environment.
* **Robust Error Handling:** Safely manages missing data using `Optional` and a custom `EmployeeNotFoundException`.

## Project Structure
* `Employee.java`: The core data blueprint representing a single employee.
* `EmployeeManager.java`: The control center for adding, finding, filtering, and sorting data.
* `EmployeeNotFoundException.java`: A custom exception to gracefully handle invalid data lookups.
* `EmployeeProcessor.java`: A custom class extending `Thread` to process records concurrently.
* `EmployeeDataAnalyzer.java`: The main entry point that executes the application.
