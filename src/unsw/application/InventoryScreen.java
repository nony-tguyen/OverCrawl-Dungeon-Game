package unsw.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InventoryScreen {
	private Stage stage;
    private String title;
    private InventoryController controller;
    private Scene scene;
    
    public InventoryScreen(Stage stage) throws IOException {
        this.stage = new Stage(StageStyle.TRANSPARENT);
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.setOpacity(0.7);
        title = "Inventory";
        
        controller = new InventoryController(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/InventoryView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        //root.relocate(100, 80);
        scene = new Scene(root, 600, 275);
        scene.setFill(Color.TRANSPARENT);
        root.requestFocus();
    }
    
    public void start() {
    	// update 
    	controller.update();
    	//scene.
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public InventoryController getController() {
        return controller;
    }
    
    public void close() {
    	stage.close();
    }
}
