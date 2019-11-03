package unsw.dungeon;

/**
 * A type of entity that can move around the dungeon
 *
 */
public abstract class MovableEntity extends Entity{
	protected Dungeon dungeon;
	protected Direction direction;
	
	public MovableEntity(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon; 
	}
	
	/**
	 * Move the entity 1 grid up
	 */
    public void moveUp() {
        if (canMove(this, getX(), getY() - 1)) {
            y().set(getY() - 1);  
            direction = Direction.UP;
            action();
        }
    }

    /**
     * Move the entity 1 grid down
     */
    public void moveDown() {
        if (canMove(this, getX(), getY() + 1)) {
            y().set(getY() + 1);
            direction = Direction.DOWN;
            action();
        }
    }

    /**
     * Move the entity 1 grid left
     */
    public void moveLeft() {
        if (canMove(this, getX() - 1, getY())) {
            x().set(getX() - 1);
            direction = Direction.LEFT;
            action();
        }
    }

    /**
     * Move the entity 1 grid right
     */
    public void moveRight() {
        if (canMove(this, getX() + 1, getY())) {
            x().set(getX() + 1);
            direction = Direction.RIGHT;
            action();
        }
    }
    
    /**
     * Checks if the grid the entity wants to move to is not obstructing
     * @param entity
     * @param desiredX
     * @param desiredY
     * @return True if grid is non-obstructing otherwise false
     */
    public boolean canMove(Entity entity, int desiredX, int desiredY) {
    	if (dungeon.isGridAvail(entity, desiredX, desiredY)) {
        	return true;  		
    	} else {
        	return false;   		
    	}
    }
    
    /**
     * Direction that the entity is facing
	 * @return direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * The action the entity will take upon moving to a new grid
	 */
	public abstract void action();
}