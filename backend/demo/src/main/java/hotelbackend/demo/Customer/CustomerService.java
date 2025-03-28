package hotelbackend.demo.Customer;

import java.sql.*;

public class CustomerService {
    
    public void addCustomer(String fullName, String address, String idType, String idNumber) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String checkQuery = "SELECT COUNT(*) FROM customer WHERE id_type = ? AND id_number = ?";
        String insertQuery = "INSERT INTO customer (id_type, customer_address, customer_name, id_number, registration_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            // Check if the customer already exists
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setString(1, idType);
                checkStmt.setString(2, idNumber);
                ResultSet resultSet = checkStmt.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    throw new SQLException("Customer already exists.");
                }
            }

            // Insert the new customer
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, idType);
                insertStmt.setString(2, address);
                insertStmt.setString(3, fullName);
                insertStmt.setString(4, idNumber);
                insertStmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
                insertStmt.executeUpdate();
            }
        }
    }

    public int getCustomerIdByIdNumber(String idNumber) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "SELECT customer_id FROM customer WHERE id_number = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idNumber);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("customer_id");
            } else {
                throw new SQLException("Customer with the given ID number does not exist.");
            }
        }
    }
}
