package unsw.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.obstacles.Door;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private HashMap<Entity, ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, HashMap<Entity, ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new HashMap<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");
        Image open_door = new Image("/open_door.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities.values())
            squares.getChildren().add(entity);
        
        // Removing entity from dungeon
        for (Entity entity : initialEntities.keySet()) {
        	entity.visibleOnMap().addListener(new ChangeListener<Boolean>() {

    			@Override
    			public void changed(ObservableValue<? extends Boolean> observable, 
    					Boolean oldValue, Boolean newValue) {
    				if (newValue == false) {
    					Node n = initialEntities.get(entity);
    					squares.getChildren().remove(n);
    				}		
    			}
            });
        	// Changing door image 
        	if (entity instanceof Door) {
        		((Door) entity).getClosed().addListener(new ChangeListener<Boolean>() {

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
							Node n = initialEntities.get(entity);
							int col = GridPane.getColumnIndex(n);
							int row = GridPane.getRowIndex(n);
							// change that picture 
							squares.getChildren().remove(n);
							squares.add(new ImageView(open_door), col, row);
							initialEntities.put(entity, new ImageView(open_door));
							
					}
        			
        		});
        	}
        	
        }
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
        	//squares.
        	//dungeon.printEntities();
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        default:
            break;
        }
    }

}

