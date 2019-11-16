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
import unsw.dungeon.combat.EnemyType;
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
    
    private List<Timeline> enemyTimeline;
    
    private GoalScreen goalScreen;

    public DungeonController(Dungeon dungeon, HashMap<Entity, ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new HashMap<>(initialEntities);
        initializeEnemyTimeline();
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
        playEnemyTimeline();
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
    
    private void initializeEnemyTimeline() {
    	enemyTimeline = new ArrayList<>();
    	Timeline enemy1 = new Timeline(new KeyFrame(Duration.millis(1000), e -> enemyMove(EnemyType.NORMAL)));	
		enemy1.setCycleCount(Timeline.INDEFINITE);
		
		Timeline enemy2 = new Timeline(new KeyFrame(Duration.millis(500), e -> enemyMove(EnemyType.HOUND)));	
		enemy2.setCycleCount(Timeline.INDEFINITE);
		
		Timeline enemy3 = new Timeline(new KeyFrame(Duration.millis(1000), e -> enemyMove(EnemyType.GNOME)));	
		enemy3.setCycleCount(8);
		enemy3.onFinishedProperty().set(e -> {
			for (Enemy enemy : dungeon.getEnemies()) {
				enemy.explode();
			}
		});
		
		enemyTimeline.add(enemy1);
		enemyTimeline.add(enemy2);
		enemyTimeline.add(enemy3);
    }
    
    private void playEnemyTimeline() {
    	for (Timeline timeline : enemyTimeline) {
    		timeline.play();
    	}
    }
    
    /**
     * Set up enemy movement
     */
    private void enemyMove(EnemyType type) {
    	for (Enemy enemy : dungeon.getEnemies()) {
    		if (enemy.getEnemyType() == type)
    			enemy.moveEnemy(player.getX(), player.getY());
    	}
    }
    
    private void enemyExplode() {
    	//for ()
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

