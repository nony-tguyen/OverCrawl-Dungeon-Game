package unsw.application;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControlsScreen {

    private Stage stage;
    private ControlsController controller;

    private Scene scene;
   

    public ControlsScreen(Stage stage) throws IOException {
        this.stage = stage;
        controller = new ControlsController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/ControlsView.fxml"));
        
        loader.setController(controller);
        // load into a Parent node called root

        Parent root = loader.load();
        scene = new Scene(root, 600, 400);

    }

    public void start() {
        //stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        
    }
    public void end() {
        //stage.setTitle(title);
        stage.close();

        
    }

	/**
	 * @return the controller
	 */
	public ControlsController getController() {
		return controller;
	}
    


}
