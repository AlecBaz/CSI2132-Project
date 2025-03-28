package hotelbackend.demo.view1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvailableRoomsPerAreaService {

    public List<AvailableRoomsPerArea> getAvailableRoomsPerArea() {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        List<AvailableRoomsPerArea> availableRoomsList = new ArrayList<>();

        String query = "SELECT city, state, available_rooms FROM AvailableRoomsPerArea";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                AvailableRoomsPerArea availableRooms = new AvailableRoomsPerArea();
                availableRooms.setCity(resultSet.getString("city"));
                availableRooms.setState(resultSet.getString("state"));
                availableRooms.setAvailableRooms(resultSet.getInt("available_rooms"));
                availableRoomsList.add(availableRooms);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableRoomsList;
    }
}
