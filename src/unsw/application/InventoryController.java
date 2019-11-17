package unsw.application;

import java.util.List;

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

public class InventoryController {
	@FXML
    private Pane displayInventory;
	
    @FXML
    private GridPane invGrid;
	
	private InventoryScreen inventoryScreen;
	private DungeonScreen dungeonScreen;
	private List<CollectableEntity> inv1;
	private List<CollectableEntity> inv2;

	public InventoryController(InventoryScreen inventoryScreen) {
		this.inventoryScreen = inventoryScreen;
	}
    @FXML
    public void initialize() {
    	// listen for inventory changes 
        invGrid.setHgap(2);
        invGrid.setVgap(30);
    }
	@FXML
    public void handleKeyRelease(KeyEvent event) {
		if (event.getCode() == KeyCode.I) {
			inventoryScreen.close();
		}
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
	/**
	 * @param inv the inv to set
	 */
	public void setInv1(List<CollectableEntity> inv) {
		this.inv1 = inv;
	}
	public void setInv2(List<CollectableEntity> inv) {
		this.inv2 = inv;
	}
	public void update() {
    	invGrid.getChildren().clear();
		addPicture(inv1, 1);
		if (inv2 != null) {
			Label player2Label = new Label();
			player2Label.setText("Player 2");
			player2Label.setTranslateX(26);
			player2Label.setTranslateY(115);
			displayInventory.getChildren().add(player2Label);
			addPicture(inv2, 2);	
		}
	}
	public void addPicture(List<CollectableEntity> inv, int player) {
    	Image treasureImage = new Image("/gold_pile.png");
    	Image swordImage = new Image("/greatsword_1_new.png");
    	Image keyImage = new Image("/key.png");
    	Image invincibilityImage = new Image("/brilliant_blue_new.png");
    	for (CollectableEntity e : inv) {
    		if (e instanceof Treasure) {
    			invGrid.addRow(player - 1, new ImageView(treasureImage));	
    		} else if (e instanceof Key) {
    			invGrid.addRow(player - 1, new ImageView(keyImage));
    		} else if (e instanceof Sword) {
    			invGrid.addRow(player - 1, new ImageView(swordImage));	
    		} else if (e instanceof InvincibilityPotion) {
    			invGrid.addRow(player - 1, new ImageView(invincibilityImage));
    		}
    	} 	
	}

	
}
