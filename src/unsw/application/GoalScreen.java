package unsw.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import unsw.dungeon.goals.GoalComponent;

public class GoalScreen {
	private Stage stage;
    private String title;
    private GoalController controller;
    private Scene scene;
    
    public GoalScreen(Stage stage) throws IOException {
        this.stage = new Stage(StageStyle.TRANSPARENT);
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.setOpacity(0.7);
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
    
    public void addDisplay(Label label, GoalComponent goal) {
    	controller.addGoalDisplay(label, goal);
    }
}
