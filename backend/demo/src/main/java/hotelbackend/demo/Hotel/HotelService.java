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

    public boolean updateHotel(int hotelId, int chainId, String hotelName, int rating, String hotelAddress, String city, String state, int amountOfRooms, String contactEmail, String contactPhone, int managerId) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String query = "UPDATE hotel SET chain_id = ?, hotel_name = ?, rating = ?, hotel_address = ?, city = ?, state = ?, amount_of_rooms = ?, contact_email = ?, contact_phone = ?, manager_id = ? WHERE hotel_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, chainId);
            statement.setString(2, hotelName);
            statement.setInt(3, rating);
            statement.setString(4, hotelAddress);
            statement.setString(5, city);
            statement.setString(6, state);
            statement.setInt(7, amountOfRooms);
            statement.setString(8, contactEmail);
            statement.setString(9, contactPhone);
            statement.setInt(10, managerId);
            statement.setInt(11, hotelId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public void addHotel(int hotelId, int chainId, String hotelName, int rating, String hotelAddress, String city, String state, int amountOfRooms, String contactEmail, String contactPhone, int managerId) throws SQLException {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        String checkQuery = "SELECT COUNT(*) FROM hotel WHERE hotel_id = ?";
        String insertQuery = "INSERT INTO hotel (hotel_id, chain_id, hotel_name, rating, hotel_address, city, state, amount_of_rooms, contact_email, contact_phone, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            // Check if the hotel already exists
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, hotelId);
                ResultSet resultSet = checkStmt.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    throw new SQLException("Hotel with this ID already exists.");
                }
            }

            // Insert the new hotel
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, hotelId);
                insertStmt.setInt(2, chainId);
                insertStmt.setString(3, hotelName);
                insertStmt.setInt(4, rating);
                insertStmt.setString(5, hotelAddress);
                insertStmt.setString(6, city);
                insertStmt.setString(7, state);
                insertStmt.setInt(8, amountOfRooms);
                insertStmt.setString(9, contactEmail);
                insertStmt.setString(10, contactPhone);
                insertStmt.setInt(11, managerId);
                insertStmt.executeUpdate();
            }
        }
    }
}
