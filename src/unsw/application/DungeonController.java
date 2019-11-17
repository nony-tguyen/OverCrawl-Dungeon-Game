package unsw.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.Animation;
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
import unsw.dungeon.MovableEntity;
import unsw.dungeon.Player;
import unsw.dungeon.obstacles.Door;
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

    private List<Player> players;

    private Dungeon dungeon;
    
    private List<Timeline> enemyTimeline;
    
    private GoalScreen goalScreen;
    
    private InventoryScreen inventoryScreen;
    
    private DungeonScreen dungeonScreen;

    public DungeonController(Dungeon dungeon, HashMap<Entity, ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.players = dungeon.getPlayers();
        this.initialEntities = new HashMap<>(initialEntities);
        initializeEnemyTimeline();
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
        // Add the non movable entities first
        for (Entity e : initialEntities.keySet()) {
        	if (! (e instanceof MovableEntity)) {
        		squares.getChildren().add(initialEntities.get(e));
        	}
        }
        for (Entity e : initialEntities.keySet()) {
        	if (e instanceof MovableEntity) {
        		squares.getChildren().add(initialEntities.get(e));
        	}
        }
        /*
        for (ImageView entity : initialEntities.values())
        	//if ()
            squares.getChildren().add(entity);
        */
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
        //setupInventory();
        dungeonGameProgress();
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case W:
        	players.get(0).moveUp();
            break;
        case S:
        	players.get(0).moveDown();
            break;
        case A:
        	players.get(0).moveLeft();
            break;
        case D:
        	players.get(0).moveRight();
            break;
        case SPACE:
        	useSword(1);
        	break;
        case DIGIT1:
        	useInvincibilityPotion(1);
        	break;
        case TAB:
        	goalScreen.start();
        	break;
        case I:
        	inventoryScreen.start();
        default:
            break;
        }

        // Controls for player 2
        if (players.size() == 2) {
            switch (event.getCode()) {
	        case UP:
	        	players.get(1).moveUp();
	            break;
	        case DOWN:
	        	players.get(1).moveDown();
	            break;
	        case LEFT:
	        	players.get(1).moveLeft();
	            break;
	        case RIGHT:
	        	players.get(1).moveRight();
	            break;
	        case CONTROL:
	        	useSword(2);
	        	break;
	        case DIGIT0:
	        	useInvincibilityPotion(2);
	        	break;
			default:
				break;
            }
        }
		playEnemyTimeline();
    }
    
    private void dungeonGameProgress() {
    	dungeon.isDungeonComplete().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, 
					Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					System.out.println("Game Finished!");
					stopEnemyTimeline();
					nextLevel();
				}
			}
    	});
    	dungeon.isGameOver().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, 
					Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					System.out.println("Game Over!");
					stopEnemyTimeline();
					gameOver();
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
		Timeline enemy3Behaviour = new Timeline(new KeyFrame(Duration.millis(150), e -> enemyFlash(EnemyType.GNOME, false)),
				   								new KeyFrame(Duration.millis(300), e -> enemyFlash(EnemyType.GNOME, true)));
		enemy3Behaviour.setAutoReverse(true);
		enemy3Behaviour.setCycleCount(Animation.INDEFINITE);
		enemy3.onFinishedProperty().set(e -> {
			for (Enemy enemy : dungeon.getEnemies()) {
				enemy3Behaviour.stop();
				enemy.explode();
			}
		});
		
		enemyTimeline.add(enemy1);
		enemyTimeline.add(enemy2);
		enemyTimeline.add(enemy3);
		enemyTimeline.add(enemy3Behaviour);
    }
    
    private void playEnemyTimeline() {
    	for (Timeline timeline : enemyTimeline) 
    		timeline.play();
    }
    
    private void stopEnemyTimeline() {
    	for (Timeline timeline : enemyTimeline) 
    		timeline.stop();
    }
    
    /**
     * Set up enemy movement
     */
    private void enemyMove(EnemyType type) {
    	for (Enemy enemy : dungeon.getEnemies()) {
    		if (enemy.getEnemyType() == type) {
    			if (dungeon.getPlayer(1) != null) {
    				enemy.moveEnemy(players.get(0).getX(), players.get(0).getY());
    			} else if (players.size() == 2) {
    				enemy.moveEnemy(players.get(1).getX(), players.get(1).getY());
    			}
    		}   			
    		/*if (dungeon.isGameOver().get() == false) {
        		enemy.moveEnemy(players.get(0).getX(), players.get(0).getY());  			
    		}*/
    	}
    }
    
    private void enemyFlash(EnemyType type, Boolean value) {
    	for (Enemy enemy : dungeon.getEnemies()) {
    		if (enemy.getEnemyType() == type) {
    			ImageView image = initialEntities.get(enemy);
    			image.setVisible(value);
    		}
    	}
    }
    
    /**
     * Handle player command to use their sword
     */
    private void useSword(int playerNum) {
    	if (players.get(playerNum - 1).getSword() != null) {
    		players.get(playerNum - 1).getSword().useItem(players.get(playerNum - 1));
    	}
    }
    
    /**
     * Handle player command to use invincibility potion
     */
    private void useInvincibilityPotion(int playerNum) {
    	for (Entity entity : players.get(playerNum - 1).getInventory()) {
    		if (entity instanceof InvincibilityPotion) {
    			InvincibilityPotion ip = (InvincibilityPotion) entity;
    			ip.useItem(players.get(playerNum - 1));
    			Timeline potionTimer = new Timeline(new KeyFrame(Duration.seconds(ip.timeLimit),
    												e -> ip.removeItem(players.get(playerNum - 1)),
    												new KeyValue(ip.getTimeRemain(), 0)));
    			potionTimer.play();
    		}
    	}
    }
    
	public void setGoalScreen(GoalScreen goalScreen) {
        this.goalScreen = goalScreen;
    }
	public void setInventoryScreen(InventoryScreen inventoryScreen) {
        this.inventoryScreen = inventoryScreen;
        inventoryScreen.getController().setInv1(players.get(0).getInventory());
        if (players.size() == 2) {
            inventoryScreen.getController().setInv2(players.get(1).getInventory());
        }
    }
	

	/**
	 * @param dungeonScreen the dungeonScreen to set
	 */
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	public void nextLevel() {
		dungeonScreen.next();
	} 
	public void gameOver() {
		dungeonScreen.end();
	}

}

