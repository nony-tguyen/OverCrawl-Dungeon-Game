package unsw.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScreen {

    private Stage stage;
    private String title;
    private MainController controller;
    private Scene scene;

    public MainScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Overcrawl";

        controller = new MainController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainMenu.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root, 600, 400);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public MainController getController() {
        return controller;
    }
}
