/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;
import unsw.dungeon.obstacles.Wall;

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

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
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
    public boolean isGridAvail(Entity subject, int x, int y) {
    	// Don't bother checking beyond the bounds
    	if (y < 0 || y >= this.height || x < 0 || x >= this.width) {
    		return false;
    	}
    	ArrayList<Entity> entitiesOnGrid = checkGrid(x, y);
    	for (Entity e : entitiesOnGrid) {
        	if (e != null) {
        		if (e instanceof Boulder) {
                	//System.out.println("aSubject is " + subject + "Checking at " + x + " " + y + "Blocker is " + e);
                	// relies on player's current pos. but this 
                	// only need to check x, y vs boulder
            		return (((Boulder) e).isBlocking(subject, x, y) ? false : true);
            	} else if (e.isBlocking(subject)) { // only returns if the entity is blocking, otherwise check again
            		// in case the next entity on same grid is blocking
                	//System.out.println("Subject is " + subject + "Checking at " + x + " " + y + "Blocker is " + e);
        			return false;
        		} 
        	}  
    	}
    	// looked at all the entities on the grid and none are blocking 
    	return true;
    }
    
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
    
    public List<Enemy> getEnemies() {
    	List<Enemy> enemies = new ArrayList<>();
    	for (Entity e : entities) {
    		if (e instanceof Enemy) {
    			enemies.add((Enemy) e);
    		}
    	}
    	return enemies;
    }
    
    public List<Entity> getEntities() {
    	return entities;
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    public boolean gameOver() {
    	if (player == null) 
    		return true;
    	else 
    		return false;
    }
}
