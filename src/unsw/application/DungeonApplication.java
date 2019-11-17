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
        BufferScreen spLevel1Screen = new BufferScreen(primaryStage, "Level 1");
        spLevel1Screen.setNextLevel(spDungeon1);

        
        DungeonScreen spDungeon2 = new DungeonScreen(primaryStage, "sp/dungeon-2.json");
        BufferScreen spLevel2Screen = new BufferScreen(primaryStage, "Level 2");
        spLevel2Screen.setNextLevel(spDungeon2);
        
        DungeonScreen spDungeon3 = new DungeonScreen(primaryStage, "tony.json");
        BufferScreen spLevel3Screen = new BufferScreen(primaryStage, "Level 3");
        spLevel3Screen.setNextLevel(spDungeon3);
        
    	// Multiplayer dungeons
        DungeonScreen mpDungeon1 = new DungeonScreen(primaryStage, "mp/dungeon-2.json");
        BufferScreen mpLevel1Screen = new BufferScreen(primaryStage, "Level 1");
        mpLevel1Screen.setNextLevel(mpDungeon1);

        
        DungeonScreen mpDungeon2 = new DungeonScreen(primaryStage, "mp/dungeon-2.json");
        BufferScreen mpLevel2Screen = new BufferScreen(primaryStage, "Level 2");
        mpLevel2Screen.setNextLevel(mpDungeon2);
        
        DungeonScreen mpDungeon3 = new DungeonScreen(primaryStage, "tony.json");
        BufferScreen mpLevel3Screen = new BufferScreen(primaryStage, "Level 3");
        mpLevel3Screen.setNextLevel(mpDungeon3);

        BufferScreen gameFinished = new BufferScreen(primaryStage, "Congrats!");
        BufferScreen gameOver = new BufferScreen(primaryStage, "Game Over");
        gameFinished.setdApp(this);
        gameOver.setdApp(this);
        spDungeon1.setGameOver(gameOver);
        spDungeon2.setGameOver(gameOver);
        spDungeon3.setGameOver(gameOver);
        mpDungeon1.setGameOver(gameOver);
        mpDungeon2.setGameOver(gameOver);
        mpDungeon3.setGameOver(gameOver);
        


        //InventoryScreen spInventoryScreen = new InventoryScreen(primaryStage);
        
        //GoalScreen goalScreen = new GoalScreen(primaryStage);
        //DungeonScreen dungeonScreen1 = new DungeonScreen(primaryStage, "doorMaze.json");
        //mainScreen.getController().setDungeonScreen(spDungeon1);
        mainScreen.getController().setSinglePlayerScreen(spLevel1Screen);
        mainScreen.getController().setMultiPlayerScreen(mpLevel1Screen);
        mainScreen.getController().setMainScreen(mainScreen);
        
        // Connect the dungeons to the next screen
        spDungeon1.setNextScreen(spLevel2Screen); 
        spDungeon2.setNextScreen(spLevel3Screen);
        spDungeon3.setNextScreen(gameFinished);
        
        mpDungeon1.setNextScreen(mpLevel2Screen); 
        mpDungeon2.setNextScreen(mpLevel3Screen);
        mpDungeon3.setNextScreen(gameFinished);

        
        mainScreen.start();    
    }

    public static void main(String[] args) {
        launch(args);
    }

}