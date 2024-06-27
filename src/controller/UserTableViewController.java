package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import query.UserDAO;
import model.User;

import java.io.IOException;

public class UserTableViewController {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> idUserColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> phoneNumberColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> avatarColumn;
    @FXML
    private TableColumn<User, String> statusColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableColumn<User, Void> actionColumn;

    @FXML
    private ImageView logoConcatoo;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button listUserButton;

    public void initialize() {
        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        avatarColumn.setCellValueFactory(new PropertyValueFactory<>("avatar"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        UserDAO userDAO = new UserDAO();
        ObservableList<User> userList = FXCollections.observableArrayList(userDAO.getAllUsers());
        userTable.setItems(userList);

        addButtonToTable();

        // Load image
        Image logoImage = new Image(getClass().getResourceAsStream("/images/logo.png"));
        logoConcatoo.setImage(logoImage);

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
    }

    private void addButtonToTable() {
        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<>() {
                    private final Button updateButton = new Button("Update Status");

                    {
                        updateButton.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            String newStatus =
                                    user.getStatus().equals("AKTIF") ? "NONAKTIF" : "AKTIF";
                            UserDAO userDAO = new UserDAO();
                            userDAO.updateUserStatus(user.getIdUser(), newStatus);
                            user.setStatus(newStatus);
                            getTableView().refresh();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(updateButton);
                        }
                    }
                };
                return cell;
            }
        };

        actionColumn.setCellFactory(cellFactory);
    }

    private void loadNewScene(String fxmlPath) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage currentStage = (Stage) userTable.getScene().getWindow();
        currentStage.setScene(new Scene(newRoot));
    }
}
