import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.Level;

public class EmployeeDataAnalyzer {
    private static final Logger logger = Logger.getLogger(EmployeeDataAnalyzer.class.getName());
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();

        //Loading Employee Data
        logger.info("Starting up Employee Data Analyzer...");
        employeeManager.loadDataFromCsv("employees.csv");

        /*
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

         */

        // 2. Process records concurrently using a Thread Pool
        logger.info("\n----Processing Employees Concurrently----");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new EmployeeProcessor(employeeManager, 1));
        executor.submit(new EmployeeProcessor(employeeManager, 2));
        executor.submit(new EmployeeProcessor(employeeManager, 5));

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
            logger.info("All concurrent processing finished successfully.");
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE,"Error waiting for thread completion: ", e);
        }


        //filtering employees by salary.
        IO.println("\nEmployees with salary >= 70000");
        List<Employee> highEarners = employeeManager.filterEmployeesBySalary(70000.0);
        highEarners.forEach(System.out::println);

        //sorted employee by
        IO.println("\nEmployees sorted by salary:");
        List<Employee> sortedEmployees = employeeManager.sortEmployeesBySalary();
        sortedEmployees.forEach(System.out::println);


        //Advanced Analytics
        System.out.println("\n ----Advanced Business Analytics----");

        System.out.printf("Average Company Salary: $%.2f%n", employeeManager.calAvgSalary());

        System.out.println("\nEmployees grouped by Department: ");
        Map<String, List<Employee>> grpByDept = employeeManager.groupEmpByDept();
        grpByDept.forEach((department, empList) -> {
            System.out.println(department + ":");
            empList.forEach(emp -> System.out.println(" -" + emp.getName()));
        });

        System.out.println("\nHighest Paid Employee per Department:");
        Map<String, Optional<Employee>> highestPaid = employeeManager.getHighestPaidByDept();
        highestPaid.forEach((department, empOpt) -> empOpt.ifPresent(emp ->
                System.out.println(department + ": " + emp.getName() + "($" + emp.getSalary().orElse(0.0) + ")")
        ));
    }
}
