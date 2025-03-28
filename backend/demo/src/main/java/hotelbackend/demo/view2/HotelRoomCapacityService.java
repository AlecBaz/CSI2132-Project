package hotelbackend.demo.view2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelRoomCapacityService {

    public List<HotelRoomCapacity> getHotelRoomCapacity() {
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        List<HotelRoomCapacity> hotelRoomCapacityList = new ArrayList<>();

        String query = "SELECT hotel_id, hotel_name, total_capacity FROM HotelRoomCapacity";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                HotelRoomCapacity hotelRoomCapacity = new HotelRoomCapacity();
                hotelRoomCapacity.setHotelId(resultSet.getInt("hotel_id"));
                hotelRoomCapacity.setHotelName(resultSet.getString("hotel_name"));
                hotelRoomCapacity.setTotalCapacity(resultSet.getInt("total_capacity"));
                hotelRoomCapacityList.add(hotelRoomCapacity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotelRoomCapacityList;
    }
}
