package unsw.application;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GoalController {
	@FXML
    private Pane displayGoal;
	
	private GoalScreen goalScreen;
	private DungeonScreen dungeonScreen;

	public GoalController(GoalScreen goalScreen) {
		this.goalScreen = goalScreen;
	}
	
	@FXML
    public void handleKeyRelease(KeyEvent event) {
		if (event.getCode() == KeyCode.TAB) {
			System.out.println("key released");
			//dungeonScreen.start();
			goalScreen.close();
		}
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
	
	
}
