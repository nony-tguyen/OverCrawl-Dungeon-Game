/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.obstacles.Boulder;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private GoalComponent goal;
    private BooleanProperty dungeonCompleted;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        dungeonCompleted = new SimpleBooleanProperty(false);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void printEntities() {
    	for (Entity e: entities) {
    		System.out.println(e);
    	}
    }
    
    /**
     * Determine whether the grid requested is available for the subject to move into
     * @param subject
     * @param x
     * @param y
     * @return
     */
    public boolean isGridAvail(Entity subject, int x, int y) {
    	// Don't bother checking beyond the bounds
    	if (y < 0 || y >= this.height || x < 0 || x >= this.width) {
    		return false;
    	}
    	ArrayList<Entity> entitiesOnGrid = checkGrid(x, y);
    	for (Entity e : entitiesOnGrid) {
        	if (e != null) {
        		if (e instanceof Boulder) {
            		return (((Boulder) e).isBlocking(subject, x, y) ? false : true);
            	} else if (e.isBlocking(subject)) { // only returns if the entity is blocking, otherwise check again
            		// in case the next entity on same grid is blocking
        			return false;
        		} 
        	}  
    	}
    	// looked at all the entities on the grid and none are blocking 
    	return true;
    }
    
    /**
     * Gets the list of entities that exist in the requested grid
     * @param x
     * @param y
     * @return
     */
    public ArrayList<Entity> checkGrid(int x, int y) {
    	ArrayList<Entity> entitiesOnGrid = new ArrayList<Entity>();
    	for (Entity e: entities) {
    		if (e != null) {
        		if (e.getX() == x && e.getY() == y) {
        			entitiesOnGrid.add(e);
        		}  			
    		}
    	}
    	return entitiesOnGrid;
    }
    
    /**
     * @return Enemy entities in the dungeon
     */
    public List<Enemy> getEnemies() {
    	List<Enemy> enemies = new ArrayList<>();
    	for (Entity e : entities) {
    		if (e instanceof Enemy) {
    			enemies.add((Enemy) e);
    		}
    	}
    	return enemies;
    }
    
    /**
     * @return All entities in the dungeon
     */
    public List<Entity> getEntities() {
    	return entities;
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    /**
     * Set the goal to achieve for the dungeon
     * @param goal
     */
    public void setGoal(GoalComponent goal) {
    	this.goal = goal;
    }
    
    /**
     * Update the game state after an action
     */
    public void updateGameProgression() {
    	if (player == null || goal.checkGoalCompleted().get()) {
    		dungeonCompleted.set(true);
    	} else {
    		dungeonCompleted.set(false);
    	}
    }
    
    /**
     * @return True if the game has finished by player dying or goals completed
     */
    public BooleanProperty isGameFinished() {
    	return dungeonCompleted;
    }
}
