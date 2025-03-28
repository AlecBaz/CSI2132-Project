package hotelbackend.demo.Employee;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
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
}
