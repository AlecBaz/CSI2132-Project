package hotelbackend.demo.Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelService {
    public List<Hotel> FilterHotel(String chain, String city, String state, Integer rating, Integer maxRooms) {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";
    
        List<Hotel> hotels = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM hotel WHERE 1=1");
        List<Object> params = new ArrayList<>();
    
        // Handle nested query for chain name
        if (chain != null && !chain.isEmpty()) {
            query.append(" AND chain_id = (SELECT chain_id FROM hotel_chain WHERE chain_name = ? LIMIT 1)");
            params.add(chain);
        }
    
        if (city != null && !city.isEmpty()) {
            query.append(" AND city = ?");
            params.add(city);
        }
    
        if (state != null && !state.isEmpty()) {
            query.append(" AND state = ?");
            params.add(state);
        }
    
        if (rating != null) {
            query.append(" AND rating >= ?");
            params.add(rating);
        }
    
        if (maxRooms != null) {
            query.append(" AND amount_of_rooms <= ?");
            params.add(maxRooms);
        }
    
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
    
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
    
            System.out.println("Executing query: " + statement);
    
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
    
        System.out.println("Found " + hotels.size() + " hotels matching the criteria.");
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
            System.out.println("Deleted hotelId: " + hotelId);
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

            System.out.println("rating:"+ rating + " hotelName: " + hotelName + " hotelAddress: " + hotelAddress + " city: " + city + " state: " + state + " amountOfRooms: " + amountOfRooms + " contactEmail: " + contactEmail + " contactPhone: " + contactPhone + " managerId: " + managerId);
            System.out.println("chainId: " + chainId + " hotelId: " + hotelId);
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

                System.out.println("rating:"+ rating + " hotelName: " + hotelName + " hotelAddress: " + hotelAddress + " city: " + city + " state: " + state + " amountOfRooms: " + amountOfRooms + " contactEmail: " + contactEmail + " contactPhone: " + contactPhone + " managerId: " + managerId);
                System.out.println("chainId: " + chainId + " hotelId: " + hotelId);
            }
        }
    }
}
