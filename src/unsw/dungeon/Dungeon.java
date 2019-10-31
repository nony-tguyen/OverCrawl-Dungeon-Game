/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

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
    public boolean isGridAvail(Entity curr, int x, int y) {
    	ArrayList<Entity> entitiesOnGrid = checkGrid(x, y);
    	for (Entity e : entitiesOnGrid) {
        	if (e != null) {
            	if (e instanceof Wall) {
    				//System.out.println("hi");
            		return ((Wall) e).canOverlap();
            	} else if (e instanceof Boulder) {
    				//System.out.println(curr + "" + "" + x + "" + y);
            		// Check if the boulder in front of the palyer can be moved
            		return ((Boulder) e).canMove(curr, x, y);
            	} else if (e instanceof FloorSwitch) {
            		return ((FloorSwitch) e).canOverlap(curr, x, y); 
            	}
        	}  		
    	}
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
    
}
