import java.util.*;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class EmployeeManager {
    private Map<Integer, Employee> employeeData;

    public EmployeeManager(){
        this.employeeData = new HashMap<>();
    }

    public void addEmployee(Employee employee){
        employeeData.put(employee.getId(), employee);
    }

    public Optional<Employee> getEmployeeById(int id){
        return Optional.ofNullable(employeeData.get(id));
    }

    public void loadDataFromCsv(String filePath){
        try{
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for(int i=1; i<lines.size(); i++){
                String[] data = lines.get(i).split(",");

                if(data.length == 4){
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String department = data[2].trim();
                    Double salary = Double.parseDouble(data[3].trim());

                    addEmployee(new Employee(id,name,department,salary));
                }
            }
            System.out.println("Successfully loaded data from "+ filePath);
        }catch (IOException e){
            System.out.println("Error reading the file: "+e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Error parsing number from CSV: "+ e.getMessage());
        }
    }

    public List<Employee> getAllEmployee(){
        return new ArrayList<>(employeeData.values());
    }

    public List<Employee> filterEmployeesBySalary(Double minSalary){
        return employeeData.values().stream()
                .filter(employee -> employee.getSalary().orElse(0.0) >= minSalary)
                .collect(Collectors.toList());
    }

    public List<Employee> sortEmployeesBySalary(){
        return employeeData.values().stream()
                .sorted(Comparator.comparingDouble(e -> e.getSalary().orElse(0.0)))
                .collect(Collectors.toList());
    }
}
