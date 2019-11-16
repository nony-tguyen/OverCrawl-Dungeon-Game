package unsw.application;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelScreen {

    private Stage stage;
    private String title;
    private LevelController controller;
    private MainScreen mainScreen;
    private DungeonScreen nextLevel;
    private Scene scene;
   

    public LevelScreen(Stage stage, String text) throws IOException {
        this.stage = stage;
        title = text;
        this.nextLevel = null;
        controller = new LevelController(text);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LevelView.fxml"));
        
        loader.setController(controller);
        // load into a Parent node called root

        Parent root = loader.load();
        scene = new Scene(root, 600, 400);

    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
    	if (nextLevel == null) {
            delay.setOnFinished( event -> mainScreen.start() );
    	} else {
            delay.setOnFinished( event -> nextLevel.start() );
    	}
        delay.play();

        
    }

	/**
	 * @param nextLevel the nextLevel to set
	 */
	public void setNextLevel(DungeonScreen nextLevel) {
		this.nextLevel = nextLevel;
	}
    
    
    /*

    public LevelController getController() {
        return controller;
    }
    */

}
