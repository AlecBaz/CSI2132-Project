package hotelbackend.demo.Employee;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();

    @DeleteMapping("/delete/{id}")
    public boolean deleteEmployee(@PathVariable int id) {
        try {
            return employeeService.deleteEmployee(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/edit/{id}")
    public boolean editEmployee(@PathVariable int id, @RequestBody Employee employee) {
        try {
            return employeeService.updateEmployee(
                id,
                employee.getEmployeeName(),
                employee.getEmployeeAddress(),
                employee.getSIN_num(),
                employee.getPosition(),
                employee.getHotelId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee) {
        try {
            System.out.println("Debug: Received JSON payload: " + employee); 
            System.out.println("Debug: Received SIN_num = " + employee.getSIN_num()); 
            employeeService.addEmployee(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeAddress(),
                employee.getSIN_num(),
                employee.getPosition(),
                employee.getHotelId()
            );
            System.out.println("Employee added successfully: " + employee);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding employee: " + employee);
        }
    }
}
