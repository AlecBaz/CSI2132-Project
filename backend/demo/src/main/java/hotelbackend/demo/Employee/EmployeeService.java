package hotelbackend.demo.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            System.out.println("Deleted employeeId: " + employeeId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean updateEmployee(int employeeId, String employeeName, String employeeAddress, int SIN, String position, int hotelId) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "UPDATE employee SET employee_name = ?, employee_address = ?, SIN = ?, employee_position = ?, hotel_id = ? WHERE employee_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, employeeName);
            statement.setString(2, employeeAddress);
            statement.setInt(3, SIN); // Ensure SIN is passed as an integer
            statement.setString(4, position);
            statement.setInt(5, employeeId);
            statement.setInt(6, hotelId);

            int rowsAffected = statement.executeUpdate();

            System.out.println("employeeName: " + employeeName + " employeeAddress: " + employeeAddress + " SIN: " + SIN + " position: " + position + " hotelId: " + hotelId);
            System.out.println("employeeId: " + employeeId);
            return rowsAffected > 0;
        }
    }

    public void addEmployee(int employeeId, String employeeName, String employeeAddress, int SIN, String position, int hotelId) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String checkQuery = "SELECT COUNT(*) FROM employee WHERE employee_id = ?";
        String insertQuery = "INSERT INTO employee (employee_id, employee_name, employee_address, SIN, employee_position, hotel_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            // Check if the employee already exists
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, employeeId);
                ResultSet resultSet = checkStmt.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    throw new SQLException("Employee with this ID already exists.");
                }
            }

            // Insert the new employee
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, employeeId);
                insertStmt.setString(2, employeeName);
                insertStmt.setString(3, employeeAddress);
                insertStmt.setInt(4, SIN);
                insertStmt.setString(5, position);
                insertStmt.setInt(6, hotelId);
                insertStmt.executeUpdate();

                System.out.println("employeeName: " + employeeName + " employeeAddress: " + employeeAddress + " SIN: " + SIN + " position: " + position + " hotelId: " + hotelId);
                System.out.println("employeeId: " + employeeId);
            }
        }
    }
}
