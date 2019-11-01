package unsw.dungeon.obstacles;

import unsw.dungeon.Entity;

public class Door extends Entity {
	
	private DoorState state;
	private int id;
	public Door(int x, int y, DoorState state, int id) {
		super(x, y);
		this.state = state;
		this.id = id; 
	}
	
    /**
	 * @param state the state to set
	 */
	public void setState(DoorState state) {
		this.state = state;
	}

	public boolean isBlocking(Entity subject) {
		return state.isBlocking();
	}

}
