package unsw.dungeon;

public abstract class MovableEntity extends Entity{
	private Dungeon dungeon; 
	
	public MovableEntity(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon; 
	}
    public void moveUp() {
       	// check if obstacle
    	// or taken space
    	// Can't push boulder -> can't move
    	// 
        if (getY() > 0 && canMove(this, getX(), getY() - 1)) {
            y().set(getY() - 1);    	
        }
        action();
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && canMove(this, getX(), getY() + 1))
            y().set(getY() + 1);
        action();
    }

    public void moveLeft() {
        if (getX() > 0 && canMove(this, getX() - 1, getY()))
            x().set(getX() - 1);
        action();
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && canMove(this, getX() + 1, getY()))
            x().set(getX() + 1);
        action();
    }
    public boolean canMove(Entity entity, int desiredX, int desiredY) {
    	if (dungeon.isGridAvail(entity, desiredX, desiredY)) {
        	return true;  		
    	} else {
        	return false;   		
    	}
    }
    
    public abstract void action();
}
