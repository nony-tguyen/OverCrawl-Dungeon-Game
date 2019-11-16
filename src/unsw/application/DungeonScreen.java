package unsw.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {

    private Stage stage;
    private String title;
    private DungeonController controller;
    private Scene scene;
    private GoalScreen goalScreen;

    public DungeonScreen(Stage stage, String map) throws IOException {
        this.stage = stage;
        title = "Dungeon 1";

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);

        this.controller = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        this.scene = new Scene(root);
        root.requestFocus();
        /*
        stage.setScene(scene);
        stage.show();
        */
        initGoalScreen(stage);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public DungeonController getController() {
        return controller;
    }
    
    private void initGoalScreen(Stage stage) throws IOException {
    	goalScreen = new GoalScreen(stage);
        controller.setGoalScreen(goalScreen);
        goalScreen.getController().setDungeonScreen(this);
    }
}