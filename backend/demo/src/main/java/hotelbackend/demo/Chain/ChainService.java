package hotelbackend.demo.Chain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChainService {

    public HotelChain getChainByID(int chainId) {
        HotelChain chain = null;
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT chain_id, chain_name, chain_address, number_of_hotels, email_addresses, phone_numbers FROM hotel_chain WHERE chain_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, chainId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                chain = new HotelChain();
                chain.setChainId(resultSet.getInt("chain_id"));
                chain.setChainName(resultSet.getString("chain_name"));
                chain.setChainAddress(resultSet.getString("chain_address"));
                chain.setNumberOfHotels(resultSet.getInt("number_of_hotels"));
                chain.setEmailAddresses(resultSet.getString("email_addresses"));
                chain.setPhoneNumbers(resultSet.getString("phone_numbers"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chain;
    }

    public List<String> getAllChainNames() {
        List<String> chainNames = new ArrayList<>();
        String jdbcURL = "jdbc:mysql://34.95.43.176:3306/HotelDB?useSSL=false";
        String dbUser = "root";
        String dbPassword = "AlecSam2025";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT chain_name FROM hotel_chain";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                chainNames.add(resultSet.getString("chain_name"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chainNames;
    }
}
