package unsw.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button startButton;

    private LevelScreen singlePlayerScreen;
    
    private MainScreen mainScreen;

    @FXML
    void handleSingleplayer(ActionEvent event) {
    	singlePlayerScreen.start();
        //dungeonScreen.start();
    }
    @FXML
    void handleMultiplayer(ActionEvent event) {
        //dungeonScreen.start();
    }
    @FXML
    void handleControls(ActionEvent event) {
        //dungeonScreen.start();
    }

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

	/**
	 * @param singlePlayerScreen the singlePlayerScreen to set
	 */
	public void setSinglePlayerScreen(LevelScreen singlePlayerScreen) {
		this.singlePlayerScreen = singlePlayerScreen;
	}
    

}

