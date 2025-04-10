package hotelbackend.demo.Renting;

import java.sql.*;
import org.springframework.stereotype.Service;
import hotelbackend.demo.Booking.BookingService;

@Service
public class RentingService {

    public void rentRoom(int customerId, int roomId, Date startDate, Date endDate, boolean direct) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        if (direct){
            BookingService bookingService = new BookingService();
            bookingService.bookRoomFromRent(roomId, customerId, startDate, endDate);
        }

        String insertRentingQuery = "INSERT INTO renting (room_id, customer_id, start_date, end_date, payment_status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            try (PreparedStatement rentingStatement = connection.prepareStatement(insertRentingQuery, Statement.RETURN_GENERATED_KEYS)) {
                rentingStatement.setInt(1, roomId);
                rentingStatement.setInt(2, customerId);
                rentingStatement.setDate(3, startDate);
                rentingStatement.setDate(4, endDate);
                rentingStatement.setString(5, "pending");

                rentingStatement.executeUpdate();
            }
        }
    }

    public void convertBooking(int bookingId) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String selectBookingQuery = "SELECT room_id, customer_id, checkin_date, checkout_date FROM booking WHERE booking_id = ?";
        String updateBookingStatusQuery = "UPDATE booking SET status = 'converted' WHERE booking_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            try (PreparedStatement selectBookingStatement = connection.prepareStatement(selectBookingQuery)) {
                selectBookingStatement.setInt(1, bookingId);

                try (ResultSet resultSet = selectBookingStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int roomId = resultSet.getInt("room_id");
                        int customerId = resultSet.getInt("customer_id");
                        Date checkinDate = resultSet.getDate("checkin_date");
                        Date checkoutDate = resultSet.getDate("checkout_date");

                        rentRoom(customerId, roomId, checkinDate, checkoutDate, false);

                        try (PreparedStatement updateBookingStatusStatement = connection.prepareStatement(updateBookingStatusQuery)) {
                            updateBookingStatusStatement.setInt(1, bookingId);
                            updateBookingStatusStatement.executeUpdate();
                        }
                    }
                }
            }
        }
    }

    public void updatePayment(int rentingId, int amount) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String updatePaymentQuery = "UPDATE renting SET payment_status = ? WHERE renting_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            try (PreparedStatement updatePaymentStatement = connection.prepareStatement(updatePaymentQuery)) {
                String paymentStatus = "processed : amount " + amount;
                updatePaymentStatement.setString(1, paymentStatus);
                updatePaymentStatement.setInt(2, rentingId);

                updatePaymentStatement.executeUpdate();
            }
        }
    }
}
