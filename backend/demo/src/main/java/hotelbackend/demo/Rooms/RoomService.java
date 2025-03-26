package hotelbackend.demo.Rooms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import hotelbackend.demo.Hotel.Hotel;
import hotelbackend.demo.Hotel.HotelService;
public class RoomService {

    public List<Room> FilterRooms(String chain, int maxPrice, int minPrice, int capacity, String city, String state, int rating) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        List<Room> rooms = new ArrayList<>();
        HotelService hotelService = new HotelService();
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
                    rooms.add(room);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }
}
