package unsw.application;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LevelController {

    @FXML
    private Text levelText;
    
    private String text;
    
    public LevelController(String text) {
    	this.text = text;
	}
    @FXML
    public void initialize() {
    	levelText.setText(text);
    }


}