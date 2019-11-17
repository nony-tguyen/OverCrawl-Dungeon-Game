package unsw.application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GoalController {
	@FXML
    private Pane displayGoal;
	
	private GoalScreen goalScreen;
	private DungeonScreen dungeonScreen;
	private List<HBox> goals;
	

	public GoalController(List<HBox> goals) {	
		this.goals = new ArrayList<>(goals);
	}
	
	@FXML
    public void initialize() {
		VBox vbox = new VBox(5);
		vbox.setAlignment(Pos.CENTER_LEFT);
		vbox.setLayoutY(100);
		for (int i = goals.size() - 1; i >= 0; i--) {
			vbox.getChildren().add(goals.get(i));
		}

		displayGoal.getChildren().add(vbox);
	}
	
	@FXML
    public void handleKeyRelease(KeyEvent event) {
		if (event.getCode() == KeyCode.TAB) {
			System.out.println("key released");
			goalScreen.close();
		}
	}
	
	public void setGoalScreen(GoalScreen goalScreen) {
		this.goalScreen = goalScreen;
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
	
	public void addGoalDisplay(HBox hbox) {
		goals.add(hbox);
    }
}
