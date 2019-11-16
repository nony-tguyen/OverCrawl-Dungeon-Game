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
    	
        DungeonScreen spDungeon1 = new DungeonScreen(primaryStage, "sp/boulders-1.json");
        LevelScreen spLevel1Screen = new LevelScreen(primaryStage, "Level 1");
        spLevel1Screen.setNextLevel(spDungeon1);
        
        DungeonScreen spDungeon2 = new DungeonScreen(primaryStage, "boulders.json");
        LevelScreen spLevel2Screen = new LevelScreen(primaryStage, "Level 2");
        spLevel2Screen.setNextLevel(spDungeon2);
        
        DungeonScreen spDungeon3 = new DungeonScreen(primaryStage, "boulders.json");
        LevelScreen spLevel3Screen = new LevelScreen(primaryStage, "Level 3");
        spLevel3Screen.setNextLevel(spDungeon3);

        LevelScreen gameFinished = new LevelScreen(primaryStage, "Congratulations! You won the game!");
        
        // Connect the dungeons to the next screen
        spDungeon1.setNextScreen(spLevel2Screen); 
        spDungeon2.setNextScreen(spLevel3Screen);
        spDungeon3.setNextScreen(gameFinished);


        GoalScreen spGoalScreen1 = new GoalScreen(primaryStage);
        GoalScreen spGoalScreen2 = new GoalScreen(primaryStage);
        GoalScreen spGoalScreen3 = new GoalScreen(primaryStage);
       
        //InventoryScreen spInventoryScreen = new InventoryScreen(primaryStage);
        
        //GoalScreen goalScreen = new GoalScreen(primaryStage);
        //DungeonScreen dungeonScreen1 = new DungeonScreen(primaryStage, "doorMaze.json");
        //mainScreen.getController().setDungeonScreen(spDungeon1);
        mainScreen.getController().setSinglePlayerScreen(spLevel1Screen);
        mainScreen.getController().setMainScreen(mainScreen);
        
        spDungeon1.getController().setGoalScreen(spGoalScreen1);
        spDungeon2.getController().setGoalScreen(spGoalScreen2);
        spDungeon3.getController().setGoalScreen(spGoalScreen3);
       // spDungeon1.getController().setInventoryScreen(spInventoryScreen);
       // spDungeon2.getController().setInventoryScreen(spInventoryScreen);
        spGoalScreen1.getController().setDungeonScreen(spDungeon1);
        spGoalScreen2.getController().setDungeonScreen(spDungeon2);
        spGoalScreen3.getController().setDungeonScreen(spDungeon3);
        //dungeonScreen1.getController().setGoalScreen(goalScreen);
        //goalScreen.getController().setDungeonScreen(dungeonScreen1);
        mainScreen.start();    
    }

    public static void main(String[] args) {
        launch(args);
    }

}