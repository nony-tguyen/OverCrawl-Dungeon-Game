package unsw.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GoalScreen {
	private Stage stage;
    private String title;
    private GoalController controller;
    private Scene scene;
    
    public GoalScreen(Stage stage) throws IOException {
        this.stage = new Stage(StageStyle.TRANSPARENT);
        this.stage.initModality(Modality.APPLICATION_MODAL);
        title = "Goals";
        
        controller = new GoalController(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/GoalView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root, 600, 400);
        scene.setFill(Color.TRANSPARENT);
        root.requestFocus();
    }
    
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public GoalController getController() {
        return controller;
    }
    
    public void close() {
    	stage.close();
    }
}
