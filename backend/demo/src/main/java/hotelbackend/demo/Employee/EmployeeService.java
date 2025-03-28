package hotelbackend.demo.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeService {

    public boolean deleteEmployee(int employeeId) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "DELETE FROM employee WHERE employee_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, employeeId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
