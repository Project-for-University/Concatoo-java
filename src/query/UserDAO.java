package query;

import db.DatabaseConnector;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id_user, name, phonenumber, email, avatar, status, role FROM user";

        try (Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String idUser = resultSet.getString("id_user");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phonenumber");
                String email = resultSet.getString("email");
                String avatar = resultSet.getString("avatar");
                String status = resultSet.getString("status");
                String role = resultSet.getString("role");

                User user = new User(idUser, name, phoneNumber, email, avatar, status, role);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int getTotalUsers() {
        String query = "SELECT COUNT(*) AS total FROM user";
        try (Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // Jika terjadi kesalahan, kembalikan 0
    }

    public void updateUserStatus(String idUser, String newStatus) {
        String query = "UPDATE user SET status = ? WHERE id_user = ?";

        try (Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newStatus);
            statement.setString(2, idUser);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
