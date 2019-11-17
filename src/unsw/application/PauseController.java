package unsw.application;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Key;
import unsw.dungeon.Treasure;
import unsw.dungeon.combat.InvincibilityPotion;
import unsw.dungeon.combat.Sword;

public class PauseController {
	@FXML
    private Pane displayInventory;
	
    @FXML
    private GridPane invGrid;
	
	private PauseScreen pauseScreen;


	public PauseController(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
	}
    @FXML
    public void initialize() {
    }
    @FXML
    void handleKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			pauseScreen.close();
		}
    }

    @FXML
    void handleResume(ActionEvent event) {
    	pauseScreen.close();
    }

    @FXML
    void handleReturn(ActionEvent event) throws IOException {
    	//pauseScreen.close();
    	pauseScreen.returnMenu();

    }

    @FXML
    void handleViewControls(ActionEvent event) {

    }

	
}
