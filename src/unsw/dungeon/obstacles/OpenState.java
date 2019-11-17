package unsw.dungeon.obstacles;

import javafx.beans.property.BooleanProperty;
import unsw.dungeon.Entity;

/**
 * A door state that allows entities to pass through
 * @author z5207825
 *
 */
public class OpenState implements DoorState {
	private Door door;
	
	public OpenState(Door door) {
		this.door = door;
		this.door.setClosed(false);
	}
	@Override
	public boolean isBlocking(Entity subject) {
		return false;
	}

}
