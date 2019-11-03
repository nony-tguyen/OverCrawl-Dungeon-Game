package unsw.dungeon.obstacles;

import unsw.dungeon.Entity;

/**
 * A door state that allows entities to pass through
 * @author z5207825
 *
 */
public class OpenState implements DoorState {

	@Override
	public boolean isBlocking(Entity subject) {
		return false;
	}

}
