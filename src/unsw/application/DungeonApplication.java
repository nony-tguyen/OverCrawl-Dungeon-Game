package unsw.application;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
	//private multilayerMaps

    @Override
    public void start(Stage primaryStage) throws IOException {
    	MainScreen mainScreen = new MainScreen(primaryStage);
        DungeonScreen dungeonScreen1 = new DungeonScreen(primaryStage, "advanced.json");
        LevelScreen singlePlayerScreen = new LevelScreen(primaryStage, "Level 2", dungeonScreen1);
        //GoalScreen goalScreen = new GoalScreen(primaryStage);
        //DungeonScreen dungeonScreen1 = new DungeonScreen(primaryStage, "doorMaze.json");
        mainScreen.getController().setDungeonScreen(dungeonScreen1);
        mainScreen.getController().setSinglePlayerScreen(singlePlayerScreen);
        mainScreen.getController().setMainScreen(mainScreen);
        //dungeonScreen1.getController().setGoalScreen(goalScreen);
        //goalScreen.getController().setDungeonScreen(dungeonScreen1);
        mainScreen.start();    
    }

    public static void main(String[] args) {
        launch(args);
    }

}