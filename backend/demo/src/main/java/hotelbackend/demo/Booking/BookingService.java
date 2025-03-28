package hotelbackend.demo.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

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

    public List<List<Date>> getAvailability(int roomid) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";
        List<List<Date>> availability = new ArrayList<>();

        String query = "SELECT checkin_date, checkout_date FROM booking WHERE room_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomid);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Date checkinDate = resultSet.getDate("checkin_date");
                Date checkoutDate = resultSet.getDate("checkout_date");
                List<Date> datePair = new ArrayList<>();
                datePair.add(checkinDate);
                datePair.add(checkoutDate);
                availability.add(datePair);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availability;
    }

    public boolean isAvailable(int roomid, Date startDate, Date endDate) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "SELECT COUNT(*) FROM booking " +
                       "WHERE room_id = ? " +
                       "AND NOT (checkout_date <= ? OR checkin_date >= ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomid);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void bookRoom(int roomid, String customerIdNumber, Date startDate, Date endDate) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        try {
            int customerid = getCustomerIdByIdNumber(customerIdNumber);

            if (!isAvailable(roomid, startDate, endDate)) {
                throw new IllegalArgumentException("Room is not available for the selected dates.");
            }

            String query = "INSERT INTO booking (room_id, customer_id, booking_date, checkin_date, checkout_date, status) " +
                           "VALUES (?, ?, CURDATE(), ?, ?, 'CONFIRMED')";

            try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, roomid);
                statement.setInt(2, customerid);
                statement.setDate(3, startDate);
                statement.setDate(4, endDate);

                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to book the room.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve customer ID.");
        }
    }
}
