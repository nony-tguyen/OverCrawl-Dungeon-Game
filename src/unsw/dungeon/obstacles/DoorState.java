package unsw.dungeon.obstacles;

import unsw.dungeon.Entity;

/**
 * A state interface for the door
 * @author z5207825
 *
 */
public interface DoorState {
	public boolean isBlocking(Entity subject);
}
