/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

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
    	Entity e = checkGrid(x, y);
    	if (e != null && checkGrid(x, y) instanceof Wall) {
    		return false;
    	} else {
        	return true;   		
    	}
    }
    
    public Entity checkGrid(int x, int y) {
    	for (Entity e: entities) {
    		//System.out.println(e.);
    		if (e != null) {
        		if (e.getX() == x && e.getY() == y) {
        			return e;
        		}  			
    		}
    	}
    	return null;
    }
}
