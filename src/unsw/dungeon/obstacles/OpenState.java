package unsw.dungeon.obstacles;

import javafx.beans.property.BooleanProperty;
import unsw.dungeon.Entity;

/**
 * A door state that allows entities to pass through
 * @author z5207825
 *
 */
public class OpenState implements DoorState {
	private BooleanProperty closed;
	
	public ClosedState(Door door) {
		this.door = door;
		this.closed.set(false);
	}
	@Override
	public boolean isBlocking(Entity subject) {
		return false;
	}

}
