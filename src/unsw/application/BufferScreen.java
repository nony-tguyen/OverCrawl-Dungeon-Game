package unsw.application;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BufferScreen {

    private Stage stage;
    private String title;
    private BufferController controller;
    private DungeonScreen nextLevel;
    private Scene scene;
    private DungeonApplication dApp;
   

    public BufferScreen(Stage stage, String text) throws IOException {
        this.stage = stage;
        title = text;
        this.nextLevel = null;
        controller = new BufferController(text);
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
            delay.setOnFinished( event -> {
				try {
					dApp.start(stage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} );
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

	public void setdApp(DungeonApplication dApp) {
		this.dApp = dApp;
		
	}
	
    
    
    /*

    public LevelController getController() {
        return controller;
    }
    */

}
