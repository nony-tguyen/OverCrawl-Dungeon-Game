package unsw.dungeon.obstacles;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.Entity;

/**
 * A door entity that has an open and close state
 *
 */
public class Door extends Entity {
	private BooleanProperty closed;
	private DoorState state;
	private int id;
	
	public Door(int x, int y, int id) {
		super(x, y);
		this.state = new ClosedState(this);
		this.closed = new SimpleBooleanProperty(true);
		this.id = id; 
	}
	
    /**
	 * @param state the state to set
	 */
	public void changeState(DoorState state) {
		this.state = state;
	}

	/** Checks if the door blocks, depending on the state
	 * @param subject
	 */
	public boolean isBlocking(Entity subject) {
		return state.isBlocking(subject);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the state
	 */
	public DoorState getState() {
		return state;
	}

	/**
	 * @param closed the closed to set
	 */
	public void setClosed(boolean b) {
		this.closed.set(b);
	}

	/**
	 * @return the closed
	 */
	public BooleanProperty getClosed() {
		return closed;
	}
	
	

}
