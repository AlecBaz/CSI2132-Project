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

    public List<Room> FilterRooms(Date StartDate, Date EndDate, String chain, int maxPrice, int minPrice, int capacity, String city, String state, int rating, int maxRooms) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";
    
        List<Room> rooms = new ArrayList<>();
        HotelService hotelService = new HotelService();
        BookingService bookingService = new BookingService();
    
        List<Hotel> hotels = hotelService.FilterHotel(chain, city, state, rating, maxRooms);
    
        if (hotels.isEmpty()) {
            return rooms; 
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

        return null;
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
            System.out.println("Deleted roomId: " + roomId);
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

    public void addRoom(int roomId, int hotelId, double price, String view, String amenities, boolean extendable, int capacity, String damages) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String checkQuery = "SELECT COUNT(*) FROM room WHERE room_id = ?";
        String insertQuery = "INSERT INTO room (room_id, hotel_id, price, view, amentities, extendable, capacity, damages) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, roomId);
                ResultSet resultSet = checkStmt.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    throw new SQLException("Room with this ID already exists.");
                }
            }

            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, roomId);
                insertStmt.setInt(2, hotelId);
                insertStmt.setDouble(3, price);
                insertStmt.setString(4, view);
                insertStmt.setString(5, amenities);
                insertStmt.setBoolean(6, extendable);
                insertStmt.setInt(7, capacity);
                insertStmt.setString(8, damages);
                insertStmt.executeUpdate();

                System.out.println("price: " + price + " view: " + view + " amenities: " + amenities + " extendable: " + extendable + " capacity: " + capacity + " damages: " + damages);
                System.out.println("roomId: " + roomId + " hotelId: " + hotelId);
            }
        }
    }

    public boolean updateRoom(int roomId, int hotelId, double price, String view, String amenities, boolean extendable, int capacity, String damages) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "UPDATE room SET price = ?, view = ?, amentities = ?, extendable = ?, capacity = ?, damages = ? WHERE room_id = ? AND hotel_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, price);
            statement.setString(2, view);
            statement.setString(3, amenities);
            statement.setBoolean(4, extendable);
            statement.setInt(5, capacity);
            statement.setString(6, damages);
            statement.setInt(7, roomId);
            statement.setInt(8, hotelId);

            System.out.println("price: " + price + " view: " + view + " amenities: " + amenities + " extendable: " + extendable + " capacity: " + capacity + " damages: " + damages);
            System.out.println("roomId: " + roomId + " hotelId: " + hotelId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
    }
}
