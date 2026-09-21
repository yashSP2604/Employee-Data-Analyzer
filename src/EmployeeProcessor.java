import java.util.Optional;

public class EmployeeProcessor implements Runnable {
    private EmployeeManager employeeManager;
    private int employeeId;

    public EmployeeProcessor(EmployeeManager employeeManager, int employeeId){
        this.employeeManager = employeeManager;
        this.employeeId = employeeId;
    }

    @Override
    public void run(){
        try{
            Optional<Employee> employee = employeeManager.getEmployeeById(employeeId);
            employee.ifPresentOrElse(
                    emp -> System.out.println("Processing Employee:"+ emp),
                    () -> System.out.println("Employee with ID "+employeeId+" not found.")
            );
        }catch(Exception e){
            System.out.println("Error processing employee Data: "+ e.getMessage());
        }
    }
}
