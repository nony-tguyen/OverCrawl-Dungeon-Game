package unsw.application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import unsw.dungeon.goals.GoalComponent;

public class GoalController {
	@FXML
    private Pane displayGoal;
	
	private GoalScreen goalScreen;
	private DungeonScreen dungeonScreen;

	public GoalController(GoalScreen goalScreen) {
		this.goalScreen = goalScreen;
	}
	
	@FXML
    public void initialize() {
		
	}
	
	@FXML
    public void handleKeyRelease(KeyEvent event) {
		if (event.getCode() == KeyCode.TAB) {
			System.out.println("key released");
			goalScreen.close();
		}
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
	
	public void addGoalDisplay(Label goalLabel, GoalComponent goal) {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
		
		Label title = new Label(goal.getDescription());
		title.setFont(new Font("Comic Sans MS", 24));
		Label total = new Label("/  " + goal.getGoalTotal());
		total.setFont(new Font("Comic Sans MS", 24));
		goalLabel.setStyle("-fx-font-weight: bold");
		goalLabel.setFont(new Font("Comic Sans MS", 24));
		HBox.setMargin(title, new Insets(0, 100, 0, 40));
		HBox.setMargin(goalLabel, new Insets(0, 0, 0, 0));
		HBox.setMargin(total, new Insets(0, 100, 0, 0));
		hbox.getChildren().addAll(title, goalLabel, total);
		
		displayGoal.getChildren().add(hbox);
    }
}
