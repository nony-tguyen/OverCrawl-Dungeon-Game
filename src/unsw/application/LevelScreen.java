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
    
    private DungeonScreen nextLevel;
    private Scene scene;
   

    public LevelScreen(Stage stage, String text, DungeonScreen nextLevel) throws IOException {
        this.stage = stage;
        title = text;
        this.nextLevel = nextLevel;
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
        //this.text.setText("level 2");
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> nextLevel.start() );
        delay.play();

        
    }
    
    /*

    public LevelController getController() {
        return controller;
    }
    */
}