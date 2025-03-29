package hotelbackend.demo.Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotelbackend.demo.Booking.BookingService;
import hotelbackend.demo.Hotel.Hotel;
import hotelbackend.demo.Hotel.HotelService;

public class RoomService {

    public List<Room> FilterRooms(Date StartDate, Date EndDate, String chain, int maxPrice, int minPrice, int capacity, String city, String state, int rating) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";
    
        List<Room> rooms = new ArrayList<>();
        HotelService hotelService = new HotelService();
        BookingService bookingService = new BookingService();
    
        List<Hotel> hotels = hotelService.FilterHotel(chain, city, state, rating);
    
        if (hotels.isEmpty()) {
            return rooms; // Return empty list if no hotels match the criteria
        }
    
        String query = "SELECT r.room_id, r.hotel_id, r.price, r.view, r.amentities, r.extendable, r.capacity, r.damages " +
                       "FROM room r " +
                       "WHERE r.hotel_id = ? AND r.price BETWEEN ? AND ? AND r.capacity >= ?";
    
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
    
            for (Hotel hotel : hotels) {
                statement.setInt(1, hotel.getHotelId());
                statement.setInt(2, minPrice);
                statement.setInt(3, maxPrice);
                statement.setInt(4, capacity);
    
                ResultSet resultSet = statement.executeQuery();
    
                while (resultSet.next()) {
                    Room room = new Room();
                    room.setRoomId(resultSet.getInt("room_id"));
                    room.setHotelId(resultSet.getInt("hotel_id"));
                    room.setPrice(resultSet.getDouble("price"));
                    room.setView(resultSet.getString("view"));
                    room.setAmentities(resultSet.getString("amentities"));
                    room.setExtendable(resultSet.getBoolean("extendable"));
                    room.setCapacity(resultSet.getInt("capacity"));
                    room.setDamages(resultSet.getString("damages"));
                    room.setHotelName(hotel.getHotelName());
                    room.setHotelAddress(hotel.getHotelAddress());
    
                    // ✅ Use isAvailable method instead of manual check
                    boolean isAvailable = bookingService.isAvailable(room.getRoomId(), StartDate, EndDate);
    
                    if (isAvailable) {
                        rooms.add(room);
                    }
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return rooms;
    }


    public Room getRoomInfo(int roomId) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "SELECT r.room_id, r.hotel_id, r.price, r.view, r.amentities, r.extendable, r.capacity, r.damages " +
                       "FROM room r " +
                       "JOIN hotel h ON r.hotel_id = h.hotel_id " +
                       "WHERE r.room_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getInt("room_id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setPrice(resultSet.getDouble("price"));
                room.setView(resultSet.getString("view"));
                room.setAmentities(resultSet.getString("amentities"));
                room.setExtendable(resultSet.getBoolean("extendable"));
                room.setCapacity(resultSet.getInt("capacity"));
                room.setDamages(resultSet.getString("damages"));
                room.setHotelInfo(resultSet.getInt("hotel_id"));

                return room;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no room is found or an error occurs
    }

    public boolean deleteRoom(int roomId) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "DELETE FROM room WHERE room_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean updateRoom(int customerId, String view, String amentities, String damages) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "UPDATE room SET view = ?, amentities = ?, damages = ? WHERE room_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, view);
            statement.setString(2, amentities);
            statement.setString(3, damages);
            statement.setInt(4, customerId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
