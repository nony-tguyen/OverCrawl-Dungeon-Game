package unsw.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button startButton;

    private BufferScreen singlePlayerScreen;
    
    private MainScreen mainScreen;

	private BufferScreen multiPlayerScreen;

    @FXML
    void handleSingleplayer(ActionEvent event) {
    	singlePlayerScreen.start();
        //dungeonScreen.start();
    }
    @FXML
    void handleMultiplayer(ActionEvent event) {
    	multiPlayerScreen.start();
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
	public void setSinglePlayerScreen(BufferScreen singlePlayerScreen) {
		this.singlePlayerScreen = singlePlayerScreen;
	}
	public void setMultiPlayerScreen(BufferScreen multiPlayerScreen) {
		this.multiPlayerScreen = multiPlayerScreen;
		
	}
    

}

