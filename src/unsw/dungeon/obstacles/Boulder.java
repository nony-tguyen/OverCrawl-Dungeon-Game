package unsw.dungeon.obstacles;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.MovableEntity;
import unsw.dungeon.Player;

public class Boulder extends MovableEntity {
	
    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }
 
	@Override
	public void action() {
		// check if it went on a floor switch
		// If it's a floor switch trigger it. 
		// Need to check when the boulder goes off
		
		// If the place the boulder went onto is a switch
		FloorSwitch fs = isSwitch(getX(), getY());
		if (fs != null) {
			// Tell the floor switch to trigger itself 
			fs.trigger();
		}
	}
	/**
	 * Check if there's an obstacle in the spot the boulder would move into
	 * @param curr
	 * @param desiredXPlayer
	 * @param desiedYPlayer
	 * @return true is blocking, false if not 
	 */
	public boolean isBlocking(Entity curr, int desiredXPlayer, int desiedYPlayer) {
		// Block for everything but the player 
		if (!(curr instanceof Player)) {
			return true;
		} else {
			// For instance: if player currently at (1,1) and their desired coordinates are (2, 1) 
			// They want to move right one 
			
			int xDiff = desiredXPlayer - curr.getX();
			int yDiff = desiedYPlayer - curr.getY();
			// Check if the boulder can be moved into the grid it will be pushed into 
			if (dungeon.isGridAvail(this, (desiredXPlayer + xDiff), (desiedYPlayer + yDiff))) {
				// It's available so just move it, since already checked
				moveBoulder(xDiff, yDiff);
				return false;
			} else {
				return true;			
			}
		}
	}
	/**
	 * Moves boulder in one direction depending on which value is matched 
	 * @param x
	 * @param y
	 */
	public void moveBoulder(int x, int y) {
		// Check if the place the boulder will be moved from is a switch 
		FloorSwitch fs = isSwitch(getX(), getY());
		if (fs != null) {
			fs.untrigger();
		}
		switch (x) {
			// +1x so move right
			case 1:
				moveRight();
				break;
			case -1:
				moveLeft();
				break;
		}
		switch (y) {
			case 1:
				moveDown();
				break;
			case -1:
				moveUp();
				break;
		}
	}
	/**
	 * Checks if there is a switch at a particular grid
	 * @param x
	 * @param y
	 * @return
	 */
	public FloorSwitch isSwitch(int x, int y) {
		ArrayList<Entity> entitiesOnGrid = dungeon.checkGrid( x, y );
		for (Entity e : entitiesOnGrid) {
			if (e instanceof FloorSwitch) {
				return (FloorSwitch) e;	
			}
		}
		return null;
	}
}
