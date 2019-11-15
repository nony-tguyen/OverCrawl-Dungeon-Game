package unsw.application;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	MainScreen mainScreen = new MainScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage, "doorMaze.json");
        mainScreen.getController().setDungeonScreen(dungeonScreen);
        mainScreen.getController().setMainScreen(mainScreen);
        mainScreen.start();    
    }

    public static void main(String[] args) {
        launch(args);
    }

}
