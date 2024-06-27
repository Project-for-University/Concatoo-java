package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import query.UserDAO;
import java.io.IOException;

public class DashboardController {

    @FXML
    private Button dashboardButton;

    @FXML
    private Button listUserButton;

    @FXML
    private Label LTotalUser;

    public void initialize() {
        // Set event handlers for buttons
        dashboardButton.setOnAction(event -> {
            try {
                loadNewScene("/ui/Dashboard.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        listUserButton.setOnAction(event -> {
            try {
                loadNewScene("/ui/UserTableView.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Ambil total user dari database dan tampilkan di label
        UserDAO userDAO = new UserDAO();
        int totalUsers = userDAO.getTotalUsers();
        LTotalUser.setText(String.valueOf(totalUsers));
    }

    private void loadNewScene(String fxmlPath) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage currentStage = (Stage) dashboardButton.getScene().getWindow();
        currentStage.setScene(new Scene(newRoot));
    }
}
