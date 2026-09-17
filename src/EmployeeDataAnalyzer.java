import java.util.List;

void main() {
    EmployeeManager employeeManager = new EmployeeManager();

    //Loading Employee Data
    employeeManager.loadDataFromCsv("employees.csv");

    //Simulate multithreading: processing employee by id.
    Thread processor1 = new EmployeeProcessor(employeeManager, 1);
    Thread processor2 = new EmployeeProcessor(employeeManager, 2);
    Thread processor3 = new EmployeeProcessor(employeeManager, 5);

    processor1.start();
    processor2.start();
    processor3.start();

    try {
        processor1.join();
        processor2.join();
        processor3.join();
    } catch (InterruptedException e) {
        IO.println("Error waiting for thread completion: " + e.getMessage());
    }

    //filtering employees by salary.
    IO.println("\nEmployees with salary >= 70000");
    List<Employee> highEarners = employeeManager.filterEmployeesBySalary(70000.0);
    highEarners.forEach(System.out::println);

    //sorted employee by
    IO.println("\nEmployees sorted by salary:");
    List<Employee> sortedEmployees = employeeManager.sortEmployeesBySalary();
    sortedEmployees.forEach(System.out::println);
}
