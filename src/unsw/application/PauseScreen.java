package unsw.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PauseScreen {
	private Stage stage;
    private String title;
    private PauseController controller;

    //private DungeonScreen dungeonScreen;
    private Scene scene;
    
    public PauseScreen(Stage stage, DungeonApplication dApp) throws IOException {
    	this.stage = stage;
        this.stage = new Stage(StageStyle.TRANSPARENT);
        this.stage.initModality(Modality.APPLICATION_MODAL);
        //this.stage.setOpacity(1);
        title = "Paused";
        
        controller = new PauseController(this, dApp);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/PauseView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        //root.relocate(100, 80);
        
        scene = new Scene(root, 600, 400);
        scene.setFill(Color.TRANSPARENT);
        root.requestFocus();
    }
    
    public void start() {
    	// update 
    	//scene.
    	//stage.close();
        stage.setTitle(title);
        stage.setScene(scene);
        //stage.show();
        stage.showAndWait();
    }

    
    public PauseController getController() {
        return controller;
    }
    
	public void close() {
		
    	stage.close();
    }

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}
	public void setdApp(DungeonApplication dApp) {
		controller.setdApp(dApp);
		
	}

}
