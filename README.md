# Employee Data Analyzer

An enterprise-grade Java application designed to process, analyze, and manage large sets of employee data efficiently. This project was built to demonstrate a strong foundation in core software engineering, clean system design, and scalable backend architecture.

Rather than relying on basic CRUD operations, this system simulates real-world production environments by implementing thread pools, thread-safe data structures, file parsing, and structured logging.

## 🚀 Key Features

* **High-Performance Concurrency:** Leverages Java's `ExecutorService` (Thread Pools) to process multiple employee records simultaneously, maximizing computational efficiency over manual thread creation.
* **Thread-Safe Architecture:** Utilizes a `ConcurrentHashMap` as the core in-memory database to ensure data integrity and prevent race conditions during parallel read/write operations.
* **Dynamic Data Ingestion:** Reads and parses employee records dynamically from an external `employees.csv` file using the `java.nio.file` API.
* **Advanced Business Analytics:** Implements the Java Streams API and `Collectors.groupingBy()` to instantly extract complex metrics, including company-wide average salaries and the highest-paid employees segmented by department.
* **Enterprise Traceability:** Integrates `java.util.logging.Logger` to track system execution lifecycles, replacing standard console outputs with professional, severity-based logs (INFO, WARNING, SEVERE).
* **Robust Error Handling:** Employs the `Optional` class and custom exceptions (`EmployeeNotFoundException`) to gracefully manage missing data without crashing the application.

## 🛠️ Technical Stack

* **Language:** Java (JDK 21+)
* **Concurrency:** `java.util.concurrent` (`ExecutorService`, `ConcurrentHashMap`, `Runnable`)
* **Data Processing:** Java Streams API, Lambda Expressions
* **I/O & Observability:** `java.nio.file`, `java.util.logging`

## 📂 Project Structure

* `Employee.java`: Encapsulated data model representing an individual employee.
* `EmployeeManager.java`: Core business logic handling dynamic CSV loading, thread-safe storage, and advanced Stream analytics.
* `EmployeeProcessor.java`: A `Runnable` task designed to execute concurrently within the thread pool, processing individual employee data safely.
* `EmployeeNotFoundException.java`: A custom exception built for granular error handling.
* `EmployeeDataAnalyzer.java`: The main entry point orchestrating the thread pool lifecycle, logging, and final analytics reporting.
* `employees.csv`: The external data source loaded at runtime.
