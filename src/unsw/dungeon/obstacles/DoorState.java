package unsw.dungeon.obstacles;

import unsw.dungeon.Entity;

public interface DoorState {
	public boolean isBlocking(Entity subject);
}
