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

    public boolean updateEmployee(int employeeId, String employeeName, String employeeAddress, int SIN, String position) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "UPDATE employee SET employee_name = ?, employee_address = ?, SIN = ?, employee_position = ? WHERE employee_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (SIN <= 0) {
                throw new SQLException("Invalid SIN value. SIN must be a positive integer.");
            }

            statement.setString(1, employeeName);
            statement.setString(2, employeeAddress);
            statement.setInt(3, SIN); // Ensure SIN is passed as an integer
            statement.setString(4, position);
            statement.setInt(5, employeeId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
