package unsw.application;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DungeonScreen {

    private Stage stage;
    private String title;
    private DungeonController controller;
    private Scene scene;
    private MediaPlayer mediaPlayer;
    private BufferScreen nextScreen;
    private BufferScreen gameOver;
    private GoalScreen goalScreen;
    private PauseScreen pauseScreen;
    private InventoryScreen inventoryScreen;
    private GoalControllerLoader goalLoader;

    public DungeonScreen(Stage stage, String map) throws IOException {
        this.stage = stage;
        title = "Dungeon";

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);      
		goalLoader = new GoalControllerLoader();
		dungeonLoader.setGoalControllerLoader(goalLoader);
		
        String musicFile = "music/Dungeon_Theme.aac";  
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
                
        this.controller = dungeonLoader.loadController();
        controller.setDungeonScreen(this);
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        this.scene = new Scene(root);
        root.requestFocus();

        inventoryScreen = new InventoryScreen(stage);
        this.controller.setInventoryScreen(inventoryScreen);

        initGoalScreen(stage);
        
    }

    public void start() {
        mediaPlayer.play();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }
    public void next() {
        mediaPlayer.stop();
    	nextScreen.start();

    }
    public void end() {
        mediaPlayer.stop();
    	gameOver.start();
    	

    }
    public DungeonController getController() {
        return controller;
    }

	/**
	 * @param nextScreen the nextScreen to set
	 */
	public void setNextScreen(BufferScreen nextScreen) {
		this.nextScreen = nextScreen;
	}
    

    


	private void initGoalScreen(Stage stage) throws IOException {
    	goalScreen = new GoalScreen(stage, goalLoader);
        controller.setGoalScreen(goalScreen);
        goalScreen.getController().setDungeonScreen(this);
    }

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(BufferScreen gameOver) {
		this.gameOver = gameOver;
	}

	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
        this.controller.setPauseScreen(pauseScreen);
	}
    
}
