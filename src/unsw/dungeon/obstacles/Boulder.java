package unsw.dungeon.obstacles;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.MovableEntity;
import unsw.dungeon.Player;

public class Boulder extends MovableEntity {
	//private Dungeon dungeon; 
   // private int x;
    //private int y;
	
    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        //this.dungeon = dungeon;
    }
 
    @Override
    public void moveUp() {
        if (getY() > 0) {
            y().set(getY() - 1);    	
        }
        action();
    }
    
    @Override
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
        action();
    }
    @Override
    public void moveLeft() {
    	
        if (getX() > 0)
            x().set(getX() - 1);
        action();
    }
    @Override
    public void moveRight() {
    	// if the current one is a floor switch then untrigger?
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
        action();
    }
    
	@Override
	public void action() {
		// TODO Auto-generated method stub
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
	public boolean isBlocking(Entity curr, int desiredX, int desiredY) {
		if (!(curr instanceof Player)) {
			return true;
		} else {
			//check if there's an obstacle in the spot the boulder would move into
			// find the diff 
			// curr (1,1)
			// (2, 1) 
			// want to move right one 
			
			int xDiff = desiredX - curr.getX();
			int yDiff = desiredY - curr.getY();
			// Check if the boulder can be moved into the grid it will
			// be pushed into 
			//System.out.println("Desired X:" + (desiredX + xDiff) + "Desired Y:" + (desiredY + yDiff));
			if (dungeon.isGridAvail(this, (desiredX + xDiff), (desiredY + yDiff))) {
				// It's available so just move it 
				moveBoulder(xDiff, yDiff);
				return false;
			} else {
				return true;			
			}
		}
	}
	
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
	 * 
	 * @return the 
	 */
	public FloorSwitch isSwitch(int x, int y) {
		ArrayList<Entity> entitiesOnGrid = dungeon.checkGrid( x, y );
		for (Entity e : entitiesOnGrid) {
			if(e instanceof FloorSwitch) {
				return (FloorSwitch) e;	
			}
		}
		return null;
	}
}
