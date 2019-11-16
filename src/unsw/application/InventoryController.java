package unsw.application;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class InventoryController {
	@FXML
    private Pane displayInventory;
	
    @FXML
    private GridPane squares;
	
	private InventoryScreen inventoryScreen;
	private DungeonScreen dungeonScreen;

	public InventoryController(InventoryScreen inventoryScreen) {
		this.inventoryScreen = inventoryScreen;
	}
    @FXML
    public void initialize() {
    	// listen for inventory changes 
    	Image treasureImage = new Image("/gold_pile.png");
    	Image swordImage = new Image("/greatsword_1_new.png");
    	Image keyImage = new Image("/key.png");
    	Image invincibilityImage = new Image("/brilliant_blue_new.png");
        
    	dungeonScreen.getController().
    }
	@FXML
    public void handleKeyRelease(KeyEvent event) {
		if (event.getCode() == KeyCode.I) {
			System.out.println("key released");
			inventoryScreen.close();
		}
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
}
