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
    	
        DungeonScreen spDungeon1 = new DungeonScreen(primaryStage, "tony.json");
        LevelScreen spLevel1Screen = new LevelScreen(primaryStage, "Level 1", spDungeon1);
        
        DungeonScreen spDungeon2 = new DungeonScreen(primaryStage, "boulders.json");
        LevelScreen spLevel2Screen = new LevelScreen(primaryStage, "Level 2", spDungeon2);
        
        spDungeon1.setNextScreen(spLevel2Screen);
        GoalScreen spGoalScreen1 = new GoalScreen(primaryStage);
        GoalScreen spGoalScreen2 = new GoalScreen(primaryStage);
        
        //InventoryScreen spInventoryScreen = new InventoryScreen(primaryStage);
        
        //GoalScreen goalScreen = new GoalScreen(primaryStage);
        //DungeonScreen dungeonScreen1 = new DungeonScreen(primaryStage, "doorMaze.json");
        mainScreen.getController().setDungeonScreen(spDungeon1);
        mainScreen.getController().setSinglePlayerScreen(spLevel1Screen);
        mainScreen.getController().setMainScreen(mainScreen);
        
        spDungeon1.getController().setGoalScreen(spGoalScreen1);
        spDungeon2.getController().setGoalScreen(spGoalScreen2);
       // spDungeon1.getController().setInventoryScreen(spInventoryScreen);
       // spDungeon2.getController().setInventoryScreen(spInventoryScreen);
        spGoalScreen1.getController().setDungeonScreen(spDungeon1);
        spGoalScreen2.getController().setDungeonScreen(spDungeon2);
        //dungeonScreen1.getController().setGoalScreen(goalScreen);
        //goalScreen.getController().setDungeonScreen(dungeonScreen1);
        mainScreen.start();    
    }

    public static void main(String[] args) {
        launch(args);
    }

}