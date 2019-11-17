package unsw.application;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class BufferController {

    @FXML
    private Text levelText;
    
    private String text;
    
    public BufferController(String text) {
    	this.text = text;
	}
    @FXML
    public void initialize() {
    	levelText.setText(text);
    }


}
