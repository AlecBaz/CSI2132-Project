package hotelbackend.demo.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    
    public List<List<Date>> getAvailability(int roomid) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";
        List<List<Date>> availability = new ArrayList<>();

        String query = "SELECT checkin_date, checkout_date FROM booking " +
                       "JOIN book_room ON booking.booking_id = book_room.booking_id " +
                       "WHERE book_room.room_id = ?";

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
}
