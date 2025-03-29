package hotelbackend.demo.Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelService {
    public List<Hotel> FilterHotel(String chain, String city, String state, Integer rating) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        List<Hotel> hotels = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM hotel h " +
                                                "JOIN hotel_chain hc ON h.chain_id = hc.chain_id WHERE 1=1");

        if (chain != null && !chain.isEmpty()) {
            query.append(" AND hc.chain_name = ?");
        }
        if (city != null && !city.isEmpty()) {
            query.append(" AND h.city = ?");
        }
        if (state != null && !state.isEmpty()) {
            query.append(" AND h.state = ?");
        }
        if (rating != null) {
            query.append(" AND h.rating >= ?");
        }

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            int paramIndex = 1;

            if (chain != null && !chain.isEmpty()) {
                statement.setString(paramIndex++, chain);
            }
            if (city != null && !city.isEmpty()) {
                statement.setString(paramIndex++, city);
            }
            if (state != null && !state.isEmpty()) {
                statement.setString(paramIndex++, state);
            }
            if (rating != null) {
                statement.setInt(paramIndex++, rating);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(resultSet.getInt("hotel_id"));
                hotel.setChainId(resultSet.getInt("chain_id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotel.setRating(resultSet.getInt("rating"));
                hotel.setHotelAddress(resultSet.getString("hotel_address"));
                hotel.setAmountOfRooms(resultSet.getInt("amount_of_rooms"));
                hotel.setContactEmail(resultSet.getString("contact_email"));
                hotel.setContactPhone(resultSet.getString("contact_phone"));
                hotel.setManagerId(resultSet.getInt("manager_id"));

                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public Hotel getHotelInfo(int hotelId) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "SELECT * FROM hotel WHERE hotel_id = ?";
        Hotel hotel = null;

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hotel = new Hotel();
                hotel.setHotelId(resultSet.getInt("hotel_id"));
                hotel.setChainId(resultSet.getInt("chain_id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotel.setRating(resultSet.getInt("rating"));
                hotel.setHotelAddress(resultSet.getString("hotel_address"));
                hotel.setAmountOfRooms(resultSet.getInt("amount_of_rooms"));
                hotel.setContactEmail(resultSet.getString("contact_email"));
                hotel.setContactPhone(resultSet.getString("contact_phone"));
                hotel.setManagerId(resultSet.getInt("manager_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    public boolean deleteHotel(int hotelId) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "DELETE FROM hotel WHERE hotel_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, hotelId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean updateHotel(int hotelId, String hotelName, int rating, String hotelAddress) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "UPDATE hotel SET hotel_name = ?, rating = ?, hotel_address = ? WHERE hotel_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, hotelName);
            statement.setInt(2, rating);
            statement.setString(3, hotelAddress);
            statement.setInt(4, hotelId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
