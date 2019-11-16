package unsw.application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import unsw.dungeon.goals.GoalComponent;

public class GoalControllerLoader {
	
	private List<HBox> goals;
	
	public GoalControllerLoader() {
		goals = new ArrayList<>();
	}
	
	public void addDisplay(Label goalLabel, GoalComponent goal) {
    	System.out.println("adding display");
    	HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.setSpacing(10);
		hbox.setPrefWidth(600);
		
		Label title = new Label(goal.getDescription());
		title.setFont(new Font("Comic Sans MS", 18));
		Label total = new Label("/  " + goal.getGoalTotal());
		total.setFont(new Font("Comic Sans MS", 18));
		goalLabel.setStyle("-fx-font-weight: bold");
		goalLabel.setFont(new Font("Comic Sans MS", 18));
		HBox.setMargin(title, new Insets(0, 130, 0, 0));
		//HBox.setMargin(goalLabel, new Insets(0, 0, 0, 0));
		HBox.setMargin(total, new Insets(0, 100, 0, 0));
		hbox.getChildren().addAll(title, goalLabel, total);
    	
		goals.add(hbox);
    	//controller.addGoalDisplay(hbox);
    }
	
	public void addCompositeDisplay(Label goalLabel, GoalComponent goal) {
		HBox hbox = new HBox();
		goalLabel.setText("SubGoals");
		goalLabel.setFont(new Font("Arial", 20));
		goalLabel.setUnderline(true);
		hbox.setPrefWidth(600);
		HBox.setMargin(goalLabel, new Insets(0, 0, 0, 20));
		hbox.getChildren().add(goalLabel);
		
		goals.add(hbox);
	}
	
	public GoalController loadGoalController() {
		return new GoalController(goals);
	}
}
