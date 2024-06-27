import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/UserTableView.fxml"));
        Scene scene = new Scene(root, 1085, 536);
        scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setTitle("My Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
