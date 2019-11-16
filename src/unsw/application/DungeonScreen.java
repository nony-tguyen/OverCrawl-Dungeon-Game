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
    private LevelScreen nextScreen;

    public DungeonScreen(Stage stage, String map) throws IOException {
        this.stage = stage;
        title = "Dungeon 1";

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);

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

        /*
        stage.setScene(scene);
        stage.show();
        */
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

    public DungeonController getController() {
        return controller;
    }

	/**
	 * @param nextScreen the nextScreen to set
	 */
	public void setNextScreen(LevelScreen nextScreen) {
		this.nextScreen = nextScreen;
	}
    
}

