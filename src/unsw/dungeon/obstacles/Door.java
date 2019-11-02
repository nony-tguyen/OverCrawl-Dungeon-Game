package unsw.dungeon.obstacles;

import unsw.dungeon.Entity;

public class Door extends Entity {
	
	private DoorState state;
	private int id;
	
	public Door(int x, int y, int id) {
		super(x, y);
		this.state = new ClosedState(this);
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
	

}
