package unsw.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ControlsController {
    private MainScreen mainScreen;
    private ControlsScreen controlsScreen;

    @FXML
    public void initialize() {
    }
    @FXML
    private Button backButton;

    @FXML
    void handleBackButton(ActionEvent event) {
    	if (mainScreen != null) {
    		mainScreen.start();
    	} else {
    		controlsScreen.end();
    	}

    }

	/**
	 * @param mainScreen the mainScreen to set
	 */
	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}
    


}
