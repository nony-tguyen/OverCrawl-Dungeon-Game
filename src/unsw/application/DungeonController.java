package unsw.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.combat.InvincibilityPotion;

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
    
    private Timeline timeline;
    
    private GoalScreen goalScreen;

    public DungeonController(Dungeon dungeon, HashMap<Entity, ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new HashMap<>(initialEntities);
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> enemyMove()));	
		timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

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
        }
        dungeonGameProgress();
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
        case SPACE:
        	useSword();
        	break;
        case I:
        	useInvincibilityPotion();
        	break;
        case TAB:
        	goalScreen.start();
        	break;
        default:
            break;
        }
        timeline.play();
    }
    
    private void dungeonGameProgress() {
    	dungeon.isGameFinished().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, 
					Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					System.out.println("Game Finished!");
				}
			}
    	});
    }
    
    /**
     * Set up enemy movement
     */
    private void enemyMove() {
    	for (Enemy enemy : dungeon.getEnemies()) {
    		enemy.moveEnemy(player.getX(), player.getY());
    	}
    }
    
    /**
     * Handle player command to use their sword
     */
    private void useSword() {
    	if (player.getSword() != null) {
    		player.getSword().useItem(player);
    	}
    }
    
    /**
     * Handle player command to use invincibility potion
     */
    private void useInvincibilityPotion() {
    	for (Entity entity : player.getInventory()) {
    		if (entity instanceof InvincibilityPotion) {
    			InvincibilityPotion ip = (InvincibilityPotion) entity;
    			ip.useItem(player);
    			Timeline potionTimer = new Timeline(new KeyFrame(Duration.seconds(ip.timeLimit),
    												e -> ip.removeItem(player),
    												new KeyValue(ip.getTimeRemain(), 0)));
    			potionTimer.play();
    		}
    	}
    }
    
	public void setGoalScreen(GoalScreen goalScreen) {
        this.goalScreen = goalScreen;
    }
}

