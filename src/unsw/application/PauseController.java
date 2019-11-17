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
    private Pane displayPause;
	
	private PauseScreen pauseScreen;
	private DungeonScreen dungeonScreen;
    private DungeonApplication dApp;
	


	public PauseController(PauseScreen pauseScreen, DungeonApplication dApp) {
		this.pauseScreen = pauseScreen;
		this.dApp = dApp;
	}
    @FXML
    public void initialize() {
    }
    @FXML
    void handleKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			pauseScreen.close();
			//dungeonScreen.start();
		}
    }

    @FXML
    void handleResume(ActionEvent event) {
    	pauseScreen.close();
    	//dungeonScreen.start();
    }

    @FXML
    void handleReturn(ActionEvent event) throws IOException {
    	pauseScreen.close();
    	//dungeonScreen.turnOffMusic();
    	dApp.start(dungeonScreen.getStage());

    }


	public void setdApp(DungeonApplication dApp) {
		this.dApp = dApp;
		
	}
	/**
	 * @param dungeonScreen the dungeonScreen to set
	 */
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

	
}
