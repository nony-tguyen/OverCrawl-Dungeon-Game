package unsw.application;

import java.util.List;

import javafx.fxml.FXML;
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
	private List<CollectableEntity> inv;

	public InventoryController(InventoryScreen inventoryScreen) {
		this.inventoryScreen = inventoryScreen;
	}
    @FXML
    public void initialize() {
    	// listen for inventory changes 
        invGrid.setHgap(2);
        invGrid.setVgap(2);
    	//dungeonScreen.getController().
    }
	@FXML
    public void handleKeyRelease(KeyEvent event) {
		if (event.getCode() == KeyCode.I) {
			System.out.println("key released");
			invGrid = new GridPane();
			inventoryScreen.close();
		}
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
	/**
	 * @param inv the inv to set
	 */
	public void setInv(List<CollectableEntity> inv) {
		this.inv = inv;
	}
	public void update() {
    	Image treasureImage = new Image("/gold_pile.png");
    	Image swordImage = new Image("/greatsword_1_new.png");
    	Image keyImage = new Image("/key.png");
    	Image invincibilityImage = new Image("/brilliant_blue_new.png");

    	//int col = 0;
    	if (inv != null) {
        	for (CollectableEntity e : inv) {
        		System.out.println(e);
        		if (e instanceof Treasure) {
        			invGrid.addRow(0, new ImageView(invincibilityImage));
        			//invGrid.add(new ImageView(treasureImage), col, 0);		
        		} else if (e instanceof Key) {
        			invGrid.addRow(0, new ImageView(invincibilityImage));
        			//invGrid.add(new ImageView(keyImage), col, 0);		
        			System.out.println("Adding key");
        		} else if (e instanceof Sword) {
        			invGrid.addRow(0, new ImageView(invincibilityImage));
        			//invGrid.add(new ImageView(swordImage), col, 0);		
        			System.out.println("Adding sword");
        		} else if (e instanceof InvincibilityPotion) {
        			invGrid.addRow(0, new ImageView(invincibilityImage));
        			//invGrid.addColumn(new ImageView(invincibilityImage), col, 0);
        			//invGrid.add(new ImageView(invincibilityImage), col, 0);
        			System.out.println("Adding ");
        		}
        		//col++;

        	} 		
    	}
	}

	
}
