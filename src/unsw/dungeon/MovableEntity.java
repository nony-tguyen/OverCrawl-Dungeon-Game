package unsw.dungeon;

public abstract class MovableEntity extends Entity{
	protected Dungeon dungeon;
	protected Direction direction;
	
	public MovableEntity(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon; 
	}
    public void moveUp() {
        if (canMove(this, getX(), getY() - 1)) {
            y().set(getY() - 1);  
            direction = Direction.UP;
            action();
        }
    }

    public void moveDown() {
        if (canMove(this, getX(), getY() + 1)) {
            y().set(getY() + 1);
            direction = Direction.DOWN;
            action();
        }
    }

    public void moveLeft() {
        if (canMove(this, getX() - 1, getY())) {
            x().set(getX() - 1);
            direction = Direction.LEFT;
            action();
        }
    }

    public void moveRight() {
        if (canMove(this, getX() + 1, getY())) {
            x().set(getX() + 1);
            direction = Direction.RIGHT;
            action();
        }
    }
    public boolean canMove(Entity entity, int desiredX, int desiredY) {
    	if (dungeon.isGridAvail(entity, desiredX, desiredY)) {
        	return true;  		
    	} else {
        	return false;   		
    	}
    }
    
    /**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}
	public abstract void action();
}